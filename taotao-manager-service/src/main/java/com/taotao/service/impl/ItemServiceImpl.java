package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

/**
 * Created by 孙建荣 on 17-5-30.下午9:59
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Resource(name = "itemAddItem")
    private Destination destination;
    @Value("${ITEM_INFO}")
    private String ITEM_INFO;
    @Value("${TIME_EXPIRE}")
    private Integer TIME_EXPIRE;
    private final JedisClient jedisClient;
    private final JmsTemplate jmsTemplate;
    private final TbItemMapper itemMapper;
    private final TbItemDescMapper tbItemDescMapper;

    @Autowired
    public ItemServiceImpl(TbItemMapper itemMapper, TbItemDescMapper tbItemDescMapper, JmsTemplate jmsTemplate, JedisClient jedisClient) {
        this.itemMapper = itemMapper;
        this.tbItemDescMapper = tbItemDescMapper;
        this.jmsTemplate = jmsTemplate;
        this.jedisClient = jedisClient;
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        // 先查询缓存
        try {
            String json = jedisClient.get(ITEM_INFO + ":" + itemId + ":DESC");
            if (StringUtils.isNoneBlank(json)) {
                // 把json数据转成实体类
                return JsonUtils.jsonToPojo(json, TbItemDesc.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        // 缓存中没有就查询数据库
        // 把查询结果添加到缓存
        try {
            jedisClient.set("ITEM_INFO:" + itemId + ":DESC", JsonUtils.objectToJson(tbItemDesc));
            // 设置过期时间,提高缓存的命中
            jedisClient.expire(ITEM_INFO + ":" + itemId + ":DESC", TIME_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbItemDesc;
    }

    /**
     * 根据商品Id查询商品
     *
     * @param itemId 商品id
     * @return 商品信息
     */
    @Override
    public TbItem getItemById(long itemId) {
        // 先查询缓存
        try {
            String json = jedisClient.get(ITEM_INFO + ":" + itemId + ":BASE");
            if (StringUtils.isNoneBlank(json)) {
                // 把json数据转成实体类
                return JsonUtils.jsonToPojo(json, TbItem.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        // 把查询结果添加到缓存中
        try {
            jedisClient.set(ITEM_INFO + ":" + itemId + ":BASE", JsonUtils.objectToJson(tbItem));
            // 设置过期时间,提高缓存的命中
            jedisClient.expire(ITEM_INFO + ":" + itemId + ":BASE", TIME_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbItem;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        // 设置分页信息
        PageHelper.startPage(page, rows);
        // 执行查询
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = itemMapper.selectByExample(tbItemExample);
        // 获取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        // 返回结果对象
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        easyUIDataGridResult.setRows(tbItems);
        return easyUIDataGridResult;
    }

    @Override
    public TaotaoResult addItem(TbItem tbItem, String desc) {
        // 生成商品id
        final long itemId = IDUtils.genItemId();
        // 补全item的属性
        tbItem.setId(itemId);
        // 补全商品状态  1-正常 2-下架   3-删除
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        // 向商品描述表中插入一条信息
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItem.setUpdated(new Date());
        tbItem.setCreated(new Date());
        // 向商品描述表中插入数据
        tbItemDescMapper.insert(tbItemDesc);
        // 向ActiveMq发送商品添加信息
        // 返回结果
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 发送商品的ID
                return session.createTextMessage(itemId + "");
            }
        });
        return TaotaoResult.ok();
    }
}














