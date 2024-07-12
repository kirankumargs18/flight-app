package com.globallogic.controller;

import com.globallogic.entity.Booking;
import com.globallogic.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking-service")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingByBookingId(id));
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.addBooking(booking), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBookingByBookId(id, booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        String message = "Booking with ID : " + id + " has been removed";
        bookingService.deleteBooking(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/passengers/{id}")
    public ResponseEntity<List<Booking>> retrieveBookingsByPassengerId(@PathVariable long id) {
        return new ResponseEntity<>(bookingService.getAllBookingsByPassengerId(id), HttpStatus.OK);
    }
}
