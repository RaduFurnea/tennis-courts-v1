package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/tennis-courts/reservation")
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @PostMapping("/book")
    @ApiOperation("Create a reservation")
    public ResponseEntity<Void> bookReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get reservation by ID")
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable("id") Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @PutMapping("/{id}/cancel")
    @ApiOperation("Cancel reservation by ID")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable("id") Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @PutMapping("/{reservationId}/reschedule/{scheduleId}")
    @ApiOperation("Reschedule reservation by ID")
    public ResponseEntity<ReservationDTO> rescheduleReservation(@PathVariable("reservationId") Long reservationId, @PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }

    @GetMapping("/history")
    @ApiOperation("Get history of reservations for the past given days")
    public ResponseEntity<List<ReservationDTO>> getReservationHistory(@RequestParam("days") Long days) {
        return ResponseEntity.ok(reservationService.getReservationHistory(days));
    }
}
