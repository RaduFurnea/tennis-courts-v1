package com.tenniscourts.reservations;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class ReservationHistoryDTO {

    List<Reservation> reservations;

    Integer totalResults;

}
