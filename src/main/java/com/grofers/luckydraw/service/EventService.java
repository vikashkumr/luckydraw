package com.grofers.luckydraw.service;

import com.grofers.luckydraw.entity.Event;
import com.grofers.luckydraw.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public String addEvent(Event event) {
        try {
            eventRepository.save(event);
            return "event added successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "even can't be add";
        }
    }

    public Optional<Event> getNextEvent() {
        return Optional.ofNullable(eventRepository.findEventByEventDateTimeAfterOrderByEventDateTimeAsc(LocalDateTime.now()));
    }

    public List<Event> getAllFutureEvents() {
        return eventRepository.findEventsByEventDateTimeIsAfter(LocalDateTime.now());
    }
}
