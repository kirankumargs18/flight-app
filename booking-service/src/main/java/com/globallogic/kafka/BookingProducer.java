package com.globallogic.kafka;

import com.globallogic.payload.BookingEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingProducer.class);

    @Autowired
    private NewTopic topic;

    @Autowired
    private KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public void sendMessage(BookingEvent bookingEvent) {

        LOGGER.info(String.format("Booking event => %s", bookingEvent.toString()));

        Message<BookingEvent> bookingEventMessage = MessageBuilder.withPayload(bookingEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(bookingEventMessage);
    }
}
