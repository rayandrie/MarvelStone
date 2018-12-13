/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import controller.GameController;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class WinView {

	// Private Variables
	private static final String WIN_MEDIA_URL = "/media/avengers.gif";
	private static final String LOSE_MEDIA_URL = "/media/lose.gif";
	private Scene gameScene;
	private int totalNumClicksForWin;
	private GameController controller;
	
	private HBox header;
	private HBox displayer;
	private HBox forButton;
	private HBox forImage;
	private HBox forVideo;
	private Label winnerLabel;
	private Label clicksDisplay;
	private Button playAgain;
	private ImageView image;
	private MediaPlayer mediaPlayer;
	private List<Animation> transitions = new ArrayList<Animation>();
	private SequentialTransition st = new SequentialTransition();
	private boolean playerWon = false;
	
	public WinView(GameController controller) {
		this.controller = controller;
	}
	
	public Scene getGameScene() {
		return gameScene;
	}
	
	public void makeScene() {
		VBox newRoot = new VBox(15);
		// Make Header
		makeHeader();
		// Make Clicks Display
		makeClicksDisplay();
		// Make Play Again button
		makePlayAgain();
		if (playerWon) {
			// Make Star Animation
			makeImageAnimation();
		}
		// Make Video
		makeVideo();
		// Add all to the parent
		if (playerWon) {
			newRoot.getChildren().addAll(header, displayer, 
					forButton, forImage, forVideo);
		} else {
			newRoot.getChildren().addAll(header, displayer, 
					forButton, forVideo);
		}
		newRoot.setId("winView");
		st.getChildren().addAll(transitions);
		st.setCycleCount(5);
		st.play();
		gameScene = new Scene(newRoot, 
				controller.getPaneWidth(), controller.getPaneHeight());
		gameScene.getStylesheets().add(getClass()
				.getResource("/controller/application.css")
				.toExternalForm());
		newRoot.setId("winView");
	}
	
	private void makeHeader() {
		header = new HBox(5);
		if (playerWon) {
			winnerLabel = new Label("Winner Winner Shawarma Dinner!");
			winnerLabel.getStyleClass().add("winLabel");
		} else {
			winnerLabel = new Label("You Lose!");
			winnerLabel.getStyleClass().add("loseLabel");
		}
		winnerLabel.setPadding(new Insets(20, 0, 20, 0));
		header.getChildren().add(winnerLabel);
		header.setAlignment(Pos.CENTER);
	}
	
	private void makeClicksDisplay() {
		displayer = new HBox(5);
		String winningNumClicks ="";
		if (playerWon) {
			winningNumClicks = "Final # of Attacks to win game: " 
				+ String.valueOf(this.totalNumClicksForWin);
		} else {
			winningNumClicks = "Final # of Attacks to lose game: " 
				+ String.valueOf(this.totalNumClicksForWin);
		}
		clicksDisplay = new Label(winningNumClicks);
		clicksDisplay.setPadding(new Insets(20, 0, 20, 0));
		if (playerWon) {
			clicksDisplay.getStyleClass().add("winLabel");
		} else {
			clicksDisplay.getStyleClass().add("loseLabel");
		}
//		clicksDisplay.setFont(Font.font("American Typewriter", FontWeight.EXTRA_BOLD, FontPosture.ITALIC , 25));
		displayer.getChildren().add(clicksDisplay);
		displayer.setAlignment(Pos.CENTER);
	}
	
	private void makePlayAgain() {
		forButton = new HBox(5);
		playAgain = new Button("Play Again!");
		playAgain.setOnAction(event -> {
			controller.resetToHomeScene();
		});
		playAgain.setOnMouseEntered(event -> {
			playAgain.setCursor(Cursor.HAND);
		});
		playAgain.setOnMouseExited(event -> {
			playAgain.setCursor(Cursor.DEFAULT);
		});
		forButton.getChildren().add(playAgain);
		forButton.setPadding(new Insets(175, 0, 0, 0));
		forButton.setAlignment(Pos.CENTER);
	}
	
	// Image Animation that fades in stars 
	// that is dependent on number of clicks
	private void makeImageAnimation() {
		forImage = new HBox(5);
		if (totalNumClicksForWin <= 8) {
			// Make 5 stars
			setStars(5);
		} else if (totalNumClicksForWin <= 10) {
			// Make 4 stars
			setStars(4);
		} else if (totalNumClicksForWin <= 15) {
			// Make 3 stars
			setStars(3);
		} else if (totalNumClicksForWin <= 20) {
			// Make 2 stars
			setStars(2);
		} else {
			// Make 1 star
			setStars(1);
		}
		forImage.setAlignment(Pos.CENTER);
	}
	
	private void setStars(int num) {
		for (int i = 0; i < num; i++) {
			image = extractImage("/media/Star.png");
			image.setFitHeight(50);
			image.setFitWidth(50);
			FadeTransition ft = new FadeTransition();
			ft.setNode(image);
			ft.setDuration(new Duration(2000));
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			transitions.add(ft);
			forImage.getChildren().add(image);
		}
	}
	
	private ImageView extractImage(String imageLocation) {
		Image i = null;
		try {
			i = new Image(imageLocation);	
		} catch (Exception e) {
			System.out.println("Couldn't load image");
		}
		ImageView iv = new ImageView(i);
		return iv;
	}
	
	private void makeVideo() {
		forVideo = new HBox(5);
		ImageView iv;
		if (playerWon) {
			iv = new ImageView(getClass().getResource(WIN_MEDIA_URL).toString());
		} else {
			iv = new ImageView(getClass().getResource(LOSE_MEDIA_URL).toString());
		}
//		iv.setFitHeight(350);
//		iv.setFitWidth(650);
		forVideo.getChildren().add(iv);
		forVideo.setAlignment(Pos.CENTER);
	}
	
	/**
	 * @return the playerWon
	 */
	public boolean isPlayerWon() {
		return playerWon;
	}

	/**
	 * @param playerWon the playerWon to set
	 */
	public void setPlayerWon(boolean playerWon) {
		this.playerWon = playerWon;
	}

	/**
	 * @return the totalNumClicksForWin
	 */
	public int getTotalNumClicksForWin() {
		return totalNumClicksForWin;
	}

	/**
	 * @param totalNumClicksForWin the totalNumClicksForWin to set
	 */
	public void setTotalNumClicksForWin(int totalNumClicksForWin) {
		this.totalNumClicksForWin = totalNumClicksForWin;
	}
}
