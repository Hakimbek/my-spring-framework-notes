package io.spring.framework;

import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveEmployee(Employee employee) {
        String query = "insert into employee values('" + employee.getId() + "', '" + employee.getName() + "', '" + employee.getSalary() + "')";
        return jdbcTemplate.update(query);
    }

    public int updateEmployee(Employee employee) {
        String query = "update employee set name = '" + employee.getName() + "', salary = '" + employee.getSalary() + "' where id = '" + employee.getId() + "' ";
        return jdbcTemplate.update(query);
    }

    public int deleteEmployee(Employee employee) {
        String query = "delete from employee where id='" + employee.getId() + "' ";
        return jdbcTemplate.update(query);
    }
}
