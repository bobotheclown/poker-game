package com.poker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poker.model.Stats;

public interface StatsRepository extends JpaRepository<Stats, Long> {

}
