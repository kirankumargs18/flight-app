package com.globallogic.client;


import com.globallogic.payload.Passenger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "passenger-service", url = "http://localhost:8083/api/v1/passenger-service")
public interface PassengerServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<Passenger> getPassengerById(@PathVariable long id);
}
