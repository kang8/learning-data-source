package org.example.repository;

import org.example.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Long id);

    int insert(User department);

    int update(User department);

    int delete(Long id);
}
