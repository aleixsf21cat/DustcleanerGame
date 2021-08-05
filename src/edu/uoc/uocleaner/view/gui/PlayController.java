package edu.uoc.uocleaner.view.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.uoc.uocleaner.controller.Game;
import edu.uoc.uocleaner.controller.GameMode;
import edu.uoc.uocleaner.model.LevelException;
import edu.uoc.uocleaner.model.Movable;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.VacuumException;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class PlayController {

	public static final int CANVAS_WIDTH = 1024;
	public static final int CANVAS_HEIGHT = 640;
	
	private Game game;
	private AnimationTimer timer;
	
	@FXML
	private Pane canvas;
	
	@FXML
	private Button ReloadButton;
	
	@FXML
	private ProgressBar capacity;
	
	@FXML
	private Label totalScore;
	
	@FXML
	private Label levelScore;
	
	@FXML
	private Label time;
	
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
	 * @throws IOException 
	 * @throws VacuumException 
	 * @throws LevelException 
     */
    @FXML
    private void initialize() throws IOException, LevelException, VacuumException, SpriteException {    	
    	game = new Game("levels/", GameMode.TIME);    	
    	   	
    	canvas.setOnKeyPressed(e -> {
	        try {
	        	switch(e.getCode()){	        
	        		case LEFT:
							game.moveVacuum(0,-Movable.SPEED);
							break;
					case UP:
							game.moveVacuum(-Movable.SPEED,0);
							break;
					case RIGHT:
							game.moveVacuum(0,Movable.SPEED);
							break;					
					case DOWN:
							game.moveVacuum(Movable.SPEED,0);
							break;
					default:
							break;
	        	}
	        }catch(VacuumException | SpriteException ex) {
	        	ex.printStackTrace();
	        }
	    });
    	
    	timer = new AnimationTimer() { 
    		public void handle(long now) {
     	   		try {
					update();
				} catch (FileNotFoundException | LevelException | VacuumException | SpriteException e) {					
					e.printStackTrace();
				}	
        	}
    	};
    	
    	if(game.nextLevel())
    		timer.start();
    }
    
    private void update() throws FileNotFoundException, LevelException, VacuumException, SpriteException  {		
		if(!game.isLevelBeaten()) {
			//We are playing in the current level
			capacity.setProgress(game.getVacuumRatioCapacity());
			capacity.getStyleClass().clear();
			capacity.getStyleClass().addAll("progress-bar");
			
			if(capacity.getProgress()>=0.9) {    				
				capacity.getStyleClass().add("capacity-full");	
			}else if(capacity.getProgress()>=0.8) {
				capacity.getStyleClass().add("capacity-quasi-full");
			}else {
				capacity.getStyleClass().add("capacity-empty");										
			}
			
			game.update();
			levelScore.setText(String.valueOf(game.getLevelScore()));
			totalScore.setText(String.valueOf(game.getTotalScore()));
			time.setText(String.valueOf(game.getTime()));				
			paint();	
			
			if(game.timesUp()) {
				//Finished because time's up, we have to reload			
				levelScore.setText(String.valueOf(game.getLevelScore()));
				totalScore.setText(String.valueOf(game.getTotalScore()));										
				game.reload();								
				paint();
			}	
		}else {
			if(!game.nextLevel()){
				//Go to Game Over view
     	   		timer.stop();		        		
    			try {
    				GuiApp.main.createView("GameOver.fxml",game.getTotalScore());
    			} catch (IOException e) {
    				e.printStackTrace();
    				System.exit(2);
    			}
			}
		}			
     }	   	
      
	
    private void paint() {
    	ObservableList<Node> nodeList =  FXCollections.observableArrayList();        
        
    	for(var item : game.getVisibleSprites()) {
    		ImageView sprite = new ImageView(new Image("file:images/"+item.getSymbol().getImage()));
    		sprite.setTranslateX(64*item.getColumn());
    		sprite.setTranslateY(64*item.getRow());
    		nodeList.addAll(sprite);
    	}
    	canvas.getChildren().clear();
    	canvas.getChildren().addAll(nodeList);
    	
    	canvas.setBackground(  		
        		new Background(new BackgroundImage(new Image("file:images/"+game.getLevelBackground(),CANVAS_WIDTH,CANVAS_HEIGHT,false,true),
        		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        		          BackgroundSize.DEFAULT)));
    	
    	canvas.requestFocus(); //To capture the key event    	
    }   
    
    
    @FXML
    private void reload() throws IOException, LevelException, VacuumException, SpriteException {   
    	
    	this.game.reload(); // To reload the game
    	
    	}
	    
    }
    
    
