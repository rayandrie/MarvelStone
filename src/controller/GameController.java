/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.SuperTeam;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Card;
import model.CardFactory;
import model.Game;
import model.HeroCard;
import model.Player;
import model.PowerCard;
import view.GameView;
import view.HomeView;
import view.StartView;
import view.WinView;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class GameController extends Application {
	
	// Private Variables
	private Stage primaryStage;
	private Game myGame;
	private CardFactory cf;
	private List<Card> p1DeckChosenToPlay;
	private List<Card> p2DeckChosenToPlay;
	private List<Card> p1StartingDeck;
	private List<Card> p2StartingDeck;
	private SuperTeam p1TeamChosen;
	private SuperTeam p2TeamChosen;
	private Random randP2;
	
	private final int paneHeight = 800; // height of scene main pane
	private final int paneWidth = 1400; // width of scene main pane
	
	// Views
	private HomeView homeView;
	private GameView gameView;
	private StartView startView;
	private WinView winView;
	
	
	public GameController() {
		myGame = Game.getInstance();
		cf = new CardFactory();
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		setupScenes();
		showStartScene();
		primaryStage.show();
	}
	
	public boolean isPlayerDead() {
		return myGame.isPlayerDead();
	}
	
	public boolean isOpponentDead() {
		return myGame.isOpponentDead();
	}
	
	// Check if any of player's heroes are dead
	public List<String> findDeadHeroes() {
		return myGame.findDeadHeroes();
	}
	
	// Clear the dead people list once they have been 
	// cleared in the front-end
	public void clearDeadHeroes() {
		myGame.getPlayer1().getDeadPeeps().clear();
	}
	
	// Check if any of opponent's heroes are dead
	public List<String> findDeadVillains() {
		return myGame.findDeadVillains();
	}
	
	// Clear the dead people list once they have been 
	// cleared in the front-end
	public void clearDeadVillains() {
		myGame.getPlayer2().getDeadPeeps().clear();
	}
	
	// Attack the opponent directly
	public boolean attackOpponentDirectly(ImageView heroImage) {
		return myGame.attackOpponentDirectly(heroImage);
	}
	
	// Attack the opponent's hero
	public boolean attackOpponentHero(ImageView heroImage, 
			ImageView villainImage) {
		return myGame.attackOpponentHero(heroImage, villainImage);
	}
	
	// Enough Mana (1 Mana) - Output to the front-end
	// that there is no mana if returned false
	public boolean enoughMana() {
		return myGame.enoughMana();
	}
	
	public void makeOpponentMove(boolean isFirstMove) {
		/* Make the Opponent only have hero cards.DONE
		 * If first move, draw 5 cards.
		 * Else, draw a card in every turn if possible
		 * Put a card on the field
		 * 
		 */ 
		Player p2 = myGame.getPlayer2();
		randP2 = new Random();
		if (isFirstMove) {
			p2 = myGame.getPlayer2();
			List<Card> p2Hand = p2.drawFiveCards();
			// r is a number between 2 and 3, the number
			// of cards the opponent will put on the field
			int r1 = randP2.nextInt(1) + 2;
			for (int i = 0; i < r1; i++) {
				// Add to the field in back-end
				// and front-end
				HeroCard hc = (HeroCard) p2Hand.get(i);
				p2.playOpponentCard(hc);
				gameView.setOppFieldCard(hc);
			}
		} else { 
			/* Not first move. 
			 * Possible Moves: 
			 * 1. Draw 1 card, play the card, 
			 * 	  attack the opponent if possible
			 * 2. If the player has no field heroes,
			 * 	  try attacking the opponent directly
			 *    (2 mana), then draw a card.
			 * 
			 */
			int index = 0;
			// Try attacking opponent directly
			if (myGame.getPlayer1().getField().size() == 0) {
				index = p2.getHealthiestHero();
				p2.attackOpponent(myGame.getPlayer1(), index);
//				Card drawnCard = p2.drawCard();
//				// if card is not null, it means draw was successful
//				if (drawnCard != null) {
//					// try playing it onto the field
//					p2.playOpponentCard((HeroCard) drawnCard);
//					gameView.setOppFieldCard((HeroCard) drawnCard);
//				}
			} else {
				Card drawnCard = p2.drawCard();
				if (p2.getHand().size() <= 1) {
					index = 0;
				} else {
					index = randP2.nextInt(p2.getHand().size()-1);
				}
				System.out.println("Index of Opponent Card drawn: "
						+ index);
				Card toPlay = p2.getHand().get(index);
				// if card is not null, it means draw was successful
				if (toPlay != null) {
					// try playing it onto the field
					p2.playOpponentCard((HeroCard) toPlay);
					gameView.setOppFieldCard((HeroCard) toPlay);
					// try attacking the player
					int r2 = p2.getStrongestHero();
					List<Card> playerField = myGame.getPlayer1().getField();
					int r3; // random hero from hero field
					if (playerField.size() <= 1) {
						r3 = 0;
					} else {
						r3 = randP2.nextInt(playerField.size()-1);
					}
					p2.attackHero(myGame.getPlayer1(), r2, r3);
				}
			}
		}
	}
	
	public Card playCard(Image image) {
		return myGame.playCard(image);
	}
	
	// Only used in the start of game
	public boolean drawFiveCards() {
		List<Card> cards = myGame.getPlayer1().drawFiveCards();
		for (int i = 0; i < cards.size(); i++) {
			gameView.drawCard(cards.get(i));
		}
		return true;
	}
	
	public boolean drawCard() {
		Card drawnCard = myGame.getPlayer1().drawCard();
		if (drawnCard != null) {
			gameView.drawCard(drawnCard);
			return true;
		}
		return false;
	}
	
	// Set up the deck that the user chose
	public void setUpDecks(SuperTeam deckChosen) {
		// Set player 1's deck
		if (deckChosen.getName().equals("Marvel Heroes")) {
			p1DeckChosenToPlay = cf.setUpMarvelHeroes();
		} else if (deckChosen.getName().equals("Marvel Villains")) {
			p1DeckChosenToPlay = cf.setUpMarvelVillains();
		} else if (deckChosen.getName().equals("The Avengers")) {
			p1DeckChosenToPlay = cf.setUpMcuHeroes();
		} else if (deckChosen.getName().equals("Thanos and Friends")) {
			p1DeckChosenToPlay = cf.setUpMcuVillains();
		} else if (deckChosen.getName().equals("X-Men")) {
			p1DeckChosenToPlay = cf.setUpXMenHeroes();
		} else if (deckChosen.getName().equals("Brotherhood of Mutants")) {
			p1DeckChosenToPlay = cf.setUpXMenVillains();
		}
		p1StartingDeck = cf.getPlayerStartingDeck(p1DeckChosenToPlay, cf.setUpPowerUps());
		p1TeamChosen = deckChosen;
		myGame.getPlayer1().setDeck(p1StartingDeck);
		// Set player 2's deck to be all HeroCards
		Random rand = new Random();
		int p2Index = rand.nextInt(5);
		if (p2Index == 0) { // Marvel Heroes
			p2DeckChosenToPlay = cf.setUpMarvelHeroes();
		} else if (p2Index == 1) { // Marvel Villains
			p2DeckChosenToPlay = cf.setUpMarvelVillains();
		} else if (p2Index == 2) { // MCU Heroes
			p2DeckChosenToPlay = cf.setUpMcuHeroes();
		} else if (p2Index == 3) { // Thanos And Friends
			p2DeckChosenToPlay = cf.setUpMcuVillains();
		} else if (p2Index == 4) { // X-Men
			p2DeckChosenToPlay = cf.setUpXMenHeroes();
		} else if (p2Index == 5) { // Brotherhood of Mutants
			p2DeckChosenToPlay = cf.setUpXMenVillains();
		}
		p2StartingDeck = cf.getOppStartingDeck(p2DeckChosenToPlay);
		p2TeamChosen = cf.getAssociatedTeam(p2Index);
		myGame.getPlayer2().setDeck(p2StartingDeck);
	}
	
	// set up all scenes for the application
	public void setupScenes() {
		homeView = new HomeView(this);
		gameView = new GameView(this);
		startView = new StartView(this);
		winView = new WinView(this);
	}
	
	// Reset Player's Mana
	public void resetPlayerMana() {
		myGame.resetPlayerMana();
	}
	
	// Reset Opponent's Mana
	public void resetOpponentMana() {
		myGame.resetOpponentMana();
	}
	
	public IntegerProperty getPlayerHealth() {
		return myGame.getPlayer1().getHealthPointsProp();
	}
	
	public IntegerProperty getPlayerMana() {
		return myGame.getPlayer1().getManaProp();
	}
	
	public IntegerProperty getOppHealth() {
		return myGame.getPlayer2().getHealthPointsProp();
	}
	
	public IntegerProperty getOppMana() {
		return myGame.getPlayer2().getManaProp();
	}
	
	// Display the start scene
	public void showStartScene() {
		primaryStage.setScene(startView.getGameScene());
	}
	
	// Display the home scene
	public void showHomeScene(){
		primaryStage.setScene(homeView.getGameScene());
	}
	
	// Display the Win/Lose View
	public void showWinScene(boolean hasWon, int numAttacks) {
		winView.setPlayerWon(hasWon);
		winView.setTotalNumClicksForWin(numAttacks);
		winView.makeScene();
		primaryStage.setScene(winView.getGameScene());
	}
	
	// Display the home scene
	public void resetToHomeScene() {
		// Reset game and views
		myGame = Game.getNewInstance();
		cf = new CardFactory();
		gameView = new GameView(this);
		primaryStage.setScene(homeView.getGameScene());
	}
	
	// display the gameboard scene with relevant data
	public void showGameScene() {
		gameView.setPlayerTeam(p1TeamChosen);
		gameView.setOpponentTeam(p2TeamChosen);
		gameView.setCenter();
		gameView.setRight();
		gameView.setUpClickEvents();
		gameView.setUpButtonEvents();
		gameView.setUpDragEvents();
		primaryStage.setScene(gameView.getGameScene());
	}
	
	public void getDeckSize() {
		System.out.println("Deck Size: " + myGame.getPlayer1().getDeck().size());
	}

	/**
	 * @return the paneHeight
	 */
	public int getPaneHeight() {
		return paneHeight;
	}

	/**
	 * @return the paneWidth
	 */
	public int getPaneWidth() {
		return paneWidth;
	}

	/**
	 * @return the primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @param primaryStage the primaryStage to set
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * @return the cf
	 */
	public CardFactory getCf() {
		return cf;
	}

	/**
	 * @param cf the cf to set
	 */
	public void setCf(CardFactory cf) {
		this.cf = cf;
	}
	
	
}
