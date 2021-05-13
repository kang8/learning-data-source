package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("datasource.customize.url"));
        dataSourceBuilder.username(env.getProperty("datasource.customize.username"));
        dataSourceBuilder.password(env.getProperty("datasource.customize.password"));

        return dataSourceBuilder.build();
    }
}
