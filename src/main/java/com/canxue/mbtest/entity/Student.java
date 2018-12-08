package com.canxue.mbtest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.canxue.mbtest.common.annotion.ClumnLike;
import com.canxue.mbtest.common.annotion.ParamMax;
import com.canxue.mbtest.common.annotion.ParamMin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author canxue
 * @since 2018-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Student extends Model<Student> {
    @TableField(exist = false)
    private static final long serialVersionUID
            = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    private Long cardNo;
    /**
     * 姓名
     */
    private String name;

    /**
     * 0 男 ,1 女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;


    /**
     * 辅助字段 年龄min
     */
    @TableField(exist = false)
    @ParamMin("age")
    private Integer ageMin;

    /**
     * 辅助字段 年龄max
     */
    @TableField(exist = false)
    @ParamMax("age")
    private Integer ageMax;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 曾用名
     */
    @ClumnLike
    private String everName;
    @TableLogic
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
