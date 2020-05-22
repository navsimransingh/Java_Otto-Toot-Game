
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public class connect_four_game extends game_play {
    
    
    public connect_four_game(int row, int column, String playerOneName, String playerTwoName, Pane gameplayPane) {
        super(row, column, playerOneName, playerTwoName, gameplayPane);
        setDiscTypeForPlayerInitializer(new disc(Color.RED, "R"), playerOne);
        setDiscTypeForPlayerInitializer(new disc(Color.YELLOW, "Y"), playerTwo);
//        playerOne.setDiscTypeForPlayer(new Disc(Color.RED, "R"));
//        playerTwo.setDiscTypeForPlayer(new Disc(Color.YELLOW, "Y"));
        
        initializeWinningLists();
        makeColumns(gameplayPane);
        System.out.println("ConnectFourGame constructed!");
    }
    
    public void initializeWinningLists(){
        winningOne.add(new disc(Color.RED, "R"));
        winningOne.add(new disc(Color.RED, "R"));
        winningOne.add(new disc(Color.RED, "R"));
        winningOne.add(new disc(Color.RED, "R"));
        winningTwo.add(new disc(Color.YELLOW, "Y"));
        winningTwo.add(new disc(Color.YELLOW, "Y"));
        winningTwo.add(new disc(Color.YELLOW, "Y"));
        winningTwo.add(new disc(Color.YELLOW, "Y"));
    }
    
    @Override
    public void activateDisc(disc disc, player player){
        disc.activate();
        disc.setColor(player.getDiscTypeForPlayer().getColor());
        disc.setLabel(player.getDiscTypeForPlayer().getLabel());
    }
    
    
    @Override
    public Group getDisc(){
        Group rootGroup = new Group();
        Circle disc = new Circle(40);
        disc.setFill(currentPlayer.getDiscTypeForPlayer().getColor());
        rootGroup.getChildren().add(disc);
        return rootGroup;
    }
    
    public static void main(String[] args){
        System.out.println("Main!!");
        game_play game = new connect_four_game(6, 7, "Navsimran", "Jarvis", new Pane());
        System.out.println(game.getGrid().toString());
//        game.computerizePlayerTwo();
        game.deComputerizePlayerTwo("Friday");
        int input = 0;
        boolean validNumber = false;
        while(!game.hasGameEnded()){
            Scanner scanner=new Scanner(System.in);
            validNumber=true;
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer number");
                validNumber=false;
            }
            System.out.println(input);
            game.playTurn(input);
            System.out.println(game.getGrid().contentsToString());
        }
        
        
    }
        
}

