package com.scheduler;

import com.scheduler.dto.ScheduleRequestDto;
import com.scheduler.dto.ScheduleResponseDto;
import java.util.List;

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    //일정 등록
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        //일정 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    //전체 일정 조회
    public List<ScheduleResponseDto> getScheduleList() {
        return scheduleRepository.findAll();
    }

    //선택 일정 조회
    public ScheduleResponseDto getSchedule(Integer scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
        ScheduleResponseDto scheduleResponseDto = scheduleRepository.find(scheduleId);
        return scheduleResponseDto;
    }

    //선택 일정 수정
    public Integer updateSchedule(Integer scheduleId, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);

        System.out.println("scheduleRequestDto=" + scheduleRequestDto.getPassword());
        System.out.println("schedule.getPassword() = " + schedule.getPassword());
        //비밀번호 검사
        if(!schedule.getPassword().equals(scheduleRequestDto.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
        scheduleRepository.updateSchedule(scheduleId,scheduleRequestDto);
        return scheduleId;
    }

    //일정 삭제
    public Integer deleteSchedule(Integer scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
        if(schedule != null){
            scheduleRepository.delete(scheduleId);
            return scheduleId;
        } else{
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다");
        }
    }
}