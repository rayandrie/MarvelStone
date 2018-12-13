/**
 * 
 */
package model;

import java.text.DecimalFormat;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class Stats {

	// Private Variables
	private IntegerProperty gamesPlayed;
	private IntegerProperty gamesWon;
	private StringProperty record;
	private DoubleProperty winRate;
	private StringProperty winRateString;
	
	private final DecimalFormat df = new DecimalFormat("##.#");
		
	public Stats() {
		this.gamesPlayed = new SimpleIntegerProperty(0);
		this.gamesWon = new SimpleIntegerProperty(0);
		this.record = new SimpleStringProperty("0-0-0");
		this.winRate = new SimpleDoubleProperty(0);
		this.winRateString = new SimpleStringProperty("0.0%");
	}
	
	/**
	 * @return the gamesPlayed Prop
	 */
	public IntegerProperty getGamesPlayedProp() {
		return gamesPlayed;
	}
	
	/**
	 * @return the gamesPlayed
	 */
	public int getGamesPlayed() {
		return gamesPlayed.get();
	}

	/**
	 * @param gamesPlayed the gamesPlayed to set
	 */
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed.set(gamesPlayed);
	}
	
	/**
	 * @return the gamesWon Prop
	 */
	public IntegerProperty getGamesWonProp() {
		return gamesWon;
	}

	/**
	 * @return the gamesWon
	 */
	public int getGamesWon() {
		return gamesWon.get();
	}

	/**
	 * @param gamesWon the gamesWon to set
	 */
	public void setGamesWon(int gamesWon) {
		this.gamesWon.set(gamesWon);
	}
	
	/**
	 * @return the record prop
	 */
	public StringProperty getRecordProp() {
		return record;
	}

	/**
	 * @return the record
	 */
	public String getRecord() {
		return record.get();
	}

	/**
	 * @param record the record to set
	 */
	public void setRecord(String record) {
		this.record.set(record);
	}

	/**
	 * @param winRate the winRate to set
	 */
	public void setWinRateProp(DoubleProperty winRate) {
		this.winRate = winRate;
	}
	
	public void setWinRate(double winRate) {
		this.winRate.set(winRate);
	}

	public DoubleProperty getWinRateProp() {
		return winRate;
	}
	
	public double getWinRate() {
		return winRate.get();
	}

	/**
	 * @return the winRateString
	 */
	public StringProperty getWinRateStringProp() {
		return winRateString;
	}
	
	public String getWinRateString() {
		return winRateString.get();
	}

	/**
	 * @param winRateString the winRateString to set
	 */
	public void setWinRateString(double winrate) {
		String result = df.format(winrate);
		winRateString.set(result + "%");
	}
}
