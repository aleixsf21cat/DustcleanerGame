package edu.uoc.uocleaner.view.cmd;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.uoc.uocleaner.controller.Game;
import edu.uoc.uocleaner.controller.GameMode;
import edu.uoc.uocleaner.model.LevelException;
import edu.uoc.uocleaner.model.Movable;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.VacuumException;

public class CmdApp {

	/**
	 * Game object that allows to manage the game.
	 */
	Game game;	
	
	/** Initializes a new game with the folder which contains the levels' configuration files and with the game mode "turns".	  
	 * @throws FileNotFoundException When the level's configuration file is not found. 
	 * @throws LevelException When there is a level exception/problem. 
	 * @throws VacuumException When there is an exception/problem with the vacuum cleaner. 
	 * @throws SpriteException When there is a Sprite exception/problem.
	 */
	public CmdApp() throws FileNotFoundException, LevelException, VacuumException, SpriteException {
		this.game = new Game("levels/",GameMode.TURNS);
	}
	
	/**
	 * Manages the idle process of the game.
	 * @throws FileNotFoundException When the level's configuration file is not found.
	 * @throws LevelException When there is a level exception/problem.
	 * @throws VacuumException When there is an exception/problem with the vacuum cleaner.
	 * @throws SpriteException When there is a Sprite exception/problem.
	 */
	public void launchGame() throws FileNotFoundException, LevelException, VacuumException, SpriteException {
		Scanner sc = new Scanner(System.in);
		
		while(game.nextLevel()) {			
			System.out.println("LEVEL "+game.getNumLevel());			
			System.out.println();
			// print board and keep accepting moves until game is over
			while (!game.isLevelBeaten()) {
				System.out.println(game.getBoardText());
				System.out.print("Total Score: "+game.getTotalScore());
				System.out.print(" | Level Score: "+game.getLevelScore());
				System.out.print(" | Capacity: "+(game.getVacuumRatioCapacity()*100)+"%");
				System.out.print(" | Turns: "+(game.getTurns()));
				System.out.print(" | Enter move: ");
				move(sc.next().charAt(0));				
				System.out.println();
				System.out.println(game.getBoardText());
				
				if(game.timesUp()) {
					System.out.println("Time's up!! You lose!!");
					System.out.println("Press enter to continue...");
					game.reload();
					sc.nextLine(); //To catch the previous "enter"
					sc.nextLine();
				}
			}			
			
			System.out.println("Congrats!! You have finished Level "+game.getNumLevel()+"!!");
			System.out.println("Press enter to continue...");				
			sc.nextLine();
			sc.nextLine();			
		}
		
		sc.close();
		
		System.out.println("Total Score: "+game.getTotalScore());
	}
	
	/**
	 * Move the vacuum cleaner according to the key pressed by the player.
	 * @param moveLetter Letter that the player has pressed.
	 * @throws VacuumException When there is an exception/problem with the vacuum cleaner.
	 * @throws SpriteException When there is a Sprite exception/problem.
	 * @throws LevelException When the turns/time is negative.
	 */
	private void move(char moveLetter) throws VacuumException, SpriteException, LevelException{
		
		switch(moveLetter) {
			case 'a':
			case 'A':
					game.moveVacuum(0,-Movable.SPEED);
					break;
			case 'w':
			case 'W':
					game.moveVacuum(-Movable.SPEED,0);
					break;
			case 'd':
			case 'D':
					game.moveVacuum(0,Movable.SPEED);
					break;
			case 's':
			case 'S':
					game.moveVacuum(Movable.SPEED,0);
					break;
		}
		
		game.update();
	}
	
	/**
	 * Main method: entry point of the program.
	 * @param args This parameter is not needed.
	 */
	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			CmdApp cmd = new CmdApp();		
			cmd.launchGame();			
		} catch (FileNotFoundException | LevelException | VacuumException | SpriteException e) {			
			e.printStackTrace();
		}
		
		System.out.println("Finishing... bye!!");
	}	
}