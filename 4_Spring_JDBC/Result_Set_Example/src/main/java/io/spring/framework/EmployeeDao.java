package io.spring.framework;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("select * from employee", rs -> {
            List<Employee> list = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setSalary(rs.getInt(3));
                list.add(employee);
            }
            return list;
        });
    }
}
