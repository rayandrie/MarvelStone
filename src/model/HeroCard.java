/**
 * 
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class HeroCard extends Card {
	// Private Variables
	private IntegerProperty health;
	private IntegerProperty damage;
	
	public HeroCard(int health, int damage, String cardName, String cardImagePath) {
		super(cardName, cardImagePath);
		this.health = new SimpleIntegerProperty(health);
		this.damage = new SimpleIntegerProperty(damage);
	}
	
	// Copy Constructor
	public HeroCard(HeroCard hc) {
		super(hc.cardName, hc.cardImagePath);
		this.health = hc.health;
		this.damage = hc.damage;
	}
	
	/*
	 * Attacks an opponent hero.
	 * Returns negative number if only damaged opponent, not fatal
	 * Returns 0 if attack fatally kills, but no add. damage
	 * Returns positive number if fatally kills, and has add. damage
	 * to other opponents
	 */
	public int attackHero(HeroCard opponent) {
		System.out.println("Opponent Health: " + opponent.getHealth());
		System.out.println("Your Damage: " + this.damage.get());
		int resultHealth = opponent.getHealth() - this.damage.get();
		opponent.setHealth(resultHealth);
		if (resultHealth == 0) {
			return 0;
		} else if (resultHealth < 0) {
			resultHealth *= -1;
			return resultHealth;
		} else { // resultHealth is positive
			return -1;
		}
	}
	
	public int attackHero(HeroCard opponent, int amount) {
		int resultHealth = opponent.getHealth() - amount;
		opponent.setHealth(resultHealth);
		if (resultHealth == 0) {
			// Opponent dead, that's it
			return 0;
		} else if (resultHealth < 0) {
			// Opponent dead, with spill-over damage
			resultHealth *= -1;
			return resultHealth;
		} else {
			// Opponent stays alive
			return -1;
		}
	}
	
	// Checks if HeroCard is dead
	public boolean isDead() {
		if (health.get() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return the health
	 */
	public IntegerProperty getHealthProp() {
		return health;
	}
	
	/**
	 * @return the health in Integer form
	 */
	public int getHealth() {
		return health.get();
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health.set(health);
	}

	/**
	 * @return the damage
	 */
	public IntegerProperty getDamageProp() {
		return damage;
	}
	
	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage.get();
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage.set(damage);
	}

	
}
