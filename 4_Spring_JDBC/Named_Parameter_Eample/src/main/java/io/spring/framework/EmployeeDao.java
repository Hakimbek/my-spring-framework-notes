package io.spring.framework;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeeDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Employee employee) {
        String query = "insert into employee values (:id, :name, :salary)";

        Map<String, Object> map = new HashMap<>();
        map.put("id", employee.getId());
        map.put("name", employee.getName());
        map.put("salary", employee.getSalary());

        jdbcTemplate.execute(query, map, new PreparedStatementCallback() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
