package com.grofers.luckydraw.service;

import com.grofers.luckydraw.entity.Event;
import com.grofers.luckydraw.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private static final Logger logger= LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    public String addEvent(Event event) {
        try {
            eventRepository.save(event);
            return "event added successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("ERROR_IN addEvent SERVICE: {}", e.getMessage());
            return "even can't be add";
        }
    }

    public Optional<Event> getNextEvent() {
        // returns next event
        return Optional.ofNullable(eventRepository.findEventByEventDateTimeAfterOrderByEventDateTimeAsc(LocalDateTime.now()));
    }

    public List<Event> getAllFutureEvents() {
        //returns all future events to be happened
        return eventRepository.findEventsByEventDateTimeIsAfter(LocalDateTime.now());
    }
}
