package com.grofers.luckydraw.controllers;

import com.grofers.luckydraw.entity.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @PostMapping("/add_event")
    public String addEvent(@RequestBody Event event) {

    }

    @GetMapping("/next_event")
    public Optional<Event> getNextEvent() {

    }

    @GetMapping("/next_all_events")
    public List<Event> getAllFutureEvent() {

    }

}
