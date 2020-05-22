

import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.scene.layout.Pane;

/**
 * This is the main entry for the command line interface debugging tool
 * Running this file will start a while loop that will allow you to enter column names to play
 * It is setup on the hard mode of the AI, to play against someone else just switch the comments on lines 27 and 28
 * The entry point for the application, i.e. the class that extends Application is in FinalProject.java
 * ConnectFourGame and OTTOandTOOTGame inherit from GamePlay the rules, playing mechanism and the judge
 * Player is the class for any player, it has a computerized subclass which has two subclasses for the easy mode and the hard mode
 * Disc is a flexible class that can represent both configurations of our games
 * 
 * style.css has some minor formatting details
 * 
 */
public class project_main {
    

    public static void main(String[] args){
        System.out.println("Main!!");
        // Initialize a game
        game_play game = new connect_four_game(6, 7, "Navsimran", "Jarvis", new Pane());
        // Print the grid with its coordinates for reference
        System.out.println(game.getGrid().toString());
        // Choose gaming mode
        game.computerizePlayerTwo();
        // Uncomment this line to play both roles
//        game.deComputerizePlayerTwo("Friday");
        
        int input = 0;
        // Keep playing till game ends
        while(!game.hasGameEnded()){
            System.out.println("Enter the column number please");
            Scanner scanner=new Scanner(System.in);
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer number");
            }
            System.out.println(input);
            game.playTurn(input);
            // Print the status of the grid
            System.out.println(game.getGrid().contentsToString());
        }
        
        
    }
}
