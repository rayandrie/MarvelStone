/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.List;

import cell.SuperTeam;
import controller.GameController;
import help.GamePopup;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Card;
import model.HeroCard;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class GameView {
	
	// Private Variables
	private GameController controller;
	private Scene gameScene;
	private List<Card> playerDeck;
	private SuperTeam playerTeam;
	private SuperTeam opponentTeam;
	
	private BorderPane mainPane;
	private VBox gameboard;
	// Opponent Deck + Hand
	private HBox oppSideBox;
	private HBox oppDeckBox;
	private HBox oppHandBox;
	// Opponent Field
	private HBox oppFieldBox;
	private HBox oppFieldCards;
	private VBox oppHeroBox1;
	private ImageView oppHero1;
	private Label oppHpValue1;
	private IntegerProperty oppHero1HpProp = new SimpleIntegerProperty(0);
	private Label oppDamageValue1;
	private IntegerProperty oppHero1DamageProp = new SimpleIntegerProperty(0);
	private VBox oppHeroBox2;
	private ImageView oppHero2;
	private Label oppHpValue2;
	private IntegerProperty oppHero2HpProp = new SimpleIntegerProperty(0);
	private Label oppDamageValue2;
	private IntegerProperty oppHero2DamageProp = new SimpleIntegerProperty(0);
	private VBox oppHeroBox3;
	private ImageView oppHero3;
	private Label oppHpValue3;
	private IntegerProperty oppHero3HpProp = new SimpleIntegerProperty(0);
	private Label oppDamageValue3;
	private IntegerProperty oppHero3DamageProp = new SimpleIntegerProperty(0);
	// Player Field
	private HBox playerFieldBox;
	private HBox playerFieldCards;
	private VBox playerHeroBox1;
	private ImageView playerHero1;
	private Label playerHpValue1;
	private IntegerProperty playerHero1HpProp = new SimpleIntegerProperty(0);
	private Label playerDamageValue1;
	private IntegerProperty playerHero1DamageProp = new SimpleIntegerProperty(0);
	private VBox playerHeroBox2;
	private ImageView playerHero2;
	private Label playerHpValue2;
	private IntegerProperty playerHero2HpProp = new SimpleIntegerProperty(0);
	private Label playerDamageValue2;
	private IntegerProperty playerHero2DamageProp = new SimpleIntegerProperty(0);
	private VBox playerHeroBox3;
	private ImageView playerHero3;
	private Label playerHpValue3;
	private IntegerProperty playerHero3HpProp = new SimpleIntegerProperty(0);
	private Label playerDamageValue3;
	private IntegerProperty playerHero3DamageProp = new SimpleIntegerProperty(0);
	// Player Deck + Hand
	private HBox playerSideBox;
	private HBox playerHandBox;
	private VBox playerDeckBox;
	private VBox playerHandBox1;
	private ImageView playerHand1;
	private Label heroHpHand1;
	private IntegerProperty heroHpHand1Prop = new SimpleIntegerProperty(0);
	private Label heroDamageHand1;
	private IntegerProperty heroDamageHand1Prop = new SimpleIntegerProperty(0);
	private VBox playerHandBox2;
	private ImageView playerHand2;
	private Label heroHpHand2;
	private IntegerProperty heroHpHand2Prop = new SimpleIntegerProperty(0);
	private Label heroDamageHand2;
	private IntegerProperty heroDamageHand2Prop = new SimpleIntegerProperty(0);
	private VBox playerHandBox3;
	private ImageView playerHand3;
	private Label heroHpHand3;
	private IntegerProperty heroHpHand3Prop = new SimpleIntegerProperty(0);
	private Label heroDamageHand3;
	private IntegerProperty heroDamageHand3Prop = new SimpleIntegerProperty(0);
	private VBox playerHandBox4;
	private ImageView playerHand4;
	private Label heroHpHand4;
	private IntegerProperty heroHpHand4Prop = new SimpleIntegerProperty(0);
	private Label heroDamageHand4;
	private IntegerProperty heroDamageHand4Prop = new SimpleIntegerProperty(0);
	private VBox playerHandBox5;
	private ImageView playerHand5;
	private Label heroHpHand5;
	private IntegerProperty heroHpHand5Prop = new SimpleIntegerProperty(0);
	private Label heroDamageHand5;
	private IntegerProperty heroDamageHand5Prop = new SimpleIntegerProperty(0);
	
	// Player Symbol
	private HBox playerSymbolBox;
	private Circle playerProfile;
	// Opponent Symbol
	private HBox oppSymbolBox;
	private Circle opponentProfile;
	
	// Right Pane
	private VBox userInterface;
	private HBox outBox;
//	private Button restartButton;
	private Button quitButton;
	private VBox gameButtonsBox;
	
	// Player and Opponent Information
	private HBox playersHealthInfo;
	private HBox playersManaInfo;
	private HBox playerInfoBox1;
	private HBox playerInfoBox2;
	private HBox playerInfoBox3;
	private HBox playerInfoBox4;
	private Label playerHealth;
	private IntegerProperty playerHealthProp;
	private Label playerMana;
	private IntegerProperty playerManaProp;
	private Label oppHealth;
	private IntegerProperty oppHealthProp;
	private Label oppMana;
	private IntegerProperty oppManaProp;
	private Button endTurnButton;
	private Button drawButton;
	private Button attackButton;
	private HBox startBox;
	private Button startButton;
	
	// Attack Variables
	private ImageView playerHeroChosen;
	private ImageView oppHeroChosen;
	private Circle opponentSymbol;
	
	// Card ImageView Lists
	List<ImageView> playerCards = new ArrayList<ImageView>();
	List<Label> playerHeroesHPLabel = new ArrayList<Label>();
	List<IntegerProperty> playerHeroesHP = new ArrayList<IntegerProperty>();
	List<Label> playerHeroesDamageLabel = new ArrayList<Label>();
	List<IntegerProperty> playerHeroesDamage = new ArrayList<IntegerProperty>();
	
	// Constants
	private final int SPACING_BETWEEN_CARDS = 10;
	private final int SPACING_BETWEEN_ZONES = 15;
	private final int PADDING_VALUE = 10;
	private final int PADDING_LEFT_UI = 350;
	private final Image BASE_IMAGE = new Image("/cards/cardback.jpg");
	private final String BASE_IMAGE_PATH = "/cards/cardback.jpg";
	private final double PROFILE_STROKE_WIDTH = 3.0;
	private final String GREEN_HIGHLIGHT = "-fx-border-color: #54CD03;\n" +
            "" +
            "-fx-border-width: 3;\n" +
            "-fx-border-style: solid;\n";
	private final String RED_HIGHLIGHT = "-fx-border-color: red;\n" +
            "" +
            "-fx-border-width: 3;\n" +
            "-fx-border-style: solid;\n";
	
	// Boolean Values
	public boolean gameStarted = false;
	private boolean validDrag = false;
	private boolean isPlayerFirstTurn = true;
	private boolean isOppFirstTurn = true;
	private int numAttackClicks = 0;

	public GameView(GameController controller) {
		this.controller = controller;
		mainPane = new BorderPane();
		gameScene = new Scene(mainPane, controller.getPaneWidth(), controller.getPaneHeight());
		gameScene.getStylesheets().add(getClass()
				.getResource("/controller/application.css")
				.toExternalForm());
		mainPane.setId("gameView");
	}
	
	public void setCenter() {
		gameboard = new VBox(10);
		setOppSide();
		setOppField();
		setPlayerField();
		setPlayerSide();
		mainPane.setCenter(gameboard);
	}
	
	public void setRight() {
		userInterface = new VBox(SPACING_BETWEEN_ZONES);
		outBox = new HBox();
		quitButton = new Button("Quit");
		outBox.getChildren().add(quitButton);
		outBox.setAlignment(Pos.TOP_RIGHT);
		outBox.setPadding(new Insets(0, 0, 
				350, PADDING_LEFT_UI));
		gameButtonsBox = new VBox(SPACING_BETWEEN_ZONES);
		playersHealthInfo = new HBox(SPACING_BETWEEN_ZONES);
		playerInfoBox1 = new HBox(5);
		playerInfoBox2 = new HBox(5);
		
		Label playerHealthLabel = new Label("Your Health: ");
		playerHealthLabel.getStyleClass().add("heroLabels");
		playerHealth = new Label();
		playerHealthProp = controller.getPlayerHealth();
		playerHealth.textProperty().bind(playerHealthProp.asString());
		playerHealth.getStyleClass().add("heroLabels");
		
		Label oppHealthLabel = new Label("Opp Health: ");
		oppHealthLabel.getStyleClass().add("villainLabels");
		oppHealth = new Label();
		oppHealthProp = controller.getOppHealth();
		oppHealth.textProperty().bind(oppHealthProp.asString());
		oppHealth.getStyleClass().add("villainLabels");
		
		playerInfoBox1.getChildren()
			.addAll(playerHealthLabel, playerHealth);
		playerInfoBox2.getChildren()
			.addAll(oppHealthLabel, oppHealth);
		playersHealthInfo.getChildren().addAll(playerInfoBox1, 
				playerInfoBox2);
		playersHealthInfo.setAlignment(Pos.CENTER);
		
		playersManaInfo = new HBox(SPACING_BETWEEN_ZONES);
		playerInfoBox3 = new HBox(5);
		playerInfoBox4 = new HBox(5);
		Label playerManaLabel = new Label("Your Mana: ");
		playerManaLabel.getStyleClass().add("heroLabels");
		playerMana = new Label();
		playerManaProp = controller.getPlayerMana();
		playerMana.textProperty().bind(playerManaProp.asString());
		playerMana.getStyleClass().add("heroLabels");
		
		Label oppManaLabel = new Label("Opp Mana: ");
		oppManaLabel.getStyleClass().add("villainLabels");
		oppMana = new Label();
		oppManaProp = controller.getOppMana();
		oppMana.textProperty().bind(oppManaProp.asString());
		oppMana.getStyleClass().add("villainLabels");
		
		playerInfoBox3.getChildren()
			.addAll(playerManaLabel, playerMana);
		playerInfoBox4.getChildren()
			.addAll(oppManaLabel, oppMana);
		playersManaInfo.getChildren().addAll(playerInfoBox3, 
				playerInfoBox4);
		playersManaInfo.setAlignment(Pos.CENTER);
		gameButtonsBox.getChildren().addAll(playersHealthInfo, 
				playersManaInfo);

		endTurnButton = new Button("End Turn");
		drawButton = new Button("Draw Card");
		attackButton = new Button("Attack");
		flipEndAndDrawButtons(true);
		flipAttackButton(true);
		gameButtonsBox.getChildren().addAll(endTurnButton, 
				drawButton, attackButton);
		gameButtonsBox.setAlignment(Pos.CENTER);
		gameButtonsBox.setPadding(new Insets(0, 0, 50, 0));
		startBox = new HBox(SPACING_BETWEEN_ZONES);
		startButton = new Button("Start Game");
		startBox.getChildren().add(startButton);
		startBox.setAlignment(Pos.CENTER);
		startBox.setPadding(new Insets(100, 0, 0, 0));
		userInterface.getChildren().addAll(outBox,
				gameButtonsBox, startBox);
		userInterface.getStyleClass().add("vboxBorder");
		userInterface.setPadding(new Insets(PADDING_VALUE));
		mainPane.setRight(userInterface);
	}
	
	public void setUpClickEvents() {
		mainPane.setOnMouseClicked(event -> {
			/* Reset all private Attack Variables
			 * if clicked outside:
			 * 1. playerHeroChosen will not have green 
			 * 	  highlight anymore
			 * 2. oppHeroChosen will not have red
			 * 	  highlight anymore
			 * 3. opponentSymbol will not have red stroke
			 * 	  anymore
			 */ 
			resetFromAttack();
			System.out.println("Clicked Outside!");
			event.consume();
		});
	}
	
	// Reset front-end if user does an invalid move
	private void resetFromAttack() {
		if (opponentSymbol != null) {
			opponentSymbol.setStroke(Color.BLACK);
			opponentSymbol = null;
		}
		if (playerHeroChosen != null) {
			VBox playerParent = (VBox) playerHeroChosen.getParent();
			playerParent.setStyle("");
		}
		if (oppHeroChosen != null) {
			VBox oppParent = (VBox) oppHeroChosen.getParent();
			oppParent.setStyle("");
		}
	}
	
	public void setUpButtonEvents() {
		// Draw Button
		drawButton.setOnAction(event -> {
			// Draw a card using controller
			boolean drawn = controller.drawCard();
			controller.getDeckSize();
			if (!drawn) {
				System.out.println("No Mana/Hand Full!");
				if (!controller.enoughMana()) {
					GamePopup.showPopupMessage("Not Enough Mana!", 
							controller.getPrimaryStage(), controller.getPaneWidth(), 
							controller.getPaneHeight());
				} else {
					// Hand is Full
					GamePopup.showPopupMessage("Your Hand is Full!", 
							controller.getPrimaryStage(), controller.getPaneWidth(), 
							controller.getPaneHeight());
				}
			}
		});
		drawButton.setOnMouseEntered(event -> {
			drawButton.setCursor(Cursor.HAND);
			event.consume();
		});
		drawButton.setOnMouseExited(event -> {
			drawButton.setCursor(Cursor.DEFAULT);
			event.consume();
		});
		// Start Button
		startButton.setOnAction(event -> {
			// Draw starting 5 cards
			if (!gameStarted) {
				controller.drawFiveCards();
				gameStarted = true;
				flipEndAndDrawButtons(false);
			}
		});
		startButton.setOnMouseEntered(event -> {
			startButton.setCursor(Cursor.HAND);
			event.consume();
		});
		startButton.setOnMouseExited(event -> {
			startButton.setCursor(Cursor.DEFAULT);
			event.consume();
		});
		// Attack Button
		attackButton.setOnAction(event -> {
			System.out.println("Attack Button Clicked!");
			/* There are 2 types of attacks: Either the 
			 * player clicks the hero on Player Field, 
			 * then the hero on Opponent Field, then this
			 * button, OR the player clicks the hero on 
			 * Player Field, then the opponent symbol, then
			 * this button. Reset all private Attack Variables
			 * once back-end and front-end done
			 */
			if (playerHeroChosen == null) {
				GamePopup.showPopupMessage("Please select hero to attack with!", 
						controller.getPrimaryStage(), controller.getPaneWidth(), 
						controller.getPaneHeight());
				return;
			}
			if ((playerHeroChosen == null && oppHeroChosen == null) 
					|| (playerHeroChosen == null && opponentSymbol == null)) {
				// Invalid move, do nothing
				GamePopup.showPopupMessage("No selection has been made!", 
						controller.getPrimaryStage(), controller.getPaneWidth(), 
						controller.getPaneHeight());
				return;
			}
			// Here on, valid selections have been made
			if (opponentSymbol == null) {
				// Player wants to attack opponent hero
				boolean attacked = controller.attackOpponentHero(playerHeroChosen, oppHeroChosen);
				if (!attacked) {
					System.out.println("Did not attack!");
				} else {
					System.out.println("Attacked Opponent Hero!");
				}
			} else {
				// Player wants to attack opponent directly
				boolean attacked = controller.attackOpponentDirectly(playerHeroChosen);
				if (!attacked) {
					System.out.println("Attack Invalid! Opponent still has "
							+ "heroes on the field!");
					if (!controller.enoughMana()) {
						GamePopup.showPopupMessage("Not Enough Mana!", 
								controller.getPrimaryStage(), controller.getPaneWidth(), 
								controller.getPaneHeight());
					} else {
						// Output attack is invalid
						GamePopup.showPopupMessage("Invalid Attack! Opponent still has "
								+ "heroes on the field!", 
								controller.getPrimaryStage(), controller.getPaneWidth(), 
								controller.getPaneHeight());
					}
				} else {
					// Successful Attack
					System.out.println("Attacked Opponent Directly!");
				}
			}
			/* Check if any cards in opponent or player's 
			 * field is dead. If so, do this in the front-end
			 */
			List<String> deadVillains = controller.findDeadVillains();
			resetOpponentField(deadVillains);
			controller.clearDeadVillains();
			List<String> deadHeroes = controller.findDeadHeroes();
			resetPlayerField(deadHeroes);
			controller.clearDeadHeroes();
			// Need to reset all CSS values
			if (playerHeroChosen != null) {
				VBox playerParent = (VBox) playerHeroChosen.getParent();
				playerParent.setStyle("");
			}
			if (oppHeroChosen != null) {
				VBox oppParent = (VBox) oppHeroChosen.getParent();
				oppParent.setStyle("");
			}
			if (opponentSymbol != null) {
				opponentSymbol.setStroke(Color.BLACK);
			}
			playerHeroChosen = null;
			oppHeroChosen = null;
			opponentSymbol = null;
			numAttackClicks++;
			// Finally, check if player or opponent is dead
			if (controller.isOpponentDead()) {
				System.out.println("Player Won!");
				controller.showWinScene(true, numAttackClicks);
			}
			if (controller.isPlayerDead()) {
				System.out.println("Opponent Won!");
				controller.showWinScene(false, numAttackClicks);
			}
			
		});
		attackButton.setOnMouseEntered(event -> {
			attackButton.setCursor(Cursor.HAND);
			event.consume();
		});
		attackButton.setOnMouseExited(event -> {
			attackButton.setCursor(Cursor.DEFAULT);
			event.consume();
		});
		// End Turn Button
		endTurnButton.setOnAction(event -> {
			// Player 2 needs to move
			
			// If player's first move, let the boolean know 
			if (isPlayerFirstTurn) {
				isPlayerFirstTurn = false;
				// Enable Attack
				flipAttackButton(isPlayerFirstTurn);
			}
			// Play Opponent's Move
			controller.makeOpponentMove(isOppFirstTurn);
			if (isOppFirstTurn) {
				isOppFirstTurn = false;
			}
			// Need to check whether the opponent's move has
			// caused casualties to player's field
			List<String> deadHeroes = controller.findDeadHeroes();
			resetPlayerField(deadHeroes);
			controller.clearDeadHeroes();
			// Need to reset both sides mana after this is done
			controller.resetPlayerMana();
			controller.resetOpponentMana();
			// Check if player has died
			if (controller.isPlayerDead()) {
				System.out.println("Opponent Won!");
				controller.showWinScene(false, numAttackClicks);
			}
		});
		endTurnButton.setOnMouseEntered(event -> {
			endTurnButton.setCursor(Cursor.HAND);
			event.consume();
		});
		endTurnButton.setOnMouseExited(event -> {
			endTurnButton.setCursor(Cursor.DEFAULT);
			event.consume();
		});
		// Quit Button
		quitButton.setOnAction(event -> {
			// Go back to homeView
			controller.resetToHomeScene();
		});
		quitButton.setOnMouseEntered(event -> {
			quitButton.setCursor(Cursor.HAND);
			event.consume();
		});
		quitButton.setOnMouseExited(event -> {
			quitButton.setCursor(Cursor.DEFAULT);
			event.consume();
		});
	}
	
	public void setUpDragEvents() {
		// Need to set up all VBoxs in Player's hand (SET ON DRAG DECTECTED)
		ObservableList<Node> handVboxes = playerHandBox.getChildren();
		for (int i = 0; i < handVboxes.size(); i++) {
			VBox source = (VBox) handVboxes.get(i);
			source.setOnDragDetected(event -> {
				ImageView im = (ImageView) source.getChildren().get(0);
				// Need to also check whether the user has enough mana to do this
				// Need to check whether the card image is legit
				if (gameStarted && controller.enoughMana() 
						&& !checkImageEquality(im.getImage(), BASE_IMAGE)) {
					// Transfer Mode is Move because we only want to move the card
					Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
					// Need to put the Image in the Dragboard
					ClipboardContent content = new ClipboardContent();
					content.putImage(im.getImage());
					db.setContent(content);
//					System.out.println("End of Drag Detected!");
				} else if (!controller.enoughMana()) {
					GamePopup.showPopupMessage("Not Enough Mana!", 
							controller.getPrimaryStage(), controller.getPaneWidth(), 
							controller.getPaneHeight());
				}
				// Consume event to stop propagation
				event.consume();
			});
			// Need to set up ON DRAG DONE
			source.setOnDragDone(event -> {
				// Set the current card to be back to 
				// base image and properties to be back to 0
				// Change ImageView
				if (validDrag) {
					ImageView currIv = (ImageView) source.getChildren().get(0);
					currIv.setImage(new Image("/cards/cardback.jpg"));
					// Change HP Property, unbind previous
					HBox healthBox = (HBox) source.getChildren().get(1);
					Label hpVal = (Label) healthBox.getChildren().get(1);
					hpVal.textProperty().unbind();
					hpVal.textProperty().bind(new SimpleIntegerProperty(0).asString());
					// Change Damage Property, unbind previous
					HBox damageBox = (HBox) source.getChildren().get(2);
					Label damageVal = (Label) damageBox.getChildren().get(1);
					damageVal.textProperty().unbind();
					damageVal.textProperty().bind(new SimpleIntegerProperty(0).asString());
//					System.out.println("End of Drag Done!");
					validDrag = false;
				}
				event.consume();
			});
			// Change the cursor when you have entered the source
			source.setOnMouseEntered(event -> {
				source.setCursor(Cursor.OPEN_HAND);
				event.consume();
			});
			// Change the cursor back when you have exited
			source.setOnMouseExited(event -> {
				source.setCursor(Cursor.DEFAULT);
				event.consume();
			});
			// TODO - Need to set up for power ups.
		}
		// Need to set up All VBoxs in player's field to 
		// receive the target (SET ON DRAG OVER)
		playerFieldCards.setOnDragOver(event -> {
			if (event.getGestureSource() != playerFieldCards &&
					event.getDragboard().hasImage()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
//			System.out.println("End of Drag Over!");
			event.consume();
		});
		// Need to provide visual feedback
		playerFieldCards.setOnDragEntered(event -> {
			if (event.getGestureSource() != playerFieldCards
					&& event.getDragboard().hasImage()) {
				Glow glow = new Glow();
				glow.setLevel(0.9);
				playerFieldCards.setEffect(glow);
			}
//			System.out.println("End of Drag Entered!");
			event.consume();
		});
		playerFieldCards.setOnDragExited(event -> {
			Glow currGlow = (Glow) playerFieldCards.getEffect();
			currGlow.setLevel(0.0);
//			System.out.println("End of Drag Exited!");
			event.consume();
		});
		// Need to set up the player field after dropped for the 
		// (SET ON DRAG DROPPED)
		playerFieldCards.setOnDragDropped(event -> {
			// Get the image, and make the controller play
			// the card. 
			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasImage()) {
				Card playedCard = controller.playCard(db.getImage());
				validDrag = true;
				if (playedCard == null) {
					// Is a Power-Up Card, so no need to 
					// do anything on the field
					return;
				}
				// Add that card to the field
				setPlayerFieldCard((HeroCard) playedCard);
				success = true;
			}
			event.setDropCompleted(success);
//			System.out.println("End of Drag Dropped!");
			event.consume();
		});
	}
	
	// Check if Opponent heroes are dead
	public void resetOpponentField(List<String> cardPaths) {
		ObservableList<Node> fieldVBoxes = oppFieldCards.getChildren();
//		System.out.println("Opponent's Dead: " + cardPaths.size());
		for (int i = 0; i < cardPaths.size(); i++) {
			for (int j = 0; j < fieldVBoxes.size(); j++) {
				VBox curr = (VBox) fieldVBoxes.get(j);
				ImageView iv = (ImageView) curr.getChildren().get(0);
				Image imageToCompare = new Image(cardPaths.get(i));
				if (checkImageEquality(imageToCompare, iv.getImage())) {
					// Need to set it back to default image
					iv.setImage(new Image(BASE_IMAGE_PATH));
					HBox healthBox = (HBox) curr.getChildren().get(1);
					Label hpVal = (Label) healthBox.getChildren().get(1);
					// Unbind previous health val
					hpVal.textProperty().unbind();
					hpVal.textProperty().bind(new SimpleIntegerProperty(0).asString());
					// Unbind previous damage val
					HBox damageBox = (HBox) curr.getChildren().get(2);
					Label damageVal = (Label) damageBox.getChildren().get(1);
					damageVal.textProperty().unbind();
					damageVal.textProperty().bind(new SimpleIntegerProperty(0).asString());
				}
			}
		}
	}

	// Check if Player heroes are dead
	public void resetPlayerField(List<String> cardPaths) {
		ObservableList<Node> fieldVBoxes = playerFieldCards.getChildren();
//		System.out.println("Player's Dead: " + cardPaths.size());
		for (int i = 0; i < cardPaths.size(); i++) {
			for (int j = 0; j < fieldVBoxes.size(); j++) {
				VBox curr = (VBox) fieldVBoxes.get(j);
				ImageView iv = (ImageView) curr.getChildren().get(0);
				Image imageToCompare = new Image(cardPaths.get(i));
				if (checkImageEquality(imageToCompare, iv.getImage())) {
					// Need to set it back to default image
					iv.setImage(new Image(BASE_IMAGE_PATH));
					HBox healthBox = (HBox) curr.getChildren().get(1);
					Label hpVal = (Label) healthBox.getChildren().get(1);
					// Unbind previous health val
					hpVal.textProperty().unbind();
					hpVal.textProperty().bind(new SimpleIntegerProperty(0).asString());
					// Unbind previous damage val
					HBox damageBox = (HBox) curr.getChildren().get(2);
					Label damageVal = (Label) damageBox.getChildren().get(1);
					damageVal.textProperty().unbind();
					damageVal.textProperty().bind(new SimpleIntegerProperty(0).asString());
				}
			}
		}
	}
	
	// The Player putting a card from hand to the field
	private void setPlayerFieldCard(HeroCard playedCard) {
		ObservableList<Node> fieldVBoxes = playerFieldCards.getChildren();
		for (int i = 0; i < fieldVBoxes.size(); i++) {
			VBox curr = (VBox) fieldVBoxes.get(i);
			// Change ImageView
			ImageView currIv = (ImageView) curr.getChildren().get(0);
			// If the current image is equal to the card back image
			if (checkImageEquality(currIv.getImage(), BASE_IMAGE)) {
				currIv.setImage(new Image(playedCard.getCardImagePath()));
				// Change HP Property, unbind previous
				HBox healthBox = (HBox) curr.getChildren().get(1);
				Label hpVal = (Label) healthBox.getChildren().get(1);
				hpVal.textProperty().unbind();
				hpVal.textProperty().bind(playedCard
						.getHealthProp().asString());
				// Change Damage Property, unbind previous
				HBox damageBox = (HBox) curr.getChildren().get(2);
				Label damageVal = (Label) damageBox.getChildren().get(1);
				damageVal.textProperty().unbind();
				damageVal.textProperty().bind(playedCard.getDamageProp().asString());
				/* Need to set an onClick Action on these images
				 * to be able to get the index of this card chosen from the
				 * field. Additionally, highlight the ImageView to show that
				 * it is selected. However, if the user clicks something that
				 * is not an opponent field card, the highlight disappears.
				 * Add style for each card to be curved.
				 */
				currIv.setOnMouseClicked(event -> {
//					System.out.println("Player Field Card Clicked!");
					// Check if a card on the field has already been clicked
					// If so, need to remove green border
					if (playerHeroChosen != null) {
						VBox playerParent = (VBox) playerHeroChosen.getParent();
						playerParent.setStyle("");
					}
					playerHeroChosen = currIv;
					VBox imParent = (VBox) currIv.getParent();
					imParent.setStyle(GREEN_HIGHLIGHT);
					event.consume();
				});
				currIv.setOnMouseEntered(event -> {
					currIv.setCursor(Cursor.HAND);
					event.consume();
				});
				currIv.setOnMouseExited(event -> {
					currIv.setCursor(Cursor.DEFAULT);
					event.consume();
				});
				break;
			}
		}
	}
	
	// The Opponent putting a card from hand to the field
	public void setOppFieldCard(HeroCard playedCard) {
		ObservableList<Node> fieldVBoxes = oppFieldCards.getChildren();
		for (int i = 0; i < fieldVBoxes.size(); i++) {
			VBox curr = (VBox) fieldVBoxes.get(i);
			// Change ImageView
			ImageView currIv = (ImageView) curr.getChildren().get(0);
			// If the current image is equal to the card back image
			if (checkImageEquality(currIv.getImage(), BASE_IMAGE)) {
				currIv.setImage(new Image(playedCard.getCardImagePath()));
				// Change HP Property, unbind previous
				HBox healthBox = (HBox) curr.getChildren().get(1);
				Label hpVal = (Label) healthBox.getChildren().get(1);
				hpVal.textProperty().unbind();
				hpVal.textProperty().bind(playedCard
						.getHealthProp().asString());
				// Change Damage Property, unbind previous
				HBox damageBox = (HBox) curr.getChildren().get(2);
				Label damageVal = (Label) damageBox.getChildren().get(1);
				damageVal.textProperty().unbind();
				damageVal.textProperty().bind(playedCard.getDamageProp().asString());
				/* Need to set an onClick Action on these images
				 * to be able to get the index of this card chosen from the
				 * field. Additionally, highlight the ImageView to show that
				 * it is selected. However, if the user clicks something that
				 * is not the attack button, the highlight disappears.
				 * Add style for each card to be curved.
				 */
				currIv.setOnMouseClicked(event -> {
//					System.out.println("Opponent Field Card Clicked!");
					// Check if a card on the field has already been clicked
					// If so, need to remove red border
					if (oppHeroChosen != null) {
						VBox oppParent = (VBox) oppHeroChosen.getParent();
						oppParent.setStyle("");
					}
					oppHeroChosen = currIv;
					VBox imParent = (VBox) oppHeroChosen.getParent();
					imParent.setStyle(RED_HIGHLIGHT);
					event.consume();
				});
				currIv.setOnMouseEntered(event -> {
					currIv.setCursor(Cursor.HAND);
					event.consume();
				});
				currIv.setOnMouseExited(event -> {
					currIv.setCursor(Cursor.DEFAULT);
					event.consume();
				});
				break;
			}
		}
	}
	
	public void drawCard(Card c) {
		// Set ImageView to be that card, only if the card 
		// is the base image, and not an actual playing card
		int index = -1;
		for (int i = 0; i < playerCards.size(); i++) {
			ImageView curr = playerCards.get(i);
			if (checkImageEquality(curr.getImage(), BASE_IMAGE)) {
				curr.setImage(new Image(c.getCardImagePath()));
				index = i;
				break;
			}
		}
		// Update health and damage properties of that particular index
		// if it is a HeroCard
		if (c.getClass() == HeroCard.class) {
			HeroCard hc = (HeroCard) c;
			playerHeroesHPLabel.get(index).textProperty().unbind();
			playerHeroesHPLabel.get(index).textProperty()
				.bind(hc.getHealthProp().asString());
			playerHeroesDamageLabel.get(index).textProperty().unbind();
			playerHeroesDamageLabel.get(index).textProperty()
				.bind(hc.getDamageProp().asString());
		} else { // is a PowerUp Card
			playerHeroesHP.get(index).set(0);
			playerHeroesDamage.get(index).set(0);
		}
	}
	
	private void setPlayerSide() {
		playerSideBox = new HBox(SPACING_BETWEEN_ZONES);
		playerHandBox = new HBox(SPACING_BETWEEN_CARDS);
		
		// 1st Hand Card
		VBox v1 = new VBox(5);
		playerHand1 = setCardImage(playerHand1, "/cards/cardback.jpg");
		HBox h1 = new HBox(5);
		Label l1 = new Label("HP: ");
		l1.getStyleClass().add("heroLabels");
		heroHpHand1 = new Label();
		heroHpHand1.textProperty().bind(heroHpHand1Prop.asString());
		heroHpHand1.getStyleClass().add("heroLabels");
		h1.getChildren().addAll(l1, heroHpHand1);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(5);
		Label l2 = new Label("Damage: ");
		l2.getStyleClass().add("heroLabels");
		heroDamageHand1 = new Label();
		heroDamageHand1.textProperty().bind(heroDamageHand1Prop.asString());
		heroDamageHand1.getStyleClass().add("heroLabels");
		h2.getChildren().addAll(l2, heroDamageHand1);
		h2.setAlignment(Pos.CENTER);
		v1.getChildren().addAll(playerHand1, h1, h2);
		
		// 2nd Hand Card
		VBox v2 = new VBox(5);
		playerHand2 = setCardImage(playerHand2, "/cards/cardback.jpg");
		h1 = new HBox(5);
		l1 = new Label("HP: ");
		l1.getStyleClass().add("heroLabels");
		heroHpHand2 = new Label();
		heroHpHand2.textProperty().bind(heroHpHand2Prop.asString());
		heroHpHand2.getStyleClass().add("heroLabels");
		h1.getChildren().addAll(l1, heroHpHand2);
		h1.setAlignment(Pos.CENTER);
		h2 = new HBox(5);
		l2 = new Label("Damage: ");
		l2.getStyleClass().add("heroLabels");
		heroDamageHand2 = new Label();
		heroDamageHand2.textProperty().bind(heroDamageHand2Prop.asString());
		heroDamageHand2.getStyleClass().add("heroLabels");
		h2.getChildren().addAll(l2, heroDamageHand2);
		h2.setAlignment(Pos.CENTER);
		v2.getChildren().addAll(playerHand2, h1, h2);
		
		// 3rd Hand Card
		VBox v3 = new VBox(5);
		playerHand3 = setCardImage(playerHand3, "/cards/cardback.jpg");
		h1 = new HBox(5);
		l1 = new Label("HP: ");
		l1.getStyleClass().add("heroLabels");
		heroHpHand3 = new Label();
		heroHpHand3.textProperty().bind(heroHpHand3Prop.asString());
		heroHpHand3.getStyleClass().add("heroLabels");
		h1.getChildren().addAll(l1, heroHpHand3);
		h1.setAlignment(Pos.CENTER);
		h2 = new HBox(5);
		l2 = new Label("Damage: ");
		l2.getStyleClass().add("heroLabels");
		heroDamageHand3 = new Label();
		heroDamageHand3.textProperty().bind(heroDamageHand3Prop.asString());
		heroDamageHand3.getStyleClass().add("heroLabels");
		h2.getChildren().addAll(l2, heroDamageHand3);
		h2.setAlignment(Pos.CENTER);
		v3.getChildren().addAll(playerHand3, h1, h2);
		
		// 4th Hand Card
		VBox v4 = new VBox(5);
		playerHand4 = setCardImage(playerHand4, "/cards/cardback.jpg");
		h1 = new HBox(5);
		l1 = new Label("HP: ");
		l1.getStyleClass().add("heroLabels");
		heroHpHand4 = new Label();
		heroHpHand4.textProperty().bind(heroHpHand4Prop.asString());
		heroHpHand4.getStyleClass().add("heroLabels");
		h1.getChildren().addAll(l1, heroHpHand4);
		h1.setAlignment(Pos.CENTER);
		h2 = new HBox(5);
		l2 = new Label("Damage: ");
		l2.getStyleClass().add("heroLabels");
		heroDamageHand4 = new Label();
		heroDamageHand4.textProperty().bind(heroDamageHand4Prop.asString());
		heroDamageHand4.getStyleClass().add("heroLabels");
		h2.getChildren().addAll(l2, heroDamageHand4);
		h2.setAlignment(Pos.CENTER);
		v4.getChildren().addAll(playerHand4, h1, h2);
		
		// 5th Hand Card
		VBox v5 = new VBox(5);
		playerHand5 = setCardImage(playerHand5, "/cards/cardback.jpg");
		h1 = new HBox(5);
		l1 = new Label("HP: ");
		l1.getStyleClass().add("heroLabels");
		heroHpHand5 = new Label();
		heroHpHand5.textProperty().bind(heroHpHand5Prop.asString());
		heroHpHand5.getStyleClass().add("heroLabels");
		h1.getChildren().addAll(l1, heroHpHand5);
		h1.setAlignment(Pos.CENTER);
		h2 = new HBox(5);
		l2 = new Label("Damage: ");
		l2.getStyleClass().add("heroLabels");
		heroDamageHand5 = new Label();
		heroDamageHand5.textProperty().bind(heroDamageHand5Prop.asString());
		heroDamageHand5.getStyleClass().add("heroLabels");
		h2.getChildren().addAll(l2, heroDamageHand5);
		h2.setAlignment(Pos.CENTER);
		v5.getChildren().addAll(playerHand5, h1, h2);
		
		playerHandBox.getChildren().addAll(v1, v2, v3, v4, v5);
		playerHandBox.getStyleClass().add("player");
		
		// Player Deck
		playerDeckBox = new VBox(SPACING_BETWEEN_CARDS);
		ImageView iv = new ImageView(new Image("/cards/cardback.jpg"));
		shrinkImage(iv);
		Label deckLabel = new Label("Your Deck");
		deckLabel.getStyleClass().add("heroLabels");
		playerDeckBox.getChildren().addAll(iv, deckLabel);
		playerDeckBox.getStyleClass().add("deck");
		playerDeckBox.setAlignment(Pos.CENTER);
		playerSideBox.getChildren()
			.addAll(playerHandBox, playerDeckBox);
		setPlayerSymbol();
		gameboard.getChildren().add(playerSideBox);
		// Add ImageViews to Lists
		playerCards.add(playerHand1);
		playerCards.add(playerHand2);
		playerCards.add(playerHand3);
		playerCards.add(playerHand4);
		playerCards.add(playerHand5);
		// Add Labels to List to List (HP)
		playerHeroesHPLabel.add(heroHpHand1);
		playerHeroesHPLabel.add(heroHpHand2);
		playerHeroesHPLabel.add(heroHpHand3);
		playerHeroesHPLabel.add(heroHpHand4);
		playerHeroesHPLabel.add(heroHpHand5);
		// Add Labels to List to List (Damage)
		playerHeroesDamageLabel.add(heroDamageHand1);
		playerHeroesDamageLabel.add(heroDamageHand2);
		playerHeroesDamageLabel.add(heroDamageHand3);
		playerHeroesDamageLabel.add(heroDamageHand4);
		playerHeroesDamageLabel.add(heroDamageHand5);
		// Add IntegerProperties to List (HP)
		playerHeroesHP.add(heroHpHand1Prop);
		playerHeroesHP.add(heroHpHand2Prop);
		playerHeroesHP.add(heroHpHand3Prop);
		playerHeroesHP.add(heroHpHand4Prop);
		playerHeroesHP.add(heroHpHand5Prop);
		// Add IntegerProperties to List (Mana)
		playerHeroesDamage.add(heroDamageHand1Prop);
		playerHeroesDamage.add(heroDamageHand2Prop);
		playerHeroesDamage.add(heroDamageHand3Prop);
		playerHeroesDamage.add(heroDamageHand4Prop);
		playerHeroesDamage.add(heroDamageHand5Prop);
	}
	
	private void setPlayerSymbol() {
		playerSymbolBox = new HBox();
		playerProfile = new Circle(150, 100, 50);
		playerProfile.setFill(new ImagePattern
				(new Image(playerTeam.getTeamImagePath())));
		playerProfile.setStroke(Color.BLACK);
		playerProfile.setStrokeWidth(PROFILE_STROKE_WIDTH);
		playerSymbolBox.getChildren().add(playerProfile);
		playerSymbolBox.setAlignment(Pos.CENTER);
		playerSymbolBox.setPadding(new Insets(5,5,5,5));
		playerSideBox.getChildren().add(playerSymbolBox);
	}
	
	private void setPlayerField() {
		playerFieldBox = new HBox(SPACING_BETWEEN_ZONES);
		playerFieldCards = new HBox(SPACING_BETWEEN_CARDS);
		
		// 1st Player's Field Card
		VBox v1 = new VBox(5);
		playerHero1 = setCardImage(playerHero1, "/cards/cardback.jpg");
		HBox h1 = new HBox(5);
		Label l1 = new Label("HP: ");
		l1.getStyleClass().add("heroLabels");
		playerHpValue1 = new Label();
		playerHpValue1.textProperty().bind(playerHero1HpProp.asString());
		playerHpValue1.getStyleClass().add("heroLabels");
		h1.getChildren().addAll(l1, playerHpValue1);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(5);
		Label l2 = new Label("Damage: ");
		l2.getStyleClass().add("heroLabels");
		playerDamageValue1 = new Label();
		playerDamageValue1.textProperty().bind(playerHero1DamageProp.asString());
		playerDamageValue1.getStyleClass().add("heroLabels");
		h2.getChildren().addAll(l2, playerDamageValue1);
		h2.setAlignment(Pos.CENTER);
		v1.getChildren().addAll(playerHero1, h1, h2);
		
		// 2nd Player's Field Card
		VBox v2 = new VBox(5);
		playerHero2 = setCardImage(playerHero2, "/cards/cardback.jpg");
		h1 = new HBox(5);
		l1 = new Label("HP: ");
		l1.getStyleClass().add("heroLabels");
		playerHpValue2 = new Label();
		playerHpValue2.textProperty().bind(playerHero2HpProp.asString());
		playerHpValue2.getStyleClass().add("heroLabels");
		h1.getChildren().addAll(l1, playerHpValue2);
		h1.setAlignment(Pos.CENTER);
		h2 = new HBox(5);
		l2 = new Label("Damage: ");
		l2.getStyleClass().add("heroLabels");
		playerDamageValue2 = new Label();
		playerDamageValue2.textProperty().bind(playerHero2DamageProp.asString());
		playerDamageValue2.getStyleClass().add("heroLabels");
		h2.getChildren().addAll(l2, playerDamageValue2);
		h2.setAlignment(Pos.CENTER);
		v2.getChildren().addAll(playerHero2, h1, h2);
		
		// 3rd Player's Field Card
		VBox v3 = new VBox(5);
		playerHero3 = setCardImage(playerHero3, "/cards/cardback.jpg");
		h1 = new HBox(5);
		l1 = new Label("HP: ");
		l1.getStyleClass().add("heroLabels");
		playerHpValue3 = new Label();
		playerHpValue3.textProperty().bind(playerHero3HpProp.asString());
		playerHpValue3.getStyleClass().add("heroLabels");
		h1.getChildren().addAll(l1, playerHpValue3);
		h1.setAlignment(Pos.CENTER);
		h2 = new HBox(5);
		l2 = new Label("Damage: ");
		l2.getStyleClass().add("heroLabels");
		playerDamageValue3 = new Label();
		playerDamageValue3.textProperty().bind(playerHero3DamageProp.asString());
		playerDamageValue3.getStyleClass().add("heroLabels");
		h2.getChildren().addAll(l2, playerDamageValue3);
		h2.setAlignment(Pos.CENTER);
		v3.getChildren().addAll(playerHero3, h1, h2);
		
		playerFieldCards.getChildren().addAll(v1, v2, v3);
		playerFieldCards.getStyleClass().add("player");
		playerFieldBox.getChildren().add(playerFieldCards);
		playerFieldBox.setAlignment(Pos.CENTER);
		gameboard.getChildren().add(playerFieldBox);
	}
	
	private void setOppField() {
		oppFieldBox = new HBox(SPACING_BETWEEN_ZONES);
		oppFieldCards = new HBox(SPACING_BETWEEN_CARDS);

		// 1st Opponent's Field Card
		VBox v1 = new VBox(5);
		oppHero1 = setCardImage(oppHero1, "/cards/cardback.jpg");
		HBox h1 = new HBox(5);
		Label l1 = new Label("HP: ");
		l1.getStyleClass().add("villainLabels");
		oppHpValue1 = new Label();
		oppHpValue1.textProperty().bind(oppHero1HpProp.asString());
		oppHpValue1.getStyleClass().add("villainLabels");
		h1.getChildren().addAll(l1, oppHpValue1);
		h1.setAlignment(Pos.CENTER);
		HBox h2 = new HBox(5);
		Label l2 = new Label("Damage: ");
		l2.getStyleClass().add("villainLabels");
		oppDamageValue1 = new Label();
		oppDamageValue1.textProperty().bind(oppHero1DamageProp.asString());
		oppDamageValue1.getStyleClass().add("villainLabels");
		h2.getChildren().addAll(l2, oppDamageValue1);
		h2.setAlignment(Pos.CENTER);
		v1.getChildren().addAll(oppHero1, h1, h2);
		
		// 2nd Opponent's Field Card
		VBox v2 = new VBox(5);
		oppHero2 = setCardImage(oppHero2, "/cards/cardback.jpg");
		h1 = new HBox(5);
		l1 = new Label("HP: ");
		l1.getStyleClass().add("villainLabels");
		oppHpValue2 = new Label();
		oppHpValue2.textProperty().bind(oppHero2HpProp.asString());
		oppHpValue2.getStyleClass().add("villainLabels");
		h1.getChildren().addAll(l1, oppHpValue2);
		h1.setAlignment(Pos.CENTER);
		h2 = new HBox(5);
		l2 = new Label("Damage: ");
		l2.getStyleClass().add("villainLabels");
		oppDamageValue2 = new Label();
		oppDamageValue2.textProperty().bind(oppHero2DamageProp.asString());
		oppDamageValue2.getStyleClass().add("villainLabels");
		h2.getChildren().addAll(l2, oppDamageValue2);
		h2.setAlignment(Pos.CENTER);
		v2.getChildren().addAll(oppHero2, h1, h2);
		
		// 3rd Opponent's Field Card
		VBox v3 = new VBox(5);
		oppHero3 = setCardImage(oppHero3, "/cards/cardback.jpg");
		h1 = new HBox(5);
		l1 = new Label("HP: ");
		l1.getStyleClass().add("villainLabels");
		oppHpValue3 = new Label();
		oppHpValue3.textProperty().bind(oppHero3HpProp.asString());
		oppHpValue3.getStyleClass().add("villainLabels");
		h1.getChildren().addAll(l1, oppHpValue3);
		h1.setAlignment(Pos.CENTER);
		h2 = new HBox(5);
		l2 = new Label("Damage: ");
		l2.getStyleClass().add("villainLabels");
		oppDamageValue3 = new Label();
		oppDamageValue3.textProperty().bind(oppHero3DamageProp.asString());
		oppDamageValue3.getStyleClass().add("villainLabels");
		h2.getChildren().addAll(l2, oppDamageValue3);
		h2.setAlignment(Pos.CENTER);
		v3.getChildren().addAll(oppHero3, h1, h2);
		
		oppFieldCards.getChildren().addAll(v1, v2, v3);
		oppFieldCards.getStyleClass().add("opponent");
		oppFieldBox.getChildren().add(oppFieldCards);
		oppFieldBox.setAlignment(Pos.CENTER);
		gameboard.getChildren().add(oppFieldBox);
	}
	
	// Set Opponent's Deck, which is static
	private void setOppSide() {
		oppSideBox = new HBox(SPACING_BETWEEN_ZONES);
		oppDeckBox = new HBox(SPACING_BETWEEN_CARDS);
		insertImage(new Image("/cards/cardback.jpg"), oppDeckBox);
		oppHandBox = new HBox(SPACING_BETWEEN_CARDS);
		insertImage(new Image("/cards/cardback.jpg"), oppHandBox);
		insertImage(new Image("/cards/cardback.jpg"), oppHandBox);
		insertImage(new Image("/cards/cardback.jpg"), oppHandBox);
		insertImage(new Image("/cards/cardback.jpg"), oppHandBox);
		insertImage(new Image("/cards/cardback.jpg"), oppHandBox);
		// Set opponent's hand style
		oppDeckBox.getStyleClass().add("deck");
		oppHandBox.getStyleClass().add("opponent");
		oppSideBox.getChildren().addAll(oppDeckBox, oppHandBox);
		setOppSymbol();
		gameboard.getChildren().add(oppSideBox);
	}
	
	private void setOppSymbol() {
		oppSymbolBox = new HBox();
		opponentProfile = new Circle(150, 100, 50);
		opponentProfile.setFill(new ImagePattern
				(new Image(opponentTeam.getTeamImagePath())));
		opponentProfile.setStroke(Color.BLACK);
		opponentProfile.setStrokeWidth(PROFILE_STROKE_WIDTH);
		opponentProfile.setOnMouseClicked(event -> {
			System.out.println("Clicked Opponent Profile!");
			opponentSymbol = opponentProfile;
			opponentProfile.setStroke(Color.RED);
			event.consume();
		});
		opponentProfile.setOnMouseEntered(event -> {
			opponentProfile.setCursor(Cursor.HAND);
			event.consume();
		});
		opponentProfile.setOnMouseExited(event -> {
			opponentProfile.setCursor(Cursor.DEFAULT);
			event.consume();
		});
		oppSymbolBox.getChildren().add(opponentProfile);
		oppSymbolBox.setAlignment(Pos.CENTER);
		oppSymbolBox.setPadding(new Insets(5,5,5,5));
		oppSideBox.getChildren().add(oppSymbolBox);
	}
	
	private ImageView setCardImage(ImageView iv, String imagePath) {
		iv = new ImageView(new Image(imagePath));
		shrinkImage(iv);
		return iv;
	}
	
	private void insertImage(Image i, HBox hBox) {
		ImageView iv = new ImageView();
		iv.setImage(i);
		shrinkImage(iv);
		hBox.getChildren().add(iv);
	}
	
	private void shrinkImage(ImageView i) {
		i.setFitHeight(150);
		i.setFitWidth(150);
		i.setPreserveRatio(true);
	}
	
	private void flipEndAndDrawButtons(boolean val) {
		endTurnButton.setDisable(val);
		drawButton.setDisable(val);
	}
	
	private void flipAttackButton(boolean val) {
		attackButton.setDisable(val);
	}
	
	private boolean checkImageEquality(Image im1, Image im2) {
		for (int i = 0; i < im1.getWidth(); i++) {
			for (int j = 0; j < im1.getHeight(); j++) {
				if (im1.getPixelReader().getArgb(i, j) != im2.getPixelReader().getArgb(i, j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * @return the playerTeam
	 */
	public SuperTeam getPlayerTeam() {
		return playerTeam;
	}

	/**
	 * @param playerTeam the playerTeam to set
	 */
	public void setPlayerTeam(SuperTeam playerTeam) {
		this.playerTeam = playerTeam;
	}

	/**
	 * @return the opponentTeam
	 */
	public SuperTeam getOpponentTeam() {
		return opponentTeam;
	}

	/**
	 * @param opponentTeam the opponentTeam to set
	 */
	public void setOpponentTeam(SuperTeam opponentTeam) {
		this.opponentTeam = opponentTeam;
	}

	/**
	 * @return the playerDeck
	 */
	public List<Card> getPlayerDeck() {
		return playerDeck;
	}

	/**
	 * @param playerDeck the playerDeck to set
	 */
	public void setPlayerDeck(List<Card> playerDeck) {
		this.playerDeck = playerDeck;
	}

	/**
	 * @return the gameScene
	 */
	public Scene getGameScene() {
		return gameScene;
	}

	/**
	 * @param gameScene the gameScene to set
	 */
	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}

}
