package org.example.service.impl;

import org.example.entity.Department;
import org.example.mapper.DepartmentMapper;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department get(String id) {
        return departmentMapper.get(id);
    }
}
