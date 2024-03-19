package com.dncoyote.FlightService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dncoyote.FlightService.model.Flight;
import com.dncoyote.FlightService.service.FlightService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/flightservice")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights/cheapest_flights/{departureCity}/{arrivalCity}")
    public Flight getCheapestFlight(@PathVariable String departureCity, @PathVariable String arrivalCity) {
        return flightService.findCheapestFlight(departureCity, arrivalCity);
    }

    @GetMapping("/flights/find_flights/{departureCity}/{arrivalCity}")
    public int getFlightCount(@PathVariable String departureCity, @PathVariable String arrivalCity) {
        return flightService.findFlights(departureCity, arrivalCity);
    }

    @GetMapping("/flights/getFlight/{takeoffTimestamp}/{takeoffCity}")
    public int getFlightCountByTimestampAndCity(@PathVariable String takeoffTimestamp,
            @PathVariable String takeoffCity) {
        return flightService.getFlightCount(takeoffTimestamp, takeoffCity);
    }

}
