package com.scheduler.dto;

import com.scheduler.Schedule;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Integer scheduleId;
    private Integer userId;
    private String todo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String content;
    private LocalDate date;

    public ScheduleResponseDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.userId = schedule.getUserId();
        this.todo =schedule.getTodo();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
//        this.updatedAt = schedule.getUpdatedAt();
        this.date = schedule.getDate();
    }

    public ScheduleResponseDto(Integer scheduleId, Integer userId, String todo, String content, LocalDateTime createdAt,  LocalDate date){
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.todo = todo;
        this.content = content;
        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
        this.date = date;
    }
}