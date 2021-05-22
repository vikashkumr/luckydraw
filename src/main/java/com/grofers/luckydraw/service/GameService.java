package com.grofers.luckydraw.service;

import com.grofers.luckydraw.entity.Event;
import com.grofers.luckydraw.entity.Participation;
import com.grofers.luckydraw.entity.User;
import com.grofers.luckydraw.repository.EventRepository;
import com.grofers.luckydraw.repository.ParticipationRepository;
import com.grofers.luckydraw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private EventRepository eventRepository;

    public String participateInEvent(Integer userId, Integer eventId) {
        participationRepository.save(new Participation(userId, eventId));
        return "user : " + userId + "has been successfully participated in event" + eventId;
    }

    public String buyRaffleTicket(Integer userId) {
        try {
            User user = userRepository.findUserByUserId(userId);
            int ticket_count = user.getNoOfRaffleTicket();
            user.setNoOfRaffleTicket(ticket_count + 1);

            return "raffle ticket successfully bought for user" + userId;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "failed to buy raffle ticket for user" + userId;
        }
    }

    public Optional<User> getWinnerForEvent(Integer eventId) {
        try {
            Event event = eventRepository.findByEventId(eventId);
            if(Objects.nonNull(event.getWinnerId())) {
                return Optional.ofNullable(userRepository.findUserByUserId(event.getWinnerId()));
            }

            List<Participation> participantsForCurrentEvent = participationRepository.findDistinctByEventId(eventId);

            if(participantsForCurrentEvent.size() == 0) {
                return Optional.empty();
            }
            int mn = 0, mx = participantsForCurrentEvent.size() - 1;
            int range = mx - mn + 1;

            int winningParticipantIndex = (int) (Math.random() * range) + mn;
            event.setWinnerId(participantsForCurrentEvent.get(winningParticipantIndex).getUserId());
            User winner = userRepository.findUserByUserId(participantsForCurrentEvent.get(winningParticipantIndex).getUserId());
            if(Objects.nonNull(winner)) {
                winner.setWinningDateTime(LocalDateTime.now());
                userRepository.save(winner);
            }
            return Optional.ofNullable(winner);

        }
        catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

    public List<User> getAllWinnersInLastWeek() {
        return userRepository.findAllByWinningDateTimeBetween(LocalDateTime.now().minusDays(7), LocalDateTime.now());
    }
}
