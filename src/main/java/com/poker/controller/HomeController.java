package com.poker.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poker.repositories.StatsRepository;
import com.poker.model.Stats;


@RestController
@RequestMapping("api/")
public class HomeController {
	
	@Autowired
	private StatsRepository statsRepository;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "players", method = RequestMethod.GET)
	public Map<String,List<Stats>> list() {
		LinkedHashMap<String,List<Stats>> map = new LinkedHashMap<>();
		map.put("players", statsRepository.findAll());
		return map;
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "pokerplayer", method = RequestMethod.POST)
	public Stats addPlayer(@RequestBody Stats stats) {
		return statsRepository.saveAndFlush(stats);
	}

}
