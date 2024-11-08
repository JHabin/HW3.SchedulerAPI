package com.scheduler;

import com.scheduler.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Integer scheduleId;
    private Integer userId;
    private String todo;
    private String content;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate date;

    // 초기화
    public Schedule(ScheduleRequestDto scheduleRequestDto){
        this.userId = scheduleRequestDto.getUserId();
        this.todo = scheduleRequestDto.getTodo();
        this.content = scheduleRequestDto.getContent();
        this.password = scheduleRequestDto.getPassword();
        //this.createdAt = scheduleRequestDto.getCreatedAt();
        //this.updatedAt = scheduleRequestDto.getUpdatedAt();
        this.date = scheduleRequestDto.getDate();
    }
}
