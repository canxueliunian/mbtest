package com.canxue.mbtest.service.impl;

import com.canxue.mbtest.entity.Book;
import com.canxue.mbtest.mapper.BookMapper;
import com.canxue.mbtest.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author canxue
 * @since 2018-12-02
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
