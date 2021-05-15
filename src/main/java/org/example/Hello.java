package org.example;

import org.flywaydb.core.Flyway;

public class Hello {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://yikang.pub:3306/mybatis?characterEncoding=utf-8", "root", "asdf;lkj").load();
//        flyway.clean();
        flyway.migrate();
    }
}
