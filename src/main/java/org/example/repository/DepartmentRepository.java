package org.example.repository;

import org.example.entity.Department;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll();

    @Select("SELECT VERSION()")
    public String getMySQLVersion();

    Department findById(Long id);

    Department findByIdUseAssociationQuery(Long id);

    int insert(Department department);

    int update(Department department);

    int delete(Long id);
}
