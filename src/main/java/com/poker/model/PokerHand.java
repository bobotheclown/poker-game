package com.poker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHand implements Comparable<PokerHand>{
	Ranking rank;
	List<PlayingCard> cards;
	int highCardRank2;
	int highCardRank;
	boolean hasPair;
	boolean hasTwoPairs;
	boolean hasThreeOfKind;
	boolean isFourOfKind;
	boolean isFlush;
	boolean isStraight;
	
	
	public PokerHand() {};
	
	public PokerHand(List<PlayingCard> cards) {
		this.cards = cards;
	}
	
	public Ranking getRank() {
		return rank;
	}
	
	/*public void setRank(Ranking rank) {
		this.rank = rank;
	}*/
	
	public List<PlayingCard> getCards() {
		return cards;
	}
	
	public void setCards(List<PlayingCard> cards) {
		this.cards = cards;
	}

	public int getHighCardRank() {
		return highCardRank;
	}

	public int getHighCardRank2() {
		return highCardRank2;
	}
	
	public void setHighCardRank(int highCardRank) {
		this.highCardRank = highCardRank;
	}

	public List<PlayingCard> getPairs(List<PlayingCard> remainingCards) {
		List<PlayingCard> pairs = new ArrayList<PlayingCard>();

		//Collections.sort(remainingCards, Collections.reverseOrder());
		for(int i = 0; (i + 1) < remainingCards.size(); i++) {
			PlayingCard currentCard = remainingCards.get(i);
			if(remainingCards.get(i).compareTo(remainingCards.get(i+1)) == 0) {
				pairs.add(currentCard);
				pairs.add(remainingCards.get(i+1));
				break;
			}
		}
		return pairs;
	}


	public List<PlayingCard> getThreeOfKind(List<PlayingCard> remainingCards) {
		List<PlayingCard> trips = new ArrayList<>();
		//Collections.sort(remainingCards, Collections.reverseOrder());
		
		for(int i = 0; (i + 2) < remainingCards.size(); i++) {
			PlayingCard currentCard = remainingCards.get(i);
			if(currentCard.compareTo(remainingCards.get(i+1)) == 0 && currentCard.compareTo(remainingCards.get(i+2)) == 0) {
				trips.add(currentCard);
				trips.add(remainingCards.get(i+1));
				trips.add(remainingCards.get(i+2));
				break;
			}
		}
		return trips;
	}


	public List<PlayingCard> getFourOfKind(List<PlayingCard> remaingCards){
		List<PlayingCard> quads = new ArrayList<>();

		//Collections.sort(remaingCards, Collections.reverseOrder());

		for(int i = 0; i + 3 < remaingCards.size(); i++){
			PlayingCard currentCard = remaingCards.get(i);
			if(currentCard.compareTo(remaingCards.get(i+1)) == 0 &&
				currentCard.compareTo(remaingCards.get(i+2)) == 0 &&
				currentCard.compareTo(remaingCards.get(i+3)) == 0){

				quads.add(currentCard);
				quads.add(remaingCards.get(i+1));
				quads.add(remaingCards.get(i+2));
				quads.add(remaingCards.get(i+3));

				break;
			}
		}

		return quads;
	}


	public boolean checkStraight(List<PlayingCard> analyzeCards){
		boolean isCheck = false;

		//Collections.sort()

		PlayingCard currentCard = analyzeCards.get(0);
		if(currentCard.getRank() - 4 == analyzeCards.get(4).getRank()){
			isCheck = true;
			this.highCardRank = currentCard.getRank();
		}

		return isCheck;
	}


	public boolean checkFlush(List<PlayingCard> analyzeCards){
		boolean isCheck = true;
		PlayingCard currentCard = analyzeCards.get(0);
		for(int i = 1; i < analyzeCards.size(); i++){
			if(currentCard.getSuit() != analyzeCards.get(i).getSuit()){
				isCheck = false;
				break;
			}
		}

		if(isCheck){
			this.highCardRank = currentCard.getRank();
		}

		return isCheck;
	}


	public void analyze() {
		List<PlayingCard> analyzeCards = new ArrayList<PlayingCard>(this.cards);
		List<PlayingCard> returnMatch = new ArrayList<>();
		List<PlayingCard> remainingCards = new ArrayList<PlayingCard>(this.cards);

		Collections.sort(analyzeCards,Collections.reverseOrder());

		//Check for Four of Kind and exits if Found
		returnMatch = getFourOfKind(analyzeCards);
		if(this.isFourOfKind = returnMatch.size() == 4){
			this.rank = Ranking.FourOfKind;
			this.highCardRank = returnMatch.get(0).getRank();
			return;
		}

		//Checks Three of Kind and Full House and Exits if Found
		returnMatch = getThreeOfKind(analyzeCards);
		if(this.hasThreeOfKind = returnMatch.size() == 3){
			this.highCardRank = returnMatch.get(0).getRank();

			remainingCards.removeAll(returnMatch);

			returnMatch = getPairs(remainingCards);
			if(this.hasPair = returnMatch.size() == 2){
				this.highCardRank2 = returnMatch.get(0).getRank();
				this.rank = Ranking.FullHouse;
				return;
			}

			this.rank = Ranking.ThreeOfKind;
			return;
		}

		//Checks for Pair and Two Pairs and exits if found
		returnMatch = getPairs(analyzeCards);
		if(this.hasPair = returnMatch.size() == 2){
			this.highCardRank = returnMatch.get(0).getRank();
			remainingCards.removeAll(returnMatch);

			returnMatch = getPairs(remainingCards);
			if(this.hasTwoPairs = returnMatch.size() == 2){
				this.highCardRank2 = returnMatch.get(0).getRank();
				this.rank = Ranking.TwoPair;
				return;
			}

			this.rank = Ranking.OnePair;
			return;
		}

		//Checks for Straight Flush or Straight or Flush and exits if found
		//High Card is set inside function
		this.isStraight = checkStraight(analyzeCards);
		this.isFlush = checkFlush(analyzeCards);

		if(this.isStraight && this.isFlush){
			this.rank = Ranking.StraightFlush;
			return;
		}

		if(this.isStraight){
			this.rank = Ranking.Straight;
			return;
		}

		if(this.isFlush){
			this.rank = Ranking.Flush;
			return;
		}

		this.highCardRank = analyzeCards.get(0).getRank();
		this.rank = Ranking.HighCard;

	}
	
	@Override
	public int compareTo(PokerHand hand) {		
		Integer myRank = new Integer(this.getRank().ordinal());
		Integer myHighCard = new Integer(this.getHighCardRank());
		Integer myHighCard2 = new Integer(this.getHighCardRank2()); 
		
		Integer otherRank = new Integer(hand.getRank().ordinal());
		Integer otherHighCard = new Integer(hand.getHighCardRank());
		Integer otherHighCard2 = new Integer(hand.getHighCardRank2()); 
		
		
		return myRank.compareTo(otherRank) != 0 ? myRank.compareTo(otherRank) : myHighCard.compareTo(otherHighCard) != 0 ? 
													myHighCard.compareTo(otherHighCard) : myHighCard2.compareTo(otherHighCard2);   
	}
	
	

}
