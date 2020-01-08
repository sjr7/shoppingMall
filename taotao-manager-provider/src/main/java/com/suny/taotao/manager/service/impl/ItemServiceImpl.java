package com.suny.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suny.taotao.common.pojo.EasyUIDataGridResult;
import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.common.utils.IDUtils;
import com.suny.taotao.common.utils.JsonUtils;
import com.suny.taotao.manager.mapper.TbItemDescMapper;
import com.suny.taotao.manager.mapper.TbItemMapper;
import com.suny.taotao.manager.pojo.TbItem;
import com.suny.taotao.manager.pojo.TbItemDesc;
import com.suny.taotao.manager.pojo.TbItemExample;
import com.suny.taotao.manager.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by 孙建荣 on 17-5-30.下午9:59
 */
@Service
@Component
public class ItemServiceImpl implements ItemService {

    @Value("${ITEM_INFO}")
    private String ITEM_INFO;
    @Value("${TIME_EXPIRE}")
    private Integer TIME_EXPIRE;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;


    @Override
    public TbItemDesc getItemDescById(long itemId) {
        // 先查询缓存
        try {
            String json = stringRedisTemplate.opsForValue().get(ITEM_INFO + ":" + itemId + ":DESC");
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
            stringRedisTemplate.opsForValue().set("ITEM_INFO:" + itemId + ":DESC", JsonUtils.objectToJson(tbItemDesc));
            // 设置过期时间,提高缓存的命中
            stringRedisTemplate.expire(ITEM_INFO + ":" + itemId + ":DESC", TIME_EXPIRE, TimeUnit.SECONDS);
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
            String json = stringRedisTemplate.opsForValue().get(ITEM_INFO + ":" + itemId + ":BASE");
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
            stringRedisTemplate.opsForValue().set(ITEM_INFO + ":" + itemId + ":BASE", JsonUtils.objectToJson(tbItem));
            // 设置过期时间,提高缓存的命中
            stringRedisTemplate.expire(ITEM_INFO + ":" + itemId + ":BASE", TIME_EXPIRE, TimeUnit.SECONDS);
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
       /* jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 发送商品的ID
                return session.createTextMessage(itemId + "");
            }
        });*/
        throw new RuntimeException("active mq 功能为不全");
        // return TaotaoResult.ok();
    }
}














