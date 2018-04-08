package com.poker.service;

import java.util.Map;

import com.poker.model.PokerPlayer;

public interface GameService {
	public void startGame();
	public Map<Long, PokerPlayer> getPlayers();
	public PokerPlayer getWinner();
}
