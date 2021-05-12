package mapper;

import entity.Department;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {
    List<Department> findAll();

    @Select("SELECT VERSION()")
    public String getMySQLVersion();
}
