package com.grofers.luckydraw.service;

import com.grofers.luckydraw.entity.Event;
import com.grofers.luckydraw.entity.Participation;
import com.grofers.luckydraw.entity.User;
import com.grofers.luckydraw.repository.EventRepository;
import com.grofers.luckydraw.repository.ParticipationRepository;
import com.grofers.luckydraw.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    private static final Logger logger= LoggerFactory.getLogger(GameService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private EventRepository eventRepository;

    public String participateInEvent(Integer userId, Integer eventId) {
        try {
            User user = userRepository.findUserByUserId(userId);

            //handle condition where user does have or not the raffle ticket
            if(Objects.nonNull(user) && user.getNoOfRaffleTicket() > 0) {
                participationRepository.save(new Participation(userId, eventId));

                //decr no of raffle ticket user has by 1
                user.setNoOfRaffleTicket(user.getNoOfRaffleTicket() - 1);
                userRepository.save(user);

                logger.info("user : {}  has been successfully participated in event : {}", userId, eventId);
                return "user : " + userId + " has been successfully participated in event " + eventId;
            }
            return "user does not have raffle ticket";
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("ERROR_IN participateInEvent has userId: {} and eventId: {}", userId, eventId);
            return "failed to participate";
        }
    }

    public String buyRaffleTicket(Integer userId) {
        try {
            User user = userRepository.findUserByUserId(userId);
            int ticket_count = user.getNoOfRaffleTicket();
            user.setNoOfRaffleTicket(ticket_count + 1);
            userRepository.save(user);
            logger.info("TICKET_BOUGHT_SUCCESSFULLY_FOR userId: {}", userId);
            return "raffle ticket successfully bought for userId: " + userId;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("ERROR_IN buyRaffleTicket userId: {}", userId);
            return "failed to buy raffle ticket for user" + userId;
        }
    }

    public Optional<User> getWinnerForEvent(Integer eventId) {
        try {
            Event event = eventRepository.findByEventId(eventId);
            if(Objects.nonNull(event.getWinnerId())) {
                //if event has already occurred simply returning winner
                return Optional.ofNullable(userRepository.findUserByUserId(event.getWinnerId()));
            }

            List<Participation> participantsForCurrentEvent = participationRepository.findDistinctByEventId(eventId);

            if(participantsForCurrentEvent.size() == 0) {
                return Optional.empty();
            }
            int mn = 0, mx = participantsForCurrentEvent.size() - 1;
            int range = mx - mn + 1;

            //getting random user to declare winner
            int winningParticipantIndex = (int) (Math.random() * range) + mn;

            //assigning winnerId to event
            event.setWinnerId(participantsForCurrentEvent.get(winningParticipantIndex).getUserId());
            User winner = userRepository.findUserByUserId(participantsForCurrentEvent.get(winningParticipantIndex).getUserId());
            if(Objects.nonNull(winner)) {
                //setting date-time of winning
                winner.setWinningDateTime(LocalDateTime.now());
                userRepository.save(winner);
                winner.setPassword("NA"); // hiding password
            }
            return Optional.ofNullable(winner);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("ERROR_IN getWinnerForEvent Service having eventId: {}", eventId);
            return Optional.empty();
        }

    }

    public List<User> getAllWinnersInLastWeek() {
        List<User> winnerListInLastWeek = userRepository.findAllByWinningDateTimeBetween(LocalDateTime.now().minusDays(7), LocalDateTime.now());
        winnerListInLastWeek.forEach(winner -> winner.setPassword("NA"));
        return winnerListInLastWeek;
    }
}
