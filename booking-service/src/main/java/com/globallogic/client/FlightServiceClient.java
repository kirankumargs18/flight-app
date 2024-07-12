package com.globallogic.client;

import com.globallogic.payload.Flight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "flight-service", url = "http://localhost:8082/api/v1/flight-service")
public interface FlightServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<Flight> getFlightById(@PathVariable long id);
}
