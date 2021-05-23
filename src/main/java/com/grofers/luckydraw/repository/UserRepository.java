package com.grofers.luckydraw.repository;

import com.grofers.luckydraw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserId(Integer userId);

    List<User> findAllByWinningDateTimeBetween(LocalDateTime lastWeekDateTime, LocalDateTime currentDateTime);

    @Query(value = "update users set ticket_count = ticket_count - 1 where user_id in (:userIds)", nativeQuery = true)
    void updateRaffleTicketCountForParticipatedUsers(List<Integer> userIds);

}
