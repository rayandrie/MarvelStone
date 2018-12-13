/**
 * 
 */
package help;

import java.util.List;

import javafx.scene.text.Font;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class FindFonts {

	/**
	 * 
	 */
	public FindFonts() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		List<String> fonts = Font.getFamilies();
		System.out.println("Fonts:");
		for (int i = 0; i < fonts.size(); i++) {
			System.out.println(fonts.get(i));
		}
	}
}
