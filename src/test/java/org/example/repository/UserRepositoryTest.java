package org.example.repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Department;
import org.example.entity.User;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
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
    void findAllLazy() {
        // give
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);

        ArrayList<Object> expectUserList = new ArrayList<>();
        expectUserList.add(new User(1L, "阿熊", 18, LocalDateTime.parse("2003-08-08T10:00:00"),
                new Department(2L, "开发部", "123")));
        expectUserList.add(new User(2L, "老狗", 30, LocalDateTime.parse("1991-02-20T15:27:20"),
                new Department(4L, "运维部", "456")));

        // when
        List<User> userList = userRepository.findAllLazy();
        userList.forEach(System.out::println);

        // then
        assertEquals(expectUserList, userList);
    }

    @Test
    void findAll() {
        // give
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);

        ArrayList<Object> expectUserList = new ArrayList<>();
        expectUserList.add(new User(1L, "阿熊", 18, LocalDateTime.parse("2003-08-08T10:00:00"),
                new Department(2L, "开发部", null)));
        expectUserList.add(new User(2L, "老狗", 30, LocalDateTime.parse("1991-02-20T15:27:20"),
                new Department(4L, "运维部", null)));

        // when
        List<User> userList = userRepository.findAll();
        userList.forEach(System.out::println);

        // then
        assertEquals(expectUserList, userList);
    }

    @Test
    void findById() {
        // give
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);

        User expectUser = new User(1L, "阿熊", 18, LocalDateTime.parse("2003-08-08T10:00:00"),
                new Department(2L, "开发部", "123"));

        // when
        User user = userRepository.findById(1L);

        // then
        assertEquals(expectUser, user);
    }

    @Test
    void insert() {
        // give
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);

        User user = new User(null, "飞机", 33, LocalDateTime.parse("2020-01-23T11:45:23"),
                new Department(2L, null, null));

        // when
        int insertBackValue = userRepository.insert(user);

        // then
        assertEquals(1, insertBackValue);

        // clean
        userRepository.delete(3L);
    }

    @Test
    void update() {
        // give
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
        User user = new User(1L, "飞机", 33, LocalDateTime.parse("2020-01-23T11:45:23"),
                new Department(3L, null, null));

        // when
        int updateBackValue = userRepository.update(user);

        // then
        assertEquals(1, updateBackValue);

        //clean
        userRepository.update(
                new User(1L, "阿熊", 18, LocalDateTime.parse("2003-08-08T10:00:00"),
                        new Department(2L, null, null)));
    }

    @Test
    void delete() {
        // give
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);

        // when
        int deleteBackValue = userRepository.delete(1L);

        // then
        assertEquals(1, deleteBackValue);

        // clean
        userRepository.insert(
                new User(1L, "阿熊", 18, LocalDateTime.parse("2003-08-08T10:00:00"),
                        new Department(2L, null, null)));
    }

    @AfterAll
    static void afterAll() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}