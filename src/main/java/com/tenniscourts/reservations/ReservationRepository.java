package com.tenniscourts.reservations;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findBySchedule_Id(Long scheduleId);

    List<Reservation> findByReservationStatusAndSchedule_StartDateTimeGreaterThanEqualAndSchedule_EndDateTimeLessThanEqual(ReservationStatus reservationStatus, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Reservation> findByScheduleStartDateTimeGreaterThanAndScheduleEndDateTimeLessThan(LocalDateTime minusDays, LocalDateTime now);

    // can be improved to not look-up the entire history
    List<Reservation> findByScheduleEndDateTimeLessThanAndReservationStatusEquals(LocalDateTime dateTime, ReservationStatus status);

//    List<Reservation> findByStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndTennisCourt(LocalDateTime startDateTime, LocalDateTime endDateTime, TennisCourt tennisCourt);
}
