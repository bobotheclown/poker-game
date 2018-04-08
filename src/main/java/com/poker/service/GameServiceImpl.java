package com.poker.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poker.model.DeckOfCards;
import com.poker.model.PlayingCard;
import com.poker.model.PokerHand;
import com.poker.model.Stats;
import com.poker.repositories.StatsRepository;
import com.poker.model.PokerPlayer;

@Service
public class GameServiceImpl implements GameService {
	
	private StatsRepository statsRepository;
	
	Map<Long, PokerPlayer> playerMap = new LinkedHashMap<Long, PokerPlayer>();
	
	public GameServiceImpl(StatsRepository statsRepository) {
		this.statsRepository = statsRepository;
	}
	
	@Override
	public void startGame() {
		DeckOfCards deck = new DeckOfCards();
		deck.Create();
		deck.Shuffle();
		
		List<Stats> statList = statsRepository.findAll();
		for(Stats stats : statList) {
			List<PlayingCard> cards = deck.Deal();			
			PokerPlayer player = new PokerPlayer(stats.getName(), new PokerHand(cards));
			
			playerMap.put(stats.getId(), player);
		}
	}
	
	@Override
	public Map<Long, PokerPlayer> getPlayers(){
		return this.playerMap;
	}
	
	@Override
	public PokerPlayer getWinner() {
		Map<Long, PokerPlayer> winnerPlayerMap = new LinkedHashMap<Long, PokerPlayer>(playerMap);
		PokerPlayer winner = winnerPlayerMap.values().iterator().next();
		
		for(Map.Entry<Long, PokerPlayer> entry : winnerPlayerMap.entrySet()) {
			
		}
		
		return winner;
	}

}
