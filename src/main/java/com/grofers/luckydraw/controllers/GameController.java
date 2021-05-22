package com.grofers.luckydraw.controllers;

import com.grofers.luckydraw.entity.User;
import com.grofers.luckydraw.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/participate")
    public String participateInEvent(@RequestParam Integer userId, @RequestParam Integer eventId) {
        return gameService.participateInEvent(userId, eventId);
    }

    @GetMapping("/buy_raffle_ticket")
    public String buyRaffleTicket(@RequestParam Integer userId) {
        return gameService.buyRaffleTicket(userId);
    }

    @GetMapping("/get_winner")
    public Optional<User> getLuckyWinnerForEvent(@RequestParam Integer eventId) {
        return gameService.getWinnerForEvent(eventId);
    }

    @GetMapping("/get_winners")
    public List<User> getAllWinnersInLastWeek() {
        return gameService.getAllWinnersInLastWeek();
    }

}
