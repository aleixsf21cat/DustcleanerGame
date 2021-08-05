package edu.uoc.uocleaner.view.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController {

	@FXML
	private Button buttonStart;
	
	@FXML
	public void start() {		
		try{
			GuiApp.main.createView("Play.fxml");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
