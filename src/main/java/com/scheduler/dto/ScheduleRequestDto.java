package com.scheduler.dto;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {
    private Integer userId;
    private String todo;
    private String content;
    private String password;
    private LocalDate date;
}