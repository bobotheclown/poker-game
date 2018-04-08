package com.poker.model;

public class PlayingCard implements Comparable<PlayingCard> {
	private int rank;
	private CardSuit suit;
	private String cardImageSVG;
	
	public PlayingCard() {};
	
	public PlayingCard(int rank, CardSuit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public CardSuit getSuit() {
		return suit;
	}
	
	public void setSuit(CardSuit suit) {
		this.suit = suit;
	}
	
	public String getCardImageSVG() {
		String cardImageSVG = "";
		if(this.rank == 11) {
			cardImageSVG += "J";
		}
		else if(this.rank == 12) {
			cardImageSVG += "Q";
		}
		else if(this.rank == 13) {
			cardImageSVG += "K";
		}
		else if(this.rank == 14) {
			cardImageSVG += "A";
		}
		else {
			cardImageSVG += this.rank;
		}
		
		cardImageSVG += this.suit.toString().substring(0,1);
		cardImageSVG += ".svg";
		
		return cardImageSVG;
	}
	
	//Overrides CompareTo
	@Override
	public int compareTo(PlayingCard a) {
		Integer x = new Integer(this.rank);
		Integer y = new Integer(a.rank);
		
		return x.compareTo(y);
	}



	
}
