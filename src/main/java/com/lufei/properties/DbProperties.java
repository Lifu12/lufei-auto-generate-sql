package com.lufei.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kun.li
 */
@Data
@ConfigurationProperties(DbProperties.DB_PREFIX)
@Component
public class DbProperties {
    public static final String DB_PREFIX = "db";
    /**
     * 地址
     */
    private String url;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 驱动类
     */
    private String driverClassName;
    /**
     * 数据库类型
     */
    private String dbType;
}
