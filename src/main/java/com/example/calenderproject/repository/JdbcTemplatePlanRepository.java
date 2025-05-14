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
import java.time.LocalDate;
import java.util.*;

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

    /*
    @Override
    public List<PlanResponseDto> findAllPlans() {
        return jdbcTemplate.query("select * from plan", planRowMapper());
    }
    */

    @Override
    public List<PlanResponseDto> findPlans(String modifiedDate, String writer) {
        String sql = "select * from plan where 1=1";
        List<Object> params = new ArrayList<>();

        if(modifiedDate !=null && !modifiedDate.isEmpty()){
            sql += " and DATE(modifiedDate) = ?";
            params.add(LocalDate.parse(modifiedDate));
        }
        if(writer != null && !writer.isEmpty()){
            sql += " and writer = ?";
            params.add(writer);
        }

        sql += " order by modifiedDate desc";

        return jdbcTemplate.query(sql, planRowMapper(), params.toArray());
    }



    private RowMapper<PlanResponseDto> planRowMapper(){
        return new RowMapper<PlanResponseDto>() {
            @Override
            public PlanResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlanResponseDto(
                        rs.getLong("id"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        "",
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("modifiedDate").toLocalDateTime()
                );
            }
        };
    }

    @Override
    public Optional<Plan> findPlanById(Long id) {
        List<Plan> result = jdbcTemplate.query("select * from plan where id =?", planRowMapperV2(id), id);
        return result.stream().findAny();
    }

    private RowMapper<Plan> planRowMapperV2(Long id){
        return new RowMapper<Plan>() {

            @Override
            public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new Plan( rs.getLong("id"), rs.getString("writer"), rs.getString("contents"),"",rs.getTimestamp("createdDate").toLocalDateTime(), rs.getTimestamp("modifiedDate").toLocalDateTime());
            }
        };
    }

    /* lv2 미완성

    @Override
    public PlanResponseDto updateWriterOrContents(Long id, String modifiedWriter, String modifiedContents) {
        String sql = "select * from plan where 1=1";
        List<Object> params = new ArrayList<>();

        if(modifiedDate !=null && !modifiedDate.isEmpty()){
            sql += " and DATE(modifiedDate) = ?";
            params.add(LocalDate.parse(modifiedDate));
        }
        if(writer != null && !writer.isEmpty()){
            sql += " and writer = ?";
            params.add(writer);
        }

        sql += " order by modifiedDate desc";

        return jdbcTemplate.query(sql, planRowMapper(), params.toArray());


        return null;
    }

    @Override
    public int deletePlan(Long id, String password) {
        if(password == )

        return 0;
    }
    */

}
