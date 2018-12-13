/**
 * 
 */
package view;

import java.util.Locale;
import java.util.ResourceBundle;

import cell.PlayerCell;
import cell.SuperTeam;
import cell.SuperTeamCell;
import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CardFactory;
import model.Player;
import model.PlayerFactory;
import model.Stats;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class HomeView {

	// Private Variables
	private GameController controller;
	private Scene gameScene; // scene to hold game
	
	private BorderPane mainPane;
	// Top Pane
	private VBox top;
	private Label gameTitle;
	// Left Pane
	private VBox leaderboards;
	private Label lbTitle;
	private ListView<Player> lvLeaderboards;
	// Mid Pane
	private HBox middleParent;
	private VBox middle;
	private HBox statsBox;
	private Label statsLabel;
	private HBox gamesPlayedBox;
	private Label gamesPlayedLabel;
	private Label gamesPlayed;
	private HBox gamesWonBox;
	private Label gamesWonLabel;
	private Label gamesWon;
	private HBox winRateBox;
	private Label winRateLabel;
	private Label winRate;
	private HBox recordBox;
	private Label recordLabel;
	private Label record;
	// Right Pane
	private VBox right;
	private Label chooseDeckLabel;
	private ListView<SuperTeam> lvDecks;
	private Button showCharsButton;
	// Bottom Pane
	private VBox bottom;
	private Button playButton;
	
	private PlayerFactory pf;
	private CardFactory cf;
	private SuperTeam deckChosenToPlay;
	
	// For Locale
	private Locale currentLocale;
	private ResourceBundle me;
	
	public HomeView(GameController controller) {
		this.controller = controller;
		mainPane = new BorderPane();
		mainPane.setId("homeView");
		cf = controller.getCf();
		pf = new PlayerFactory();
		this.currentLocale = Locale.getDefault();
		me = ResourceBundle.getBundle("labels", currentLocale);
		setUpTopPane();
		setUpLeftPane();
		setUpMidPane();
		setUpRightPane();
		setUpBottomPane();
		gameScene = new Scene(mainPane, controller.getPaneWidth(), 
			controller.getPaneHeight());
		gameScene.getStylesheets().add(getClass()
				.getResource("/controller/application.css")
				.toExternalForm());
	}	

	private void setUpTopPane() {
		top = new VBox(10);
		top.setPadding(new Insets(20, 0, 0, 0));
		gameTitle = new Label(me.getString("Labels.gameName"));
		gameTitle.setPadding(new Insets(20));
		gameTitle.getStyleClass().add("title");
		top.getChildren().add(gameTitle);
		top.setAlignment(Pos.CENTER);
		mainPane.setTop(top);
	}
	
	private void setUpLeftPane() {
		leaderboards = new VBox(10);
		lbTitle = new Label(me.getString("Labels.leaderboards"));
		lbTitle.setAccessibleRoleDescription(me.getString("Labels.leaderboards"));
		lbTitle.setPadding(new Insets(5));
		lbTitle.getStyleClass().add("homeLabel");
		lvLeaderboards = new ListView<Player>();
		createLeaderboards();
		leaderboards.getChildren()
			.addAll(lbTitle, lvLeaderboards);
		leaderboards.setAlignment(Pos.CENTER);
		mainPane.setLeft(leaderboards);
	}
	
	private void setUpMidPane() {
		Stats curr = lvLeaderboards.getSelectionModel()
				.getSelectedItem().getPlayerStats();
		middleParent = new HBox(5);
		middle = new VBox(20);
		
		statsBox = new HBox(5);
		statsLabel = new Label(me.getString("Labels.playerStats"));
		statsLabel.setAccessibleRoleDescription(me.getString("Labels.playerStats"));
		statsLabel.setId("statsLabel");
		statsBox.getChildren().add(statsLabel);
		
		gamesPlayedBox = new HBox(5);
		gamesPlayedLabel = new Label(me.getString("Labels.gamesPlayed"));
		gamesPlayedLabel.setAccessibleRoleDescription(me.getString("Labels.gamesPlayed"));
		gamesPlayedLabel.getStyleClass().add("midLabels");
		gamesPlayed = new Label(Integer.toString(curr.getGamesPlayed()));
		gamesPlayed.getStyleClass().add("midLabels");
		gamesPlayedBox.getChildren()
			.addAll(gamesPlayedLabel, gamesPlayed);
		gamesPlayedBox.getStyleClass().add("midBox");
		
		gamesWonBox = new HBox(5);
		gamesWonLabel = new Label(me.getString("Labels.gamesWon"));
		gamesWonLabel.setAccessibleRoleDescription(me.getString("Labels.gamesWon"));
		gamesWonLabel.getStyleClass().add("midLabels");
		gamesWon = new Label(Integer.toString(curr.getGamesWon()));
		gamesWon.getStyleClass().add("midLabels");
		gamesWonBox.getChildren()
			.addAll(gamesWonLabel, gamesWon);
		gamesWonBox.getStyleClass().add("midBox");
		
		winRateBox = new HBox(5);
		winRateLabel = new Label(me.getString("Labels.winRate"));
		winRateLabel.setAccessibleRoleDescription(me.getString("Labels.winRate"));
		winRateLabel.getStyleClass().add("midLabels");
		winRate = new Label(curr.getWinRateString());
		winRate.getStyleClass().add("midLabels");
		winRateBox.getStyleClass().add("midBox");
		winRateBox.getChildren()
			.addAll(winRateLabel, winRate);
		
		recordBox = new HBox(5);
		recordLabel = new Label(me.getString("Labels.record"));
		recordLabel.setAccessibleRoleDescription(me.getString("Labels.record"));
		recordLabel.getStyleClass().add("midLabels");
		record = new Label(curr.getRecord());
		record.getStyleClass().add("midLabels");
		recordBox.getStyleClass().add("midBox");
		recordBox.getChildren()
			.addAll(recordLabel, record);
		middle.getChildren().addAll(statsBox, gamesPlayedBox, 
				gamesWonBox, winRateBox, recordBox);
		middle.setAlignment(Pos.CENTER);
		middleParent.getChildren().add(middle);
		middleParent.setAlignment(Pos.CENTER);
		mainPane.setCenter(middleParent);
	}
	
	private void setUpRightPane() {
		right = new VBox(10);
		chooseDeckLabel = new Label(me.getString("Labels.chooseDeck"));
		chooseDeckLabel.setAccessibleRoleDescription(me.getString("Labels.chooseDeck"));
		chooseDeckLabel.setPadding(new Insets(5));
		chooseDeckLabel.getStyleClass().add("homeLabel");
		lvDecks = new ListView<SuperTeam>();
		createDeckListView();
//		showCharsButton = new Button("Show Characters");
		right.getChildren()
			.addAll(chooseDeckLabel, lvDecks);
		right.setAlignment(Pos.CENTER);
		mainPane.setRight(right);
	}
	
	private void setUpBottomPane() {
		bottom = new VBox(5);
		playButton = new Button(me.getString("Labels.playButton"));
		playButton.setAccessibleRoleDescription(me.getString("Labels.playButton"));
		bottom.setPadding(new Insets(0,0,20,0));
		bottom.getChildren().add(playButton);
		bottom.setAlignment(Pos.TOP_CENTER);
		mainPane.setBottom(bottom);
		setOnPlaySelected();
	}
	
	private void createLeaderboards() {
		lvLeaderboards.getItems().addAll(pf.getPlayers());
		lvLeaderboards.getSelectionModel()
			.setSelectionMode(SelectionMode.SINGLE);
		lvLeaderboards.getSelectionModel().selectedItemProperty()
			.addListener((obValue, oldVal, newVal) -> {
				System.out.println("Selected Val: " + newVal.getUserName());
				if (gamesPlayed != null && gamesWon != null && winRate != null
						&& record != null) {
					gamesPlayed.textProperty().unbind();
					gamesPlayed.textProperty().bind(newVal
							.getPlayerStats().getGamesPlayedProp()
							.asString());
					gamesWon.textProperty().unbind();
					gamesWon.textProperty().bind(newVal
							.getPlayerStats().getGamesWonProp()
							.asString());
					winRate.textProperty().unbind();
					winRate.textProperty().bind(newVal
							.getPlayerStats().getWinRateStringProp());
					record.textProperty().unbind();
					record.textProperty().bind(newVal
							.getPlayerStats().getRecordProp());
				}
			});
		lvLeaderboards.getSelectionModel().selectFirst();
		// cell factory -- create custom cells to display. 
		lvLeaderboards.setCellFactory(l -> new PlayerCell());
	}
	
	private void createDeckListView() {
		lvDecks.getItems().addAll(cf.getSuperTeams());
		lvDecks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lvDecks.getSelectionModel().selectedItemProperty().addListener((obValue, oldVal, newVal) -> {
			System.out.println("Selected Val: " + newVal.getName());
			deckChosenToPlay = newVal;
		});
		lvDecks.getSelectionModel().selectFirst();
		// cell factory -- create custom cells to display. 
		lvDecks.setCellFactory(l -> new SuperTeamCell());
	}
	
	private void setOnPlaySelected() {
		playButton.setOnAction(event -> {
			controller.setUpDecks(deckChosenToPlay);
			controller.showGameScene();
		});
		playButton.setOnMouseEntered(event -> {
			playButton.setCursor(Cursor.HAND);
			event.consume();
		});
		playButton.setOnMouseExited(event -> {
			playButton.setCursor(Cursor.DEFAULT);
			event.consume();
		});
	}
	
	/**
	 * @return the gameScene
	 */
	public Scene getGameScene() {
		return gameScene;
	}
}
