package com.dncoyote.FlightService.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dncoyote.FlightService.model.Flight;
import com.dncoyote.FlightService.service.FlightService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final RestTemplate restTemplate;

    @Override
    public Flight findCheapestFlight(String departureCity, String arrivalCity) {
        String url = "https://6098f0d799011f001713fbf3.mockapi.io/techcurators/products/flights";
        Flight[] flights = restTemplate.getForObject(url, Flight[].class);

        // Filter flights based on departureCity and arrivalCity
        List<Flight> filteredFlights = Arrays.stream(flights)
                .filter(flight -> flight.getDepartureFrom().equalsIgnoreCase(departureCity) &&
                        flight.getArrivalAt().equalsIgnoreCase(arrivalCity))
                .collect(Collectors.toList());

        // Find the cheapest flight from the filtered list
        if (!filteredFlights.isEmpty()) {
            Optional<Flight> cheapestFlight = filteredFlights.stream()
                    .min(Comparator.comparingInt(Flight::getFlightTicketPrice));
            return cheapestFlight.orElse(null);
        }

        return null; // No flights found between the given cities
    }

    @Override
    public int findFlights(String departureCity, String arrivalCity) {
        String url = "https://6098f0d799011f001713fbf3.mockapi.io/techcurators/products/flights";
        Flight[] flights = restTemplate.getForObject(url, Flight[].class);

        // Count flights based on departureCity and arrivalCity
        long flightCount = Arrays.stream(flights)
                .filter(flight -> flight.getDepartureFrom().equalsIgnoreCase(departureCity) &&
                        flight.getArrivalAt().equalsIgnoreCase(arrivalCity))
                .count();

        return (int) flightCount;
    }

    @Override
    public int getFlightCount(String takeoffTimestamp, String takeoffCity) {
        String url = "https://6098f0d799011f001713fbf3.mockapi.io/techcurators/products/flights";
        Flight[] flights = restTemplate.getForObject(url, Flight[].class);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(takeoffTimestamp);
        } catch (ParseException e) {
            // Handle parsing exception if needed
            return 0;
        }

        long flightCount = Arrays.stream(flights)
                .filter(flight -> {
                    Date flightTakeoffDate = flight.getTakeoffTimestamp();
                    if (flightTakeoffDate == null) {
                        return false;
                    }
                    // Convert flightTakeoffDate to LocalDate for comparison
                    Date flightDateWithoutTime = removeTimeComponent(flightTakeoffDate);
                    Date parsedDateWithoutTime = removeTimeComponent(parsedDate);
                    return flightDateWithoutTime.equals(parsedDateWithoutTime) &&
                            flight.getDepartureFrom().equalsIgnoreCase(takeoffCity);
                })
                .count();

        return (int) flightCount;

    }

    private Date removeTimeComponent(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            // Handle parsing exception if needed
            return null;
        }
    }

}
