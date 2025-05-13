package com.example.calenderproject.repository;

import com.example.calenderproject.dto.PlanResponseDto;
import com.example.calenderproject.entity.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplatePlanRepository implements PlanRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePlanRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PlanResponseDto savePlan(Plan plan) {
       // Insert query 를 직접 작성하지 않아도 된다
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("plan").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", plan.getWriter());
        parameters.put("contents", plan.getContents());
        parameters.put("password", plan.getPassword());
        parameters.put("createdDate", plan.getCreatedDate());
        parameters.put("modifiedDate", plan.getModifiedDate());


        // 저장 후 생성된 key 값을 number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new PlanResponseDto(key.longValue(), plan.getWriter(), plan.getContents(), plan.getPassword(), plan.getCreatedDate(), plan.getModifiedDate());

    }

    @Override
    public List<PlanResponseDto> findAllPlans() {
        return jdbcTemplate.query("select * from plan", planRowMapper());
    }

    private RowMapper<PlanResponseDto> planRowMapper(){
        return new RowMapper<PlanResponseDto>() {
            @Override
            public PlanResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlanResponseDto(
                        rs.getLong("id"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        null,
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("modifiedDate").toLocalDateTime()
                );
            }
        };
    }

    @Override
    public Optional<Plan> findPlanById(Long id) {
        List<Plan> result = jdbcTemplate.query("select * from plan where id =?", planRowMapperV2(), id);
        return result.stream().findAny();
    }

    private RowMapper<Plan> planRowMapperV2(){
        return new RowMapper<Plan>() {

            @Override
            public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {

                // 확인용
                System.out.println(rs.getLong("id") + "  " + rs.getString("writer") + "  " +rs.getString("contents") + rs.getString("modifiedDate"));

                return new Plan(
                        rs.getString("writer"),
                        rs.getString("contents"),
                        null

                );
            }
        };
    }


    @Override
    public Plan findPlanByIdOrElseThrow(Long id) {
        List<Plan> result = jdbcTemplate.query("select * from plan where id =?", planRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exsit id = "+id));
    }




}
