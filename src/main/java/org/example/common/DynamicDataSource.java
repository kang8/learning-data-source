package org.example.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    @Override
    public void setDefaultTargetDataSource(Object defaultDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> dataSources) {
        super.setTargetDataSources(dataSources);
        DynamicDataSourceContextHolder.addDataSourceKeys(dataSources.keySet());
    }
}
