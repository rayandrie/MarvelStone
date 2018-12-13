/**
 * 
 */
package cell;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Player;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class PlayerCell extends ListCell<Player> {

	private HBox row;
	private Label name;
	private ImageView teamImage;
	
	public PlayerCell() {
		row = new HBox(10);
		name = new Label();
		row.getChildren().add(name);
	}

	@Override
	public void updateItem(Player item, boolean empty) {
		// Need to call the super first
		super.updateItem(item, empty);
		// Set the text and graphic for the cell
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			makeRow(item);
			setGraphic(row);
		}
	}
	
	private void makeRow(Player p) {
		// Set the album name and date
		name.setText(p.getUserName() + " " 
				+ p.getPlayerStats().getWinRateString());
	}
	
}
