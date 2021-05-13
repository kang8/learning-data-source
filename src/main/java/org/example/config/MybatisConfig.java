package org.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.common.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class MybatisConfig {
    @Autowired
    private Environment env;

    @Bean("master")
    @Primary
    public DataSource master() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("datasource.master.url"));
        dataSourceBuilder.username(env.getProperty("datasource.master.username"));
        dataSourceBuilder.password(env.getProperty("datasource.master.password"));

        return dataSourceBuilder.build();
    }

    @Bean("slave")
    public DataSource slave() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("datasource.slave.url"));
        dataSourceBuilder.username(env.getProperty("datasource.slave.username"));
        dataSourceBuilder.password(env.getProperty("datasource.slave.password"));

        return dataSourceBuilder.build();
    }

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        HashMap<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master());
        dataSourceMap.put("slave", slave());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultDataSource(master());
        dynamicDataSource.setDataSource(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
