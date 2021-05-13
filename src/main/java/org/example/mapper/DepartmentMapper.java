package org.example.mapper;

import org.example.entity.Department;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {
    List<Department> findAll();

    Department get(String id);
}
