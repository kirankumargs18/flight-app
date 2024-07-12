package com.globallogic.kafka;

import com.globallogic.model.EmailRequest;
import com.globallogic.payload.BookingEvent;
import com.globallogic.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingEventConsumer {

    @Autowired
    private EmailService emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingEventConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    private void consumeBookingEvent(BookingEvent bookingEvent) {

        LOGGER.info(String.format("Booking event received in notification-service => %s", bookingEvent.toString()));

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(bookingEvent.getEmail());
        emailRequest.setSubject("Successfully Booked A Flight");
        emailRequest.setBody(bookingEvent.getBookingDTO().toString());
        emailService.sendEmail(emailRequest);
    }
}
