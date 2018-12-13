package com.canxue.mbtest.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canxue.mbtest.common.util.DealDataUtil;
import com.canxue.mbtest.entity.Student;
import com.canxue.mbtest.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.canxue.mbtest.common.BaseController;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author canxue
 * @since 2018-11-30
 */
@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Resource
    StudentService studentService;

    /**
     * 普通的分页查询接口
     * 做 age 的区间查询, 查询大于20 小于23的student
     *
     * @param student
     * @return
     */
    @GetMapping("getPage")
    public String getList(Student student) {
        Page<Student> page = getPage();
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        DealDataUtil.DealData(wrapper, student);
        IPage<Student> resultPage = studentService.page(page, wrapper);
        logger.info("查询结束");
        return (jsonPage(resultPage));
    }


    /**
     * 新增接口
     *
     * @param student
     * @return
     */
    @GetMapping("add")
    public String save(Student student) {
        boolean save = studentService.save(student);
        logger.info("插入结束");
        return ("插入结束, 新增值的主键id为" + student.getId());
    }

    /**
     * 修改接口
     *
     * @param student
     * @return
     */
    @GetMapping("update")
    public String update(Student student) {
        boolean b = studentService.updateById(student);
        logger.info("修改结束");
        return ("插入结束, 新增值的主键id为" + student.getId());
    }

    /**
     * 修改接口
     *
     * @return
     */
    @GetMapping("del/{id}")
    public String update(@PathVariable long id) {

        boolean b = studentService.removeById(id);
        logger.info("删除结束");
        return ("删除结果" + b);
    }


}

