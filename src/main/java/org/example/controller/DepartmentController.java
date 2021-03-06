package org.example.controller;

import org.example.annotation.DataSourceAnnotation;
import org.example.entity.Department;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department/{id}")
    public Department get(@PathVariable String id) {
        return departmentService.get(id);
    }

    @GetMapping("/master/department/{id}")
    @DataSourceAnnotation("master")
    public Department getDepartmentByMaster(@PathVariable String id) {
        return departmentService.get(id);
    }

    @GetMapping("/slave/department/{id}")
    @DataSourceAnnotation("slave")
    public Department getDepartmentBySlave(@PathVariable String id) {
        return departmentService.get(id);
    }

}
