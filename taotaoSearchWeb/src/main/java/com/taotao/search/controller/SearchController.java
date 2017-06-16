package com.taotao.search.controller;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * 搜索服务Controller
 * Created by 孙建荣 on 17-6-16.上午11:31
 */
@Controller
public class SearchController {

    @Value("${SEARCH_RESULT_ROWS}")
    private Integer ITEM_ROWS;

    private final SearchService searchServiceImpl;

    @Autowired
    public SearchController(SearchService searchServiceImpl) {
        this.searchServiceImpl = searchServiceImpl;
    }

    /**
     * 进入搜索页面
     *
     * @param queryString 查询参数
     * @param page        显示第几页
     * @param model       模型数据
     * @return 带查询条件的搜索页面
     */
    @RequestMapping("/search.html")
    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model) throws UnsupportedEncodingException {
        // 解决前端页面乱码问题
        queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
//        queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
        SearchResult result = searchServiceImpl.search(queryString, page, ITEM_ROWS);
        // 传递给页面
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", result.getPageCount());
        model.addAttribute("itemList", result.getItemList());
        model.addAttribute("page", page);
        // 返回逻辑视图
        return "search";
    }


}
