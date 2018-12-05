package com.canxue.mbtest.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by lishuntao on 2018/11/12.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected HttpServletRequest request;

    /**
     * <p>
     * 转换为 bootstrap-table 需要的分页格式 JSON
     * </p>
     *
     * @param page
     *            分页对象
     * @return
     */
    protected String jsonPage(IPage<?> page) {
        JSONObject jo = new JSONObject();
        jo.put("total", page.getTotal());
        jo.put("rows", page.getRecords());
        return toJson(jo);
    }
    protected String toJson(Object object) {
        return JSON.toJSONString(object, new SerializerFeature[]{SerializerFeature.BrowserCompatible});
    }


    protected <T> Page<T> getPage(int size) {
        int _size = size, _index = 1;
        if (request.getParameter("_size") != null) {
            _size = Integer.parseInt(request.getParameter("_size"));
        }
        if (request.getParameter("_index") != null) {
            int _offset = Integer.parseInt(request.getParameter("_index"));
            _index = _offset / _size + 1;
        }
        return new Page<T>(_index, _size);
    }
    protected <T> Page<T> getPage() {
        return this.getPage(10);
    }


}
