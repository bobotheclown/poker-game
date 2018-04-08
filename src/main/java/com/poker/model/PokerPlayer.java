package com.poker.model;

public class PokerPlayer {
	String name;
	PokerHand hand;
	
	public PokerPlayer(String name) {
		this.name = name;
	}
	
	public PokerPlayer(String name, PokerHand hand) {
		this.name = name;
		this.hand = hand;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PokerHand getHand() {
		return hand;
	}
	public void setHand(PokerHand hand) {
		this.hand = hand;
	}
	
}
