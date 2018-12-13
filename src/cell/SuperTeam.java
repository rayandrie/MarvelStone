/**
 * 
 */
package cell;

import java.util.List;

import model.Card;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class SuperTeam {

	private String name;
	private String teamImagePath;
	
	public SuperTeam(String name, String teamImagePath) {
		this.name = name;
		this.teamImagePath = "/logos/" + teamImagePath;
	}
	
	// Copy Constructor
	public SuperTeam(SuperTeam st) {
		this.name = st.name;
		this.teamImagePath = st.teamImagePath;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the teamImagePath
	 */
	public String getTeamImagePath() {
		return teamImagePath;
	}

	/**
	 * @param teamImagePath the teamImagePath to set
	 */
	public void setTeamImagePath(String teamImagePath) {
		this.teamImagePath = teamImagePath;
	}

}
