package com.lufei.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.db.*;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.db.meta.MetaUtil;
import com.lufei.properties.DbProperties;
import com.lufei.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作管理
 *
 * @author kun.li
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private DbProperties dbProperties;

    /**
     * 获取所有的表名称
     *
     * @return
     */
    @GetMapping("selectTables")
    public ResponseResult selectTables() {
        SimpleDataSource simpleDataSource = new SimpleDataSource(
                dbProperties.getUrl(), dbProperties.getUsername(),
                dbProperties.getPassword(), dbProperties.getDriverClassName()
        );
        List<String> tables = MetaUtil.getTables(simpleDataSource);
        return ResponseResult.success(tables);

    }

    @GetMapping("executedSql")
    public ResponseResult executedSql(String sql, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        SimpleDataSource simpleDataSource = new SimpleDataSource(
                dbProperties.getUrl(), dbProperties.getUsername(),
                dbProperties.getPassword(), dbProperties.getDriverClassName()
        );
        Db use = DbUtil.use(simpleDataSource);
        try {
            PageResult<Entity> page = use.page(sql, new Page(currentPage - 1, pageSize));
            if (CollUtil.isNotEmpty(page)) {
                map.put("fieldNames", page.get(0).getFieldNames());
                map.put("resultData", page);
                map.put("total", page.getTotal());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseResult.success(map);

    }
}
