package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/tennis-courts/schedule")
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ApiOperation("Create schedule for a given tennis court")
    public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @GetMapping("/by-dates")
    @ApiOperation("Finds available schedules based on date")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate startDate,
                                                                  @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @GetMapping("/{id}")
    @ApiOperation("Finds schedule based on id")
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable("id") Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
