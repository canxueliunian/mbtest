package com.canxue.mbtest.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canxue.mbtest.common.util.DealDataUtil;
import com.canxue.mbtest.common.util.MapUtils;
import com.canxue.mbtest.entity.Book;
import com.canxue.mbtest.entity.Student;
import com.canxue.mbtest.service.BookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.canxue.mbtest.common.BaseController;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author canxue
 * @since 2018-12-02
 */
@RestController
@RequestMapping("/book")
public class BookController extends BaseController {
    @Resource
    BookService bookService;


    /**
     * 普通的分页查询接口
     * name like 王
     * 20=<age<=23
     * book :
     *
     * @param book
     * @return
     */
    @GetMapping("getPage")
    public String getList(Book book) {
        Page<Book> page = getPage();
        QueryWrapper<Book> wrapper = new QueryWrapper<>(book);


//
//        DealDataUtil.DealData(wrapper, book);
        // womenxinjian yige neirong ba dade , ni shuo ni yinggai shi xiangyao yixie zheg de dongxi de a
        IPage<Book> resultPage = bookService.page(page, wrapper);
        logger.info("查询结束");
        return (jsonPage(resultPage));
    }
    /**
     * name eq
     * book == like查新
     *
     */


}

