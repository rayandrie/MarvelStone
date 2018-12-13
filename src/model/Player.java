/**
 * 
 */
package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class Player {

	// Private Variables
	private IntegerProperty healthPoints;
	private final int START_HEALTH = 30;
	private IntegerProperty mana;
	private final int START_MANA = 3;
	private List<Card> deck; // to draw from (max 15)
	private final int MAX_DECK_SIZE = 15;
	private List<Card> hand; // player's hand (max 5)
	private final int MAX_HAND_SIZE = 5;
	private List<Card> field; // player's field (max 3)
	private final int MAX_FIELD_SIZE = 3;
	private String userName;
	private String userImagePath;
	private Stats playerStats;
	
	private Random random;
	private List<String> deadPeeps;
	
	public Player(String userName, String userImagePath) {
		this.healthPoints = new SimpleIntegerProperty(START_HEALTH);
		this.mana = new SimpleIntegerProperty(START_MANA);
		this.deck = new ArrayList<Card>();
		this.hand = new ArrayList<Card>();
		this.field = new ArrayList<Card>();
		this.userName = userName;
		this.userImagePath = userImagePath;
		this.playerStats = new Stats();
		this.random = new Random();
		this.deadPeeps = new ArrayList<String>();
	}
	
	public Player(String userName, String userImagePath, int gamesPlayed,
			int gamesWon, String record) {
		this(userName, userImagePath);
		playerStats.setGamesPlayed(gamesPlayed);
		playerStats.setGamesWon(gamesWon);
		playerStats.setRecord(record);
		double result = ((double) gamesWon / gamesPlayed) * 100.0;
		playerStats.setWinRateString(result);
	}
	
	/*
	 * Shuffle deck of Cards
	 */
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
	
	/*
	 * Reset mana for player. 
	 * Used for every turn
	 */
	public void resetPlayerMana() {
		this.mana.set(START_MANA);
	}
	
	/*
	 * Draw 5 cards, and put it in your hand. 
	 * This function is only called at the start to
	 * draw 5 cards at once. Hand must be empty.
	 */
	public List<Card> drawFiveCards() {
		if (deck.isEmpty()) {
			return null;
		}
		if (!hand.isEmpty()) {
			return null;
		}
		for (int i = 0; i < 5; i++) {
			Card drawnCard = deck.remove(deck.size() - 1);
			hand.add(drawnCard);
		}
		return hand;
	}
	
	/*
	 * Draw A Card from the deck, and put it in your hand.
	 * If deck is empty, do nothing and return null.
	 * If hand is full, return null
	 * If user's mana is 0, return null
	 * Update mana after drawing a card
	 */
	public Card drawCard() {
		System.out.println("User Hand Size: " + hand.size());
		if (deck.isEmpty()) {
			return null;
		}
		if (hand.size() == MAX_HAND_SIZE) {
			return null;
		}
		if (mana.get() == 0) {
			return null;
		}
		Card drawnCard = deck.remove(deck.size() - 1);
		hand.add(drawnCard);
		int currMana = mana.get();
		mana.set(--currMana);
		return drawnCard;
	}
	
	/*
	 * Play A Card from your hand, put it on the field
	 * If hand is empty, return false
	 * If field is full, return false
	 * If the card being played is not a Hero, return false
	 * Update mana after playing a card
	 */
	public Card playPlayerCard(Image image, Player opponent) {
		if (hand.isEmpty()) {
			return null;
		}
		if (field.size() == MAX_FIELD_SIZE) {
			return null;
		}
		if (mana.get() == 0) {
			return null;
		}
		Card playedCard = null;
		for (int i = 0; i < hand.size(); i++) {
			Image currImage = new Image(hand.get(i).getCardImagePath());
			if (checkImageEquality(image, currImage)) {
				playedCard = hand.get(i);
				break;
			}
		}
		if (playedCard == null) {
			return null;
		}
		hand.remove(playedCard);
		int currMana = mana.get();
		mana.set(--currMana);
		if (playedCard.getClass() != HeroCard.class) {
			System.out.println("IS A POWER CARD!");
			playPowerUp((PowerCard) playedCard, opponent);
		} else { // A hero card
			field.add(playedCard);
		}
		return playedCard;
	}
	
	/*
	 * Play Opponent Card. Takes 1 Mana
	 */
	public boolean playOpponentCard(HeroCard playedCard) {
		if (hand.isEmpty()) {
			return false;
		}
		if (field.size() == MAX_FIELD_SIZE) {
			return false;
		}
		if (mana.get() == 0) {
			return false;
		}
		hand.remove(playedCard);
		int currMana = mana.get();
		mana.set(--currMana);
		field.add(playedCard);
		return true;
	}
	
	/*
	 * Play the powerUp Card in your hand
	 */
	public void playPowerUp(PowerCard pc, Player opponent) {
		PowerUp pu = pc.getPowerUpType();
		if (pu.equals(PowerUp.HEALTH_REGEN)) {
			regenPlayerHealth(pu.getMultiplier());
		} else if (pu.equals(PowerUp.MANA_UP)) {
			regenPlayerMana(pu.getMultiplier());
		} else { // Health Attack
			int resultHealth = opponent.getHealthPoints() 
				- pu.getMultiplier();
			opponent.setHealthPoints(resultHealth);
		}
	}
	
	/*
	 * Check if player has died.
	 */
	public boolean isDead() {
		if (healthPoints.get() <= 0) {
			return true;
		}
		return false;
	}
	
	/* 
	 * Attack the opponent himself/herself.
	 * Get the index of the Player's Field ImageView (0-2) from the
	 * View to find the heroCard chosen to launch the attack.
	 * This takes 2 mana. If player has not enough mana, return false.
	 * Returns true if changes successfully made. This means that in the
	 * front-end, we need to check whether the opponent has died or not.
	 */
	public boolean attackOpponent(Player opponent, int heroChosen) {
		if (mana.get() < 2) {
			return false;
		}
		HeroCard myHero = (HeroCard) field.get(heroChosen);
		int resultHealth = opponent.getHealthPoints() - myHero.getDamage();
		opponent.setHealthPoints(resultHealth);
		int currMana = mana.get();
		currMana -= 2;
		mana.set(currMana);
		return true;
	}
	
	/* Get index of hero with highest damage in 
	 * the player's field
	 */
	public int getStrongestHero() {
		int index = 0;
		HeroCard hc = (HeroCard) field.get(0);
		for (int i  = 1; i < field.size(); i++) {
			HeroCard curr = (HeroCard) field.get(i);
			if (curr.getDamage() > hc.getDamage()) {
				index = i;
				hc = curr;
			}
		}
		return index;
	}
	
	/* Get index of hero with highest health in
	 * the player's field
	 */
	public int getHealthiestHero() {
		int index = 0;
		HeroCard hc = (HeroCard) field.get(0);
		for (int i  = 1; i < field.size(); i++) {
			HeroCard curr = (HeroCard) field.get(i);
			if (curr.getHealth() > hc.getHealth()) {
				index = i;
				hc = curr;
			}
		}
		return index;
	}
	
	/*
	 * Attack opponent directly, with health given.
	 * This function will be used if there is spillover
	 * damage to the opponent
	 */
	public boolean attackOpponent(Player opponent, int heroChosen, 
			int amount) {
		if (mana.get() < 2) {
			return false;
		}
//		HeroCard myHero = (HeroCard) field.get(heroChosen);
		int resultHealth = opponent.getHealthPoints() - amount;
		opponent.setHealthPoints(resultHealth);
		int currMana = mana.get();
		currMana -= 2;
		mana.set(currMana);
		return true;
	}
	
	
	/*
	 * Attack the opponent's heroes.
	 * Get the index of the Player's Field ImageView (0-2) from the
	 * View to find the heroCard chosen to launch the attack. Get
	 * the index of the opponent Field ImageView (0-2) and the 
	 * Opponent Player too. The attack can either just damage the 
	 * opponent heroCard chosen, kill the opponent heroCard completely,
	 * or kill the card + damage other cards and/or damage the opponent.
	 * If it damages other cards, they are chosen at random.
	 * This takes 1 mana. If player has not enough mana, return false.
	 * Returns true if change has been made. This means that in the front-end, 
	 * we have to check the opponent's field and remove the cards that have
	 * been killed.
	 */
	public boolean attackHero(Player opponent, int heroChosen, int heroToAttack) {
		if (mana.get() == 0) {
			return false;
		}
		// Need to check that the player's heroChosen is valid
		if (heroChosen < 0 || heroChosen >= field.size()) {
			return false;
		}
		// Need to check that the opponent's heroToAttack is valid
		if (heroToAttack < 0 || heroToAttack >= opponent.getField().size()) {
			return false;
		}
		HeroCard myHero = (HeroCard) field.get(heroChosen);
		HeroCard oppHero = (HeroCard) opponent.getField().get(heroToAttack);
		int res = myHero.attackHero(oppHero); // if res is (+), that is the leftover damage to deal
		if (res >= 0) {
			// Need to remove the opponent's card from the field
			opponent.getField().remove(heroToAttack);
			opponent.getDeadPeeps().add(oppHero.getCardImagePath());
		}
		while (res > 0) {
			// Attack other cards on the opponent's field. If no other cards exist
			// on the opponent's field, attack the opponent himself/herself
			if (opponent.getField().isEmpty()) {
				// Attack the Opponent Player's HP
				boolean attacked = attackOpponent(opponent, heroChosen, res);
				if (!attacked) {
					int currMana = mana.get();
					mana.set(--currMana);
				}
				return true;
			}
			int oppFieldSize = opponent.getField().size();
			// Get random number from 0 to last index of field
//			System.out.println("Opp Field Size: " + oppFieldSize);
			int randIndex;
			if (oppFieldSize <= 1) {
				randIndex = 0;
			} else {
				randIndex = random.nextInt(oppFieldSize - 1);
			}
			HeroCard toAttack = (HeroCard) opponent.getField().get(randIndex);
			res = myHero.attackHero(toAttack, res);
			if (res >= 0) {
				opponent.getField().remove(toAttack);
				opponent.getDeadPeeps().add(toAttack.getCardImagePath());
			}
		}
		int currMana = mana.get();
		mana.set(--currMana);
		return true;
	}
	
	/*
	 * Increase the health of the Player.
	 * Set the cap as the starting health
	 */
	public void regenPlayerHealth(int amount) {
		int hp = healthPoints.get();
		hp += amount;
		if (hp > START_HEALTH) {
			hp = START_HEALTH;
		}
		healthPoints.set(hp);
	}
	
	/*
	 * Increase the mana of the Player.
	 * Set the cap as the starting mana
	 */
	public void regenPlayerMana(int amount) {
		int currMana = mana.get();
		currMana += amount;
		if (currMana > START_MANA) {
			currMana = START_MANA;
		}
		mana.set(currMana);
	}

	/**
	 * @return the healthPoints
	 */
	public IntegerProperty getHealthPointsProp() {
		return healthPoints;
	}
	
	/**
	 * @return the healthPoints in Integer form
	 */
	public int getHealthPoints() {
		return healthPoints.get();
	}

	/**
	 * @param healthPoints the healthPoints to set
	 */
	public void setHealthPoints(int healthPoints) {
		this.healthPoints.set(healthPoints);
	}

	/**
	 * @return the mana
	 */
	public IntegerProperty getManaProp() {
		return mana;
	}
	
	/**
	 * @return the mana in Integer form
	 */
	public int getMana() {
		return mana.get();
	}

	/**
	 * @param mana the mana to set
	 */
	public void setMana(int mana) {
		this.mana.set(mana);
	}

	/**
	 * @return the deck
	 */
	public List<Card> getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	/**
	 * @return the hand
	 */
	public List<Card> getHand() {
		return hand;
	}

	/**
	 * @param hand the hand to set
	 */
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	/**
	 * @return the playingCards
	 */
	public List<Card> getField() {
		return field;
	}

	/**
	 * @param playingCards the playingCards to set
	 */
	public void setField(List<Card> playingCards) {
		this.field = playingCards;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userImagePath
	 */
	public String getUserImagePath() {
		return userImagePath;
	}

	/**
	 * @param userImagePath the userImagePath to set
	 */
	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}

	/**
	 * @return the playerStats
	 */
	public Stats getPlayerStats() {
		return playerStats;
	}

	/**
	 * @param playerStats the playerStats to set
	 */
	public void setPlayerStats(Stats playerStats) {
		this.playerStats = playerStats;
	}
	
	/**
	 * @return the deadPeeps
	 */
	public List<String> getDeadPeeps() {
		return deadPeeps;
	}

	/**
	 * @param deadPeeps the deadPeeps to set
	 */
	public void setDeadPeeps(List<String> deadPeeps) {
		this.deadPeeps = deadPeeps;
	}

	private boolean checkImageEquality(Image im1, Image im2) {
		for (int i = 0; i < im1.getWidth(); i++) {
			for (int j = 0; j < im1.getHeight(); j++) {
				if (im1.getPixelReader().getArgb(i, j) != im2.getPixelReader().getArgb(i, j)) {
					return false;
				}
			}
		}
		return true;
	}
	
}
