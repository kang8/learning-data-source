package org.example.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.example.entity.Department;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentTypeHandler implements TypeHandler<Department> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Department department, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i, department.getId());
    }

    @Override
    public Department getResult(ResultSet resultSet, String columnName) throws SQLException {
        Department department = new Department();
        System.out.println("getResult columnName: " + columnName);
        department.setId(resultSet.getLong(columnName));
        return department;
    }

    @Override
    public Department getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Department department = new Department();
        System.out.println("getResult columnIndex: " + columnIndex);
        department.setId(resultSet.getLong(columnIndex));
        return department;
    }

    @Override
    public Department getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Department department = new Department();
        System.out.println("getResult callableStatement columnIndex: " + columnIndex);
        department.setId(callableStatement.getLong(columnIndex));
        return department;
    }
}
