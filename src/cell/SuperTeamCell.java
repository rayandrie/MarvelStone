/**
 * 
 */
package cell;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class SuperTeamCell extends ListCell<SuperTeam> {

	private HBox row;
	private Label teamName;
	private ImageView teamImage;
	
	public SuperTeamCell() {
		row = new HBox(10);
		teamName = new Label();
		teamImage = new ImageView();
		row.getChildren().addAll(teamName);
	}

	@Override
	public void updateItem(SuperTeam item, boolean empty) {
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
	
	private void makeRow(SuperTeam st) {
		// Set the album picture
		teamImage.setImage(new Image(st.getTeamImagePath()));
		shrinkImage(teamImage);
		// Set the album name and date
		teamName.setText(st.getName());
		teamName.setGraphic(teamImage);
	}
	
	private void shrinkImage(ImageView i) {
		i.setFitHeight(40);
		i.setFitWidth(40);
		i.setPreserveRatio(true);
	}
}
