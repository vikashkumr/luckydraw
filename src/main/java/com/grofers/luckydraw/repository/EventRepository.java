package com.grofers.luckydraw.repository;

import com.grofers.luckydraw.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findEventsByEventDateTimeIsAfter(LocalDateTime currentTime);

    Event findEventByEventDateTimeAfterOrderByEventDateTimeAsc(LocalDateTime currentTime);

    Event findByEventId(Integer eventId);

}
