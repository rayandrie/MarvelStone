/**
 * 
 */
package view;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class StartView {
	
	// Private Variables
	private Pane mainPane;
	private HBox titleBox;
	private Label label;
	private VBox buttonBox;
	private Button playButton;
	private Button instructionsButton;
	
	private GameController controller;
	private Scene gameScene; // scene to hold game
	
	private MediaPlayer mediaPlayer;
	private Media media;
	private final String toPlay = "/media/avengersTheme.mp3";
	
	public StartView(GameController controller) {
		this.controller = controller;
		mainPane = new VBox();
		mainPane.setId("startView");
		setUpTitle();
		setUpButton();
		setUpMedia();
		gameScene = new Scene(mainPane, controller.getPaneWidth(), 
				controller.getPaneHeight());
		gameScene.getStylesheets().add(getClass()
				.getResource("/controller/application.css")
				.toExternalForm());
	}
	
	public void setUpTitle() {
		titleBox = new HBox(5);
		label = new Label("MarvelStone");
		label.getStyleClass().add("mainTitle");
		label.setPadding(new Insets(20, 300, 20, 300));
		titleBox.setPadding(new Insets(20));
		titleBox.setAlignment(Pos.CENTER);
		titleBox.getChildren().add(label);
		mainPane.getChildren().add(titleBox);
	}
	
	public void setUpButton() {
		buttonBox = new VBox(10);
		playButton = new Button("PLAY GAME");
		instructionsButton = new Button("Instructions");
		buttonBox.setPadding(new Insets(525, 0, 0, 0));
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		buttonBox.getChildren().addAll(playButton, instructionsButton);
		mainPane.getChildren().add(buttonBox);
		playButton.setOnAction(event -> {
			controller.showHomeScene();
		});
		playButton.setOnMouseEntered(event -> {
			playButton.setCursor(Cursor.HAND);
		});
		playButton.setOnMouseExited(event -> {
			playButton.setCursor(Cursor.DEFAULT);
		});
		instructionsButton.setOnAction(event -> {
			showInstructions();
		});
		instructionsButton.setOnMouseEntered(event -> {
			instructionsButton.setCursor(Cursor.HAND);
		});
		instructionsButton.setOnMouseExited(event -> {
			instructionsButton.setCursor(Cursor.DEFAULT);
		});
	}
	
	private void showInstructions() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("MarvelStone Instructions!");
		alert.setHeaderText("Instructions to play MarvelStone: ");
		// TODO - Change below
		alert.setContentText("1. Choose a deck that you want to use against the AI. Each deck consists of 15 cards. \n" + 
				"2. You will start the game with 30 Health Points, along with 3 mana that will be refreshed after every turn. \n" + 
				"3. You can have a maximum of five cards in your hand, and 3 cards on the field. Drawing a card takes 1 mana. Playing a card takes 1 mana as well. \n" + 
				"4. All heroes in your deck possess a health and damage stat, which can attack the opponent directly (2 mana) or his/her heroes accordingly (1 mana). \n" + 
				"5. To attack an opponent directly, click on the hero you wish to attack with, and then the symbol of the opponent you are against, and then press the attack button. \n" + 
				"6. To attack an opponent hero, click on the hero you wish to attack with, and then the hero of the opponent you wish to attack, and then press the attack button. \n" + 
				"7. The objective of the game is to defeat the opponent, by reducing their health points to 0. \n" + 
				"8. If you attack an opponent’s hero (1 mana), and there is extra damage, that leftover damage will be spilled over to another opponent hero on the field, or the opponent directly (2 mana). ");
		alert.showAndWait();
	}
	
	public void setUpMedia() {
		media = new Media(getClass().getResource(toPlay).toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
	        public void run() {
	          mediaPlayer.seek(Duration.ZERO);
	        }
	    });
		mediaPlayer.play();
	}
	
	public Scene getGameScene() {
		return gameScene;
	}
}
