package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 索引库维护
 * Created by 孙建荣 on 17-6-15.下午9:13
 */
@Controller
public class IndexManagerController {
    private final SearchItemService searchItemServiceImpl;

    @Autowired
    public IndexManagerController(SearchItemService searchItemServiceImpl) {
        this.searchItemServiceImpl = searchItemServiceImpl;
    }

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
