package com.tenniscourts.reservations;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationScheduleChecker {

    private final ReservationRepository reservationRepository;

    // assuming there is something that puts reservation in PLAYED status after being played
    @Scheduled(cron = "0 0 * * * *")
    public void checkNoShows() {
        List<Reservation> pastReservationsNotPlayedOut = reservationRepository.findByScheduleEndDateTimeLessThanAndReservationStatusEquals(LocalDateTime.now(), ReservationStatus.READY_TO_PLAY);
        pastReservationsNotPlayedOut.forEach(r -> r.setRefundValue(BigDecimal.ZERO));
        reservationRepository.saveAll(pastReservationsNotPlayedOut);
    }

}
