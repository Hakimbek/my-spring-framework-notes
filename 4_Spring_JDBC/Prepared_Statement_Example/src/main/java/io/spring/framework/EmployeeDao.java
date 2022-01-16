package io.spring.framework;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean saveEmployeeByPreparedStatement(final Employee employee) {
        String query = "insert into employee values(?,?,?)";
        return jdbcTemplate.execute(query, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setDouble(3, employee.getSalary());

            return ps.execute();
        });
    }
}
