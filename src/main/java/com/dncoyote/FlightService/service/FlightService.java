package com.dncoyote.FlightService.service;

import com.dncoyote.FlightService.model.Flight;

public interface FlightService {
    public Flight findCheapestFlight(String departureCity, String arrivalCity);

    public int findFlights(String departureCity, String arrivalCity);

    public int getFlightCount(String takeoffTimestamp, String takeoffCity);
}
