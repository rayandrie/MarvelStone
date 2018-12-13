/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */

public class StartController extends Application {

	private GameController gameController;
	private Stage stage;
	
    @FXML
    private Pane mainPane;

    @FXML
    private HBox titleBox;

    @FXML
    private Label title;

    @FXML
    private HBox buttonBox;

    @FXML
    private Button playButton;

    public static void main(String[] args) {
    	launch(args);
    }
    
    public StartController() {
		System.out.println("Starting Controller Constructor!");
		// this happens first before @FXML controls are injected
	}

    public void initialize() {
    	System.out.println("Starting Controller Initialization");
    	// this happens after the @FXML controls are injected
    	playButton.setOnAction(event -> {
    		// Go to Home Scene
    		gameController.showHomeScene();
    	});
    	playButton.setOnMouseEntered(event -> {
			playButton.setCursor(Cursor.HAND);
			event.consume();
		});
		playButton.setOnMouseExited(event -> {
			playButton.setCursor(Cursor.DEFAULT);
			event.consume();
		});
		mainPane.setId("startView");
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StartView.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root, 1400, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Add new controllers
			gameController = new GameController();
			stage = primaryStage;
			gameController.setPrimaryStage(stage);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    
}