package com.grofers.luckydraw.repository;

import com.grofers.luckydraw.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    List<Participation> findDistinctByEventId(Integer eventId);
}
