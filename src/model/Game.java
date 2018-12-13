/**
 * 
 */
package model;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
// SINGLETON DESIGN PATTERN
public class Game {
	
	// Private Variables
	private static Game instance;
	private Player p1;
	private Player p2;
	
	private Game() {
		p1 = new Player("Player 1", "");
		p2 = new Player("Player 2", "");
	}
	
	private Game(Player player1, Player player2) {
		this.p1 = player1;
		this.p2 = player2;
	}
	
	public static Game getInstance() {
		if (instance == null) {
			synchronized (Game.class) {
				if (instance == null) {
					instance = new Game();
				}
			}
		}
		return instance;
	}
	
	public static Game getNewInstance() {
		instance = new Game();
		return instance;
	}
	
	public boolean isPlayerDead() {
		return p1.isDead();
	}
	
	public boolean isOpponentDead() {
		return p2.isDead();
	}
	
	// Check if any of player's heroes are dead
	public List<String> findDeadHeroes() {
		return p1.getDeadPeeps();
	}
	
	// Clear the dead people list once they have been 
	// cleared in the front-end
	public void clearDeadHeroes() {
		p1.getDeadPeeps().clear();
	}
	
	// Check if any of opponent's heroes are dead
	public List<String> findDeadVillains() {
		return p2.getDeadPeeps();
	}
	
	// Clear the dead people list once they have been 
	// cleared in the front-end
	public void clearDeadVillains() {
		p2.getDeadPeeps().clear();
	}
	
	// Attack the opponent directly
	public boolean attackOpponentDirectly(ImageView heroImage) {
		// Can only attack directly if no opponent heroes 
		// on the field
		if (p2.getField().size() > 0) {
			return false;
		}
		// Get player's field, then find the hero, then
		// call the attack
		List<Card> playerField = p1.getField();
		// Find the hero
		int heroIndex = findIndexOfCard(playerField, heroImage);
		// Call the attack
		return p1.attackOpponent(p2, heroIndex);
	}
	
	// Attack the opponent's hero
	public boolean attackOpponentHero(ImageView heroImage, 
			ImageView villainImage) {
		// Get player's and opponent's fields, then find 
		// that hero and villain, then call the attack
		List<Card> playerField = p1.getField();
		List<Card> oppField = p2.getField();
		// Find the hero (Must not return -1)
		int heroIndex = findIndexOfCard(playerField, heroImage);
		// Find the villain (Must not return -1)
		int villainIndex = findIndexOfCard(oppField, villainImage);
		// Call the attack
		return p1.attackHero(p2, heroIndex, villainIndex);
	}
	
	private int findIndexOfCard(List<Card> field, ImageView iv) {
		for (int i = 0; i < field.size(); i++) {
			Image curr = new Image(field.get(i).getCardImagePath());
			if (checkImageEquality(iv.getImage(), curr)) {
				return i;
			}
		}
		return -1;
	}
	
	// Enough Mana (1 Mana) - Output to the front-end
	// that there is no mana if returned false
	public boolean enoughMana() {
		System.out.println("MANA: " + p1.getMana());
		if (p1.getMana() >= 1) {
			return true;
		}
		return false;
	}
	
	public Card playCard(Image image) {
		Card playedCard = p1.playPlayerCard(image, p2);
		if (playedCard == null) {
			return null;
		}
		if (playedCard.getClass() == HeroCard.class) {
			System.out.println("Playing Card!");
			return playedCard;
		} else {
			// Power Up Card
			System.out.println("Is a Power Up Card!");
			return null;
		}
	}
	
	// Reset Player's Mana
	public void resetPlayerMana() {
		p1.resetPlayerMana();
	}
	
	// Reset Opponent's Mana
	public void resetOpponentMana() {
		p2.resetPlayerMana();
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
	
	public IntegerProperty getPlayerHealth() {
		return p1.getHealthPointsProp();
	}
	
	public IntegerProperty getPlayerMana() {
		return p1.getManaProp();
	}
	
	public IntegerProperty getOppHealth() {
		return p2.getHealthPointsProp();
	}
	
	public IntegerProperty getOppMana() {
		return p2.getManaProp();
	}

	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return instance.p1;
	}

	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(Player player1) {
		instance.p1 = player1;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return instance.p2;
	}

	/**
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(Player player2) {
		instance.p2 = player2;
	}
	
	

}
