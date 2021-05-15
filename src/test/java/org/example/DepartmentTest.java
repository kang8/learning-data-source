package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Department;
import org.example.mapper.DepartmentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    private DepartmentMapper departmentMapper;

    @BeforeEach
    void setUp() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
    }

    @Test
    void testFindAll() {
        List<Department> departmentList = departmentMapper.findAll();
        departmentList.forEach(System.out::println);
    }

}