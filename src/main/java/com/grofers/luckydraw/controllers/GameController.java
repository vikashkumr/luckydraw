package com.grofers.luckydraw.controllers;

import com.grofers.luckydraw.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @PostMapping("/participate")
    public String participateInEvent(@RequestParam Integer userId, @RequestParam Integer eventId) {

    }

    @GetMapping("/get_raffle_ticket")
    public String getRaffleTicket(@RequestParam Integer userId) {

    }

    @GetMapping("/get_winner")
    public Optional<User> getLuckyWinnerForEvent(@RequestParam Integer eventId) {

    }

    @GetMapping("/get_winners")
    public List<User> getAllWinnersInLastWeek() {

    }

}
