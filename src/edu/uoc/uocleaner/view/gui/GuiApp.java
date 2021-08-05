package edu.uoc.uocleaner.view.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class GuiApp extends Application {

	private Region rootLayout;	
	private Stage stage;
	
	@FXML
	private Pane tankPane;
	
	public static GuiApp main;
	
	@Override
	public void start(Stage primaryStage) throws IOException {		
		main = this;
		stage = primaryStage;
		stage.setTitle("UOCleaner");
		stage.setResizable(false);      
		createView("Welcome.fxml");    	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void createView(String view, int... text) throws IOException {
		// Load root layout from fxml file.
		FXMLLoader loader = new FXMLLoader();         
	    loader.setLocation(GuiApp.class.getResource(view));	    
	    rootLayout = (Region) loader.load();         
        // Show the scene containing the root layout.
	    
	    if(text.length>0) {
	    	//Game Over view
	    	GameOverController go = loader.getController();	    	
	    	go.setTotalScore(text[0]);
	    }
	    
        Scene scene = new Scene(rootLayout);  
        stage.setScene(scene);	    
        stage.show();        
	}	 
}