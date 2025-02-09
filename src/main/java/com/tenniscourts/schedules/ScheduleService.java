package com.tenniscourts.schedules;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.tenniscourts.TennisCourtRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TennisCourtRepository tennisCourtRepository;

    private final ScheduleMapper scheduleMapper;

    private final static Long DEFAULT_SLOT_LENGTH_HOURS = 1L;

    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
        Schedule schedule = new Schedule();
        schedule.setStartDateTime(createScheduleRequestDTO.getStartDateTime());
        schedule.setEndDateTime(createScheduleRequestDTO.getStartDateTime().plusHours(DEFAULT_SLOT_LENGTH_HOURS));
        schedule.setTennisCourt(tennisCourtRepository.findById(tennisCourtId).orElseThrow(() -> new EntityNotFoundException("Tennis court does not exist")));
        return scheduleMapper.map(scheduleRepository.save(schedule));
    }

    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        return scheduleMapper.map(scheduleRepository.findByStartDateTimeAfterAndEndDateTimeBefore(startDate, endDate));
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
        return scheduleMapper.map(scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found")));
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
}
