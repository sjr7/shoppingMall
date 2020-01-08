package com.suny.taotao.manager.controller;

import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.manager.search.service.SearchItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 索引库维护
 * Created by 孙建荣 on 17-6-15.下午9:13
 */
@Controller
public class IndexManagerController {
    @Reference
    private SearchItemService searchItemServiceImpl;


    @RequestMapping("/index/import")
    @ResponseBody
    public TaotaoResult importIndex() {
        return searchItemServiceImpl.importItemToIndex();
    }

    @RequestMapping("/import-index")
    public String importIndexPage() {
        return "import-index";
    }
}
