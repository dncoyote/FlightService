package com.dncoyote.FlightService.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @JsonProperty("flightID")
    private Long flightID;

    @JsonProperty("takeoff_timestamp")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date takeoffTimestamp;

    @JsonProperty("landing_timestamp")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date landingTimestamp;

    @JsonProperty("departure_from")
    private String departureFrom;

    @JsonProperty("arrival_at")
    private String arrivalAt;

    @JsonProperty("flight_ticket_price")
    private int flightTicketPrice;
}
