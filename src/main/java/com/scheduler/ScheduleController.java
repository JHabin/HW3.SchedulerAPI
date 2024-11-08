package com.scheduler;

import com.scheduler.dto.ScheduleRequestDto;
import com.scheduler.dto.ScheduleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }
    //일정 등록
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.createSchedule(requestDto);
    }
    //전체 일정 목록 조회
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getScheduleList(){
        return scheduleService.getScheduleList();
    }

    //선택 일정 조회
    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Integer scheduleId){
        return scheduleService.getSchedule(scheduleId);
    }

    //선택 일정 수정
    @PutMapping("/schedules/{scheduleId}")
    public Integer updateSchedule(@PathVariable Integer scheduleId,@RequestBody ScheduleRequestDto scheduleRequestDto){
        return scheduleService.updateSchedule(scheduleId,scheduleRequestDto);
    }

    //선택 일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public Integer deleteSchedule(@PathVariable Integer scheduleId){
        return scheduleService.deleteSchedule(scheduleId);
    }
}