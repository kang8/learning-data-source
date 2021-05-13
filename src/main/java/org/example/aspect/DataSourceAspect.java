package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.annotation.DataSourceAnnotation;
import org.example.common.DynamicDataSourceContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DataSourceAspect {
    @Before("@annotation(dataSource))")
    public void switchDataSource(JoinPoint point, DataSourceAnnotation dataSource) {
        if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value())) {
            System.out.println("DataSource " + dataSource.value() + "doesn't exist, use default DataSource " + DynamicDataSourceContextHolder.getDataSourceKey());
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
            System.out.println("Switch DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                    + "] in Method [" + point.getSignature() + "]");
        }
    }

    @After("@annotation(dataSource))")
    public void restoreDataSource(JoinPoint point, DataSourceAnnotation dataSource) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        System.out.println("Restore DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                + "] in Method [" + point.getSignature() + "]");
    }
}
