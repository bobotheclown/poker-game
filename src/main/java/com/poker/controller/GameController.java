package com.poker.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poker.model.PokerPlayer;
import com.poker.model.Stats;
import com.poker.repositories.StatsRepository;
import com.poker.service.GameService;
import com.poker.service.GameServiceImpl;

@RestController
@RequestMapping("api/")
public class GameController {
	
	@Autowired
	private StatsRepository statsRepository;
	@Autowired
	private GameService game;

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "game", method = RequestMethod.GET)
	public Map<String, Map<Long,PokerPlayer>> list(){
		//GameService game = new GameServiceImpl(statsRepository);
		game.startGame();
		
		Map<String, Map<Long,PokerPlayer>> playerList = new LinkedHashMap<String, Map<Long,PokerPlayer>>();
		playerList.put("players", game.getPlayers());
		
		return playerList;
	}

}
