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
public class Card {
	// private variables
	protected String cardImagePath; // Path of Image in src folder
	protected String cardName;
	
	public Card(String cardName, String cardImagePath) {
		this.cardImagePath = cardImagePath;
		this.cardName = cardName;
	}

	/**
	 * @return the cardImagePath
	 */
	public String getCardImagePath() {
		return cardImagePath;
	}

	/**
	 * @param cardImagePath the cardImagePath to set
	 */
	public void setCardImagePath(String cardImagePath) {
		this.cardImagePath = cardImagePath;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @param cardName the cardName to set
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}
