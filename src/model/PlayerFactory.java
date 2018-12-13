/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class PlayerFactory {
	
	public PlayerFactory() {
		// TODO Auto-generated constructor stub
	}

	public List<Player> getPlayers() {
		List<Player> pList = new ArrayList<Player>();
		pList.add(new Player("Tony Stark", "", 200, 190, "190-10"));
		pList.add(new Player("Steve Rodgers", "", 100, 40, "40-60"));
		pList.add(new Player("Star Lord", "", 150, 111, "111-39"));
		pList.add(new Player("Groot", "", 500, 412, "412-88"));
		pList.add(new Player("Peter Parker", "", 300, 212, "212-88"));
		pList.add(new Player("T'Challa", "", 50, 41, "41-9"));
		pList.add(new Player("Thor", "", 240, 150, "150-90"));
		pList.add(new Player("Bruce Banner", "", 120, 40, "40-80"));
		
		return pList;
	}
}
