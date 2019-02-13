package com.zoy.springboot.service.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by zouzp on 2019/2/12.
 */
//@Configuration // ·@Configuration定义 配置类，可以替代 xml文件。用于加载Bean
public class MutiDataSourceConfig {

    /**
     * ·多数据源的 Bean注入
     * @return
     */
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary") // ·而这些Bean的值从 application.properties中来
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondDataSource")
    @Qualifier("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second") // ·而这些Bean的值从 application.properties中来
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * ·获取多数据源DataSource，并封装为 JdbcTemplate
     * @param dataSource
     * @return
     */
    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /* ·这个是为了 UserServiceImpl.java中 @Autowired @Qualifier("jdbcTemplate")
    *  ·不至于直接
    *   ·@Autowired
    *   ·private JdbcTemplate jdbcTemplate
    *   ·会直接报错*/
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}