package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Department;
import org.example.mapper.DepartmentMapper;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    private static SqlSession sqlSession;


    @BeforeAll
    static void setup() throws IOException {
        setupFlyWay();
        setupMyBatis();
    }

    private static void setupFlyWay() {
        Flyway flyway = Flyway.configure().dataSource(
                "jdbc:mysql://yikang.pub:3306/mybatis?characterEncoding=utf-8",
                "root",
                "asdf;lkj").load();
        flyway.clean();
        flyway.migrate();
    }

    private static void setupMyBatis() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);

        sqlSession = sqlSessionFactory.openSession();
//        departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
    }

    @Test
    void testFindAll() {
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        List<Department> departmentList = departmentMapper.findAll();
        departmentList.forEach(System.out::println);
    }

    @Test
    void testGetMySALVerSION() {
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        String mySQLVersion = departmentMapper.getMySQLVersion();
        assertEquals("8.0.23", mySQLVersion);
    }
}