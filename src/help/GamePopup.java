/**
 * 
 */
package help;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class GamePopup {

	public GamePopup() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static Popup createPopup(final String message) {
	    final Popup popup = new Popup();
	    popup.setAutoFix(true);
	    popup.setAutoHide(true);
	    popup.setHideOnEscape(true);
	    Label label = new Label(message);
	    label.setOnMouseReleased(event -> {
	    	popup.hide();
	    });
	    label.setPadding(new Insets(10));
	    label.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	    label.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
	    label.getStylesheets().add("/controller/application.css");
	    label.getStyleClass().add("popup");
	    popup.getContent().add(label);
	    return popup;
	}

	public static void showPopupMessage(final String message, final Stage stage, 
			final int paneWidth, final int paneHeight) {
	    final Popup popup = createPopup(message);
	    popup.setOnShown(event -> {
	    	popup.setX(paneWidth / 2);
            popup.setY(paneHeight / 2);
	    });        
	    popup.show(stage);
	}

}
