package com.poker.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poker.model.Stats;
import com.poker.repositories.StatsRepository;

@Service
public class PokerStatsServiceImpl implements PokerStatsService {
	
	@Autowired
	private StatsRepository statsRepository;
	
	@Override
	public List<Stats> getAllStats(){
		return statsRepository.findAll();
	}
}
