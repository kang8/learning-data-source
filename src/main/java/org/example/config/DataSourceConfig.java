package org.example.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://121.36.19.76:3306/test?serverTimezone=GMT");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("asdf;lkj");

        return dataSourceBuilder.build();
    }
}
