package com.canxue.mbtest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author canxue
 * @since 2018-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Book extends Model<Book> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 索书号
     */
    private Long bookNo;

    /**
     * 作者
     */
    private String author;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 类型描述
     */
    private String typeDec;

    /**
     * 入库时间
     */
    private LocalDate entryDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
