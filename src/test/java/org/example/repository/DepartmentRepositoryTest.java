package org.example.repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Department;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartmentRepositoryTest {
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
    }

    @Test
    void findAll() {
        // give. 前置条件
        DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);

        List<Department> expectedDepartmentList = new ArrayList<>();
        expectedDepartmentList.add(new Department(1L, "全部部门", "-"));
        expectedDepartmentList.add(new Department(2L, "开发部", "123"));
        expectedDepartmentList.add(new Department(3L, "测试产品部", "789"));
        expectedDepartmentList.add(new Department(4L, "运维部", "456"));

        // when. 对应执行
        List<Department> departmentList = departmentRepository.findAll();

        // then. 对应断言
        assertEquals(expectedDepartmentList, departmentList);
    }

    @Test
    void getMySQLVersion() {
        DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
        String mySQLVersion = departmentRepository.getMySQLVersion();
        assertEquals("8.0.23", mySQLVersion);
    }

    @Test
    void findById() {
        // give
        DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);

        Department departmentByI = new Department(1L, "全部部门", "-");

        // when
        Department departmentByQuery = departmentRepository.findById(1L);

        // then
        assertEquals(departmentByI, departmentByQuery);
    }

    @AfterAll
    static void afterAll() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    @Test
    void insert() {
        // give
        DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
        Department addDepartment = new Department(null, "售前部", "321456");

        // when
        int insert = departmentRepository.insert(addDepartment);

        // then
        assertEquals(1, insert);

        // clean
        departmentRepository.delete(5L);
    }

    @Test
    void update() {
        // give
        DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);

        Department updateDepartment = new Department(1L, "全部部门", "18812344321");

        // when
        int update = departmentRepository.update(updateDepartment);

        // then
        assertEquals(1, update);

        // clean
        departmentRepository.update(new Department(1L, "全部部门", "-"));
    }

    @Test
    void delete() {
        // give
        DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);

        // when
        int delete = departmentRepository.delete(1L);

        // then
        assertEquals(1, delete);

        // clean
        departmentRepository.insert(new Department(1L, "全部部门", "-"));
    }
}