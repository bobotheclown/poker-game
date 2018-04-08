package com.poker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class DeckOfCards {
	public List<PlayingCard> gameDeck = new ArrayList<PlayingCard>(52);
	
	public void Create(){
		for(int i = 2; i < 15; i++) {
			for(CardSuit suit : CardSuit.values()) {
				PlayingCard singleCard = new PlayingCard(i,suit);
				gameDeck.add(singleCard);
			}
		}
	}
	
	public void Shuffle() {
		Random rnd = ThreadLocalRandom.current();
		for(int i = 0; i < gameDeck.size() - 1; i++) {
			int index = rnd.nextInt(i + 1);
			
			PlayingCard tempCard = gameDeck.get(index);
			gameDeck.set(index, gameDeck.get(i));
			gameDeck.set(i, tempCard);
		}
	}
	
	public List<PlayingCard> GetDeck(){
		return gameDeck;
	}
	
	public List<PlayingCard> Deal(){
		List<PlayingCard> cards = new ArrayList<PlayingCard>(5);
		if(gameDeck.size() > 5) {
			for(int i = 0; i < 5; i++) {
				cards.add(gameDeck.get(i));
				gameDeck.remove(i);
			}		
		}
		
		return cards;

	}
	
	
}
