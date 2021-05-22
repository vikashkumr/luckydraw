package com.grofers.luckydraw.controllers;

import com.grofers.luckydraw.entity.Event;
import com.grofers.luckydraw.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add_event")
    public String addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @GetMapping("/next_event")
    public Optional<Event> getNextEvent() {
        return eventService.getNextEvent();
    }

    @GetMapping("/next_all_events")
    public List<Event> getAllFutureEvents() {
        return eventService.getAllFutureEvents();
    }

}
