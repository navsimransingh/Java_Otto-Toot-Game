

import java.util.ArrayList;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This is where all the logic of the application happens
 * With a reference to the main grid, it can play turns, manage players, judge, generate views
 */
public abstract class game_play {
    protected player playerOne;
    protected player playerTwo;
    protected player currentPlayer;
    private main_grid grid;
    private Pane gameplayPane;
    private disc discTypeForSecondPlayer;
    private Label playerTurn;
    private static final int DISC_RADIUS = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private boolean gameEnded;
    
    // Disc holder
    private Pane discRoot = new Pane();
    
    // Winning lists of player one and two
    protected ArrayList<disc> winningOne = new ArrayList<disc>();
    protected ArrayList<disc> winningTwo = new ArrayList<disc>();
    
    
    //Default initializer for the whole game
    public game_play(int rows, int columns, String playerOneName, String playerTwoName, Pane gameplayPane){
        grid = new main_grid(rows, columns, gameplayPane);
        playerOne = new player(playerOneName);
        playerTwo = new player(playerTwoName);
        currentPlayer = playerOne;
        this.gameplayPane = gameplayPane;
        System.out.println("GamePlay constructed!");
    }
    
    // Abstract methods that have to decided by each game
    abstract void activateDisc(disc disc, player player); 
    abstract Group getDisc();
    
    /**
     * 
     * @return the current grid of the game
     */
    public main_grid getGrid(){
        return grid;
    }
    
    /**
     * Turn the second player into a computer
     */
    public void computerizePlayerTwo(){
        playerTwo = new hard_player("Jarvis");
        playerTwo.setDiscTypeForPlayer(discTypeForSecondPlayer);
    }
    
    /**
     * Turn the second player back into manual mode
     */
    public void deComputerizePlayerTwo(String playerTwoName){
        playerTwo = new player(playerTwoName);
        playerTwo.setDiscTypeForPlayer(discTypeForSecondPlayer);
    }
    
    
    
    /**
     * Change to easy level player
     */public void changeToEasyMode(){
        playerTwo = new easy_player("Randy");
        playerTwo.setDiscTypeForPlayer(discTypeForSecondPlayer);
    }
    
     
     /**
     * Turn the second player into a computer
     */
    public void changeToHardMode(){
        playerTwo = new hard_player("Jarvis");
        playerTwo.setDiscTypeForPlayer(discTypeForSecondPlayer);
    }
    
    /**
     * Keep scrolling down till the point where the disc is activated
     * @param col column where to drop the disc
     * @return true if disc was dropped, usefull for the auto player
     */
    public boolean playTurn(int col){
        boolean dropped = false;
        for(int i=0; i<grid.getLength(); i++){
            if(grid.getGrid()[i][col].isEmpty() == true){
                activateDisc(grid.getGrid()[i][col], currentPlayer);
                System.out.println("Player " + currentPlayer.getName() + " Played");
                placeDisc(i, col);
                if(checkWin()){
                    endGame();
                } else {
                togglePlayer();
                }
                dropped = true;
                break;
                
            }
        }
        return dropped;
        
    }
    
    /**
     * Game Over!
     */
    public void endGame(){
        System.out.println(currentPlayer.getName() + " Won!!!");
        if(playerTurn != null) playerTurn.setText(currentPlayer.getName() + " Won!!!");
        gameEnded = true;
    }
    
    public boolean hasGameEnded(){
        return gameEnded;
    }
    
    /**
     * Change current player to the other one and start the play function
     */
    public void togglePlayer(){
        currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
        if(playerTurn != null) playerTurn.setText(currentPlayer.getName() + "'s turn");
        currentPlayer.play(this);
    }
    
    /**
     * Pick the disc of the player
     * @param disc
     * @param player 
     */
    public void setDiscTypeForPlayerInitializer(disc disc, player player){
        player.setDiscTypeForPlayer(disc);
        discTypeForSecondPlayer = disc;
    }
    
    /**
     * Edit player turn view 
     * @param playerTurn 
     */
    public void setPlayerTurn(Label playerTurn){
        this.playerTurn = playerTurn;
    }
    
    /**
     * Check all cells for a winning series
     * @return 
     */
    public boolean checkWin(){
        return checkHorizontal() || checkVertical() || checkPositiveDiagonal() || checkNegativeDiagonal();
    }
    
    
    
    public boolean checkHorizontal(){
        ArrayList<disc> inds = new ArrayList<disc>();
        for(disc[] i: grid.getGrid()){
            for(disc a: i){
                inds.add(a);
            }
            if(checkConsecutive(inds)) return true;
            inds.clear();
        }
        return false;
    }
    
    public boolean checkVertical(){
        ArrayList<disc> inds = new ArrayList<disc>();
        for(int i = 0; i<grid.getGrid()[0].length; i++){
            for(int j = 0; j<grid.getGrid().length; j++){
                inds.add(grid.getGrid()[j][i]);
            }
            if(checkConsecutive(inds)) return true;
            inds.clear();
        }
        return false;
    }
    
    
    public boolean checkPositiveDiagonal(){
        ArrayList<disc> inds = new ArrayList<disc>();
        for(int j=COLUMNS-1; j>=0; j--){
            for(int k=0; k<ROWS; k++){
                if((j + k) < COLUMNS){
                    inds.add(grid.getGrid()[k][j + k]);
                } else {
                    break;
                }
            }
            if(checkConsecutive(inds)) return true;
            inds.clear();
        }
        for(int i=1; i<ROWS; i++){
            for(int j=i, k=0; j<ROWS && k<COLUMNS; j++, k++){
                inds.add(grid.getGrid()[j][k]);
            }
            if(checkConsecutive(inds)) return true;
            inds.clear();
        }

        return false;
    }
    
    public boolean checkNegativeDiagonal(){
        ArrayList<disc> inds = new ArrayList<disc>();
        for(int i = 0; i < Integer.min(COLUMNS,ROWS); i++){
            for(int j = 0; j <= i; j++){
                inds.add(grid.getGrid()[j][i-j]);
            }
            if(checkConsecutive(inds)) return true;
            inds.clear();
        }

        for(int i = 1; i < Integer.min(COLUMNS, ROWS); i++){
            for(int j = 0; (i + j) < Integer.max(COLUMNS, ROWS); j++){
                inds.add(grid.getGrid()[Integer.min(COLUMNS, ROWS)-j-1][j+i]);
            }
            if(checkConsecutive(inds)) return true;
            inds.clear();
        }
        return false;
    }
    
    /**
     * Evaluate consecutives four by four and compare them with the winning hands
     * @param inds list of consecutive cells
     * @return 
     */
    public boolean checkConsecutive(ArrayList<disc> inds){
        if(inds.size() < 4) return false;
        for(int i = 0, j = 3; j < inds.size(); i += 1, j += 1){
            if(inds.subList(i, j+1).equals(winningOne)){ 
                System.out.println("Player one won!");
                return true;
            }
            if(inds.subList(i, j+1).equals(winningTwo)){ 
                System.out.println("Player two won!");
                return true;
            }
        }
        
        return false;
    }
    
    
    
    
    public void showPlay(){
        System.out.print(grid);
    }
    
    
    /**
     * make sensitive rectangles along the columns to detect mouse movements and clicks
     * It also shows an indicator
     * @param gameplayPane
     * @return 
     */
    public List<Rectangle> makeColumns(Pane gameplayPane){
        List<Rectangle> list = new ArrayList<>();
        
        for(int x=0; x < COLUMNS; x++){
            Rectangle rect = new Rectangle(DISC_RADIUS, (ROWS + 1) * DISC_RADIUS);
            rect.setTranslateX(x * (DISC_RADIUS + 5) + DISC_RADIUS / 4);
            rect.setFill(Color.TRANSPARENT);
            
            Circle tempCircle = new Circle(40, Color.CORNFLOWERBLUE);
            tempCircle.setOpacity(0.5);
            tempCircle.setVisible(false);
            tempCircle.setCenterX(40);
            tempCircle.setTranslateX(x * (DISC_RADIUS + 5) + DISC_RADIUS / 4);
            tempCircle.setTranslateY(-40);
            
            rect.setOnMouseEntered(e -> tempCircle.setVisible(true));
            rect.setOnMouseExited(e -> tempCircle.setVisible(false));
            
            final int column = x;
            rect.setOnMouseClicked(e -> playTurn(column));
            gameplayPane.getChildren().add(tempCircle);
            list.add(rect);
        }
        gameplayPane.getChildren().add(discRoot);
        gameplayPane.getChildren().addAll(list);
        return list;
    }
    
    
    /**
     * put a disc in the right place and animate it going down
     * @param row
     * @param column 
     */
    private void placeDisc(int row, int column){
        System.out.println("Placing a disc");
        row = ROWS - row - 1;
        Group rootGroup = getDisc();
        discRoot.getChildren().add(rootGroup);
        rootGroup.setLayoutX(40);
        rootGroup.setLayoutY(40);
        rootGroup.setTranslateX(column * (DISC_RADIUS + 5) + 20);
        
        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), rootGroup);
        animation.setToY(row * (DISC_RADIUS + 5) + DISC_RADIUS / 4);
        animation.play();
        
    }
    
    public static void main(String[] args){
//        System.out.println();
    }
    
       
}

