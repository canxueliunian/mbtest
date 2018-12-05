package com.canxue.mbtest.service.impl;

import com.canxue.mbtest.entity.Student;
import com.canxue.mbtest.mapper.StudentMapper;
import com.canxue.mbtest.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author canxue
 * @since 2018-11-30
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
