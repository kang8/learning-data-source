# 如何在 Spring Boot 项目中使用配置多数据源，使用 MyBatis

## 如何实现

Spring-jdbc 中，有一个 `AbstractRoutingDataSource` 类，实现多数据源只需要继承该类，重写 `determineCurrentLookupKey()` 方法即可。其中 `determineCurrentLookupKey()` 返回的值(String) 就是我们已经设置好的 DataSource Bean，它就会自动切换数据源。

最后在 MyBatis 配置中加上实现 `AbstractRoutingDataSource` 的类即可。