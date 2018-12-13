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
public enum PowerUp {
	HEALTH_REGEN(5),
	MANA_UP(2),
	HEALTH_ATTACK(5);
	
	private int multiplier;
	
	public int getMultiplier() {
		return multiplier;
	}
	
	private PowerUp(int multiplier) {
		this.multiplier = multiplier;
	}
}
