package com.poker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.poker.model.CardSuit;
import com.poker.model.PlayingCard;
import com.poker.model.PokerHand;
import com.poker.model.PokerPlayer;
import com.poker.model.Ranking;
import com.poker.model.Stats;
import com.poker.repositories.StatsRepository;
import com.poker.service.GameService;
import com.poker.service.GameServiceImpl;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameTest {
	
	@Autowired StatsRepository statsRepository;

	@Test
	public void startGame() {
		statsRepository.save(new Stats(3L,"Brandon C"));
		
		GameService game = new GameServiceImpl(statsRepository);
		game.startGame();
		assertNotNull(game);
		
		Map<Long, PokerPlayer> playerMap = game.getPlayers();
		assertTrue(playerMap.size() > 0);
		
		PokerHand hand = playerMap.get(1L).getHand();
		assertTrue(hand.getCards().size() > 0);
		for(int i = 0; i < 5; i++) {
			System.out.println(hand.getCards().get(i).getCardImageSVG());
		}
		
	}
	
	@Test
	public void CheckPokerHandAnalysis() {
		PlayingCard aceClub = new PlayingCard(14,CardSuit.Club);
		PlayingCard kingClub = new PlayingCard(13,CardSuit.Club);
		PlayingCard queenClub = new PlayingCard(12,CardSuit.Club);
		PlayingCard jackClub = new PlayingCard(11,CardSuit.Club);
		PlayingCard tenClub = new PlayingCard(10,CardSuit.Club);
		PlayingCard nineClub = new PlayingCard(9,CardSuit.Club);
		
		
		PlayingCard sixClub = new PlayingCard(6,CardSuit.Club);
		PlayingCard sixHeart = new PlayingCard(6,CardSuit.Heart);
		PlayingCard sixDiamond = new PlayingCard(6,CardSuit.Diamond);
		PlayingCard sixSpade = new PlayingCard(6,CardSuit.Spade);
		
		PlayingCard nineDiamond = new PlayingCard(9,CardSuit.Diamond);
		PlayingCard nineSpade = new PlayingCard(9,CardSuit.Spade);
		PlayingCard nineHeart = new PlayingCard(9,CardSuit.Heart);

		PlayingCard tenDiamond = new PlayingCard(10,CardSuit.Diamond);
		PlayingCard tenSpade = new PlayingCard(10,CardSuit.Spade);
		PlayingCard tenHeart = new PlayingCard(10,CardSuit.Heart);
		
		//Testing Straight Flush
		ArrayList<PlayingCard> newHand = new ArrayList<PlayingCard>();
		Collections.addAll(newHand, new PlayingCard[] {aceClub,kingClub,queenClub,jackClub,tenClub});
		PokerHand straightFlush = new PokerHand(newHand);
		straightFlush.analyze();
		
		assertEquals(straightFlush.getRank(),Ranking.StraightFlush);
		assertEquals(straightFlush.getHighCardRank(),14);
		assertNotEquals(straightFlush.getRank(),Ranking.Straight);
		
		//Testing Flush
		newHand.set(4, sixClub);
		PokerHand flush = new PokerHand(newHand);
		flush.analyze();
		assertNotEquals(flush.getRank(),Ranking.StraightFlush);
		assertEquals(flush.getHighCardRank(),14);
		assertEquals(flush.getRank(),Ranking.Flush);
		
		//Testing Straight
		newHand.set(4, tenDiamond);
		PokerHand straight = new PokerHand(newHand);
		straight.analyze();
		assertNotEquals("Should Not Be Straight Flush",straight.getRank(),Ranking.StraightFlush);
		assertEquals(straight.getHighCardRank(),14);
		assertEquals("Should be Straight",straight.getRank(),Ranking.Straight);

		//Testing High Card
		newHand.set(4, sixDiamond);
		PokerHand highCard = new PokerHand(newHand);
		highCard.analyze();
		assertEquals(highCard.getRank(),Ranking.HighCard);
		assertEquals(highCard.getHighCardRank(),14);
		
		//Test Four of a Kind
		newHand.clear();
		Collections.addAll(newHand, new PlayingCard[] {nineClub,nineHeart,nineDiamond,nineSpade,sixDiamond});
		PokerHand fourOfKind = new PokerHand(newHand);
		fourOfKind.analyze();
		assertEquals(fourOfKind.getRank(),Ranking.FourOfKind);
		assertEquals(fourOfKind.getHighCardRank(),9);
			
		//Testing FullHouse
		newHand.set(3, sixHeart);		 
		PokerHand fullHouse = new PokerHand(newHand);
		assertNotEquals("Before Analysis Ranking Null",fullHouse.getRank(),Ranking.FullHouse);
		fullHouse.analyze();
		assertEquals("After Analysis Ranking should Equal Full House",fullHouse.getRank(),Ranking.FullHouse);
		assertNotEquals("Should Be Full House Not 3 of a Kind",fullHouse.getRank(),Ranking.ThreeOfKind);
		assertEquals(fullHouse.getHighCardRank(),9);
		assertEquals(fullHouse.getHighCardRank2(),6);
		
		//Test Three of a Kind
		newHand.set(3, tenDiamond);
		PokerHand threeOfKind = new PokerHand(newHand);
		threeOfKind.analyze();
		assertEquals(threeOfKind.getRank(),Ranking.ThreeOfKind);
		assertEquals(threeOfKind.getHighCardRank(),9);
	
		//Test Pair
		newHand.set(2, aceClub);
		PokerHand pair = new PokerHand(newHand);
		pair.analyze();
		assertEquals(pair.getRank(),Ranking.OnePair);
		assertEquals(pair.getHighCardRank(),9);
		
		//Test Two Pair
		newHand.set(2, tenClub);
		PokerHand twoPair = new PokerHand(newHand);
		twoPair.analyze();
		assertEquals(twoPair.getRank(),Ranking.TwoPair);
		assertEquals(twoPair.getHighCardRank(),10);
		assertEquals(twoPair.getHighCardRank2(),9);		
		
		//Test PokerHand Compare
		newHand = new ArrayList<PlayingCard>(straight.getCards());
		newHand.set(0, nineClub);
		PokerHand straightFlushKingHigh = new PokerHand(newHand);
		straightFlushKingHigh.analyze();
		
		newHand = new ArrayList<PlayingCard>(fullHouse.getCards());
		newHand.set(2,sixClub);
		PokerHand fullHouseSixHigh = new PokerHand(newHand);
		fullHouseSixHigh.analyze();
		
		
		
		assertEquals(String.format("Straight Flush Value: %d   Straight Value: %d", straightFlush.getRank().ordinal(),
										straight.getRank().ordinal()),straightFlush.compareTo(straight),1);
		assertEquals(straightFlush.compareTo(straightFlushKingHigh),1);
		assertEquals(fullHouse.compareTo(fullHouseSixHigh),1);
		
		
		
		
	}

}
