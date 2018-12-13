/**
 * 
 */
package model;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class PowerCard extends Card {

	// Private Variables
	public PowerUp powerUpType;
	
	public PowerCard(PowerUp powerUpType, String cardName, String cardImagePath) {
		super(cardName, cardImagePath);
		this.powerUpType = powerUpType;
	}
	
	// Copy Constructor
	public PowerCard(PowerCard pc) {
		super(pc.cardName, pc.cardImagePath);
		this.powerUpType = pc.powerUpType;
	}

	/**
	 * @return the powerUpType
	 */
	public PowerUp getPowerUpType() {
		return powerUpType;
	}

	/**
	 * @param powerUpType the powerUpType to set
	 */
	public void setPowerUpType(PowerUp powerUpType) {
		this.powerUpType = powerUpType;
	}
}
