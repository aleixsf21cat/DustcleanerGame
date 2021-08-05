package edu.uoc.uocleaner.view.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameOverController {

	@FXML
	private Button buttonStart;
	
	@FXML
	private Label totalScore;
	
    public void setTotalScore(int totalScore) {    	
		this.totalScore.setText(totalScore+" points");
    }
	
	@FXML
	public void back() {		
		try{
			GuiApp.main.createView("Welcome.fxml");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void exit() {		
		System.exit(0);		
	}	
}