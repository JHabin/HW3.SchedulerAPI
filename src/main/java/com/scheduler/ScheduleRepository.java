package com.scheduler;

import com.scheduler.dto.ScheduleRequestDto;
import com.scheduler.dto.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;
    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // 일정 저장
    public Schedule save(Schedule schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedules (userId, todo, content, createdAt, password ,date) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(con ->{
            PreparedStatement preparedStatement=con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            LocalDateTime createdAt = schedule.getCreatedAt();
            LocalDate date = schedule.getDate();

            preparedStatement.setInt(1, schedule.getUserId());
            preparedStatement.setString(2, schedule.getTodo());
            preparedStatement.setString(3, schedule.getContent());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(createdAt)); // LocalDateTime을 Timestamp로 변환
            preparedStatement.setString(5, schedule.getPassword());
            preparedStatement.setDate(6, Date.valueOf(date)); // LocalDate를 Date로 변환
            return preparedStatement;
        },keyHolder);

        Integer scheduleId = keyHolder.getKey().intValue();
        schedule.setScheduleId(scheduleId);
        return schedule;
    }
    //선택한 id 조회
    public Schedule findByScheduleId(Integer scheduleId){
        String sql="SELECT * FROM schedules WHERE scheduleId = ?";

        return jdbcTemplate.query(sql,resultSet ->{
            if(resultSet.next()){
                Schedule schedule=new Schedule();
                schedule.setPassword(resultSet.getString("password"));
                schedule.setUserId(resultSet.getInt("userId"));
                schedule.setTodo(resultSet.getString("todo"));
                return schedule;
            }else{
                return null;
            }
        },scheduleId);
    }
    //전체 일정 조회
    public List<ScheduleResponseDto> findAll() {
        String sql="SELECT * FROM schedules";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer scheduleId = rs.getInt("scheduleId");
                Integer userId = rs.getInt("userId");
                String todo = rs.getString("todo");
                String content = rs.getString("content");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
                LocalDate date = rs.getDate("date").toLocalDate();
                return new ScheduleResponseDto(scheduleId, userId, todo, content, createdAt, date);
            }
        });
    }
    //찾기
    public ScheduleResponseDto find(Integer scheduleId) {
        String sql="SELECT * FROM schedules WHERE scheduleId = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getInt("scheduleId"),
                        rs.getInt("userId"),
                        rs.getString("todo"),
                        rs.getString("content"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getDate("date").toLocalDate()
                );
            }
        }, scheduleId);

    }
    //삭제
    public void delete(Integer scheduleId) {
        String sql="DELETE FROM schedules WHERE scheduleId = ?";
        jdbcTemplate.update(sql,scheduleId);
    }
    //수정
    public void updateSchedule(Integer scheduleId, ScheduleRequestDto scheduleRequestDto) {
        String sql="UPDATE schedules SET userId = ?, todo = ? WHERE scheduleId = ?";
        jdbcTemplate.update(sql,scheduleRequestDto.getUserId(),scheduleRequestDto.getTodo(),scheduleId);
    }

}