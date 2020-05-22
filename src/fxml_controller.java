

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class fxml_controller implements Initializable {
    
    game_play gamePlay;
    main_grid grid;
    ToggleGroup modeToggleGroup;
    ToggleGroup levelToggleGroup;
        
    @FXML
    private Label label;
    
    @FXML
    private Pane gameplayPane;
    
    @FXML
    private TextField playerOne;
    
    @FXML
    private TextField playerTwo;
    
    @FXML
    private ToggleButton oneOnOneToggle;
    
    @FXML
    private ToggleButton oneOnComputerToggle;
        
    @FXML
    private Label playerTurn;
    
    @FXML
    private Shape discOne;
    
    @FXML
    private Shape discTwo;
    
    @FXML
    private Group discGroupOne;
    
    @FXML
    private Group discGroupTwo;
    
    @FXML
    private Pane pane;
    
    @FXML
    private Pane Pane;
    
    /**
     * Start the connect four game
     * @param event 
     */
    @FXML
    private void startConnectFour(ActionEvent event){
        gamePlay = new connect_four_game(6, 7, playerOne.textProperty().getValue(), playerTwo.textProperty().getValue(), gameplayPane);
        gamePlay.setPlayerTurn(playerTurn);
        discGroupOne.setVisible(false);
        discGroupTwo.setVisible(false);
    }
    
    /**
     * Start the OTTO and TOOT game
     * @param event 
     */
    @FXML
    private void startOTTOFour(ActionEvent event){
        gamePlay = new otto_and_toot_game(6, 7, playerOne.textProperty().getValue(), playerTwo.textProperty().getValue(), gameplayPane);
        playerTurn.setText(playerOne.textProperty().getValue() + "'s turn");
        gamePlay.setPlayerTurn(playerTurn);
        discGroupOne.setVisible(true);
        discGroupTwo.setVisible(true);
    }
    
    
    @FXML
    private void computerizePlayerTwo(ActionEvent event){
        gamePlay.computerizePlayerTwo();
    }
    
    @FXML
    private void deComputerizePlayerTwo(ActionEvent event){
        gamePlay.deComputerizePlayerTwo(playerTwo.textProperty().getValue());
    }
    
    @FXML
    private void changeToEasyPlayer(ActionEvent event){
        gamePlay.changeToEasyMode();
    }
    
    @FXML
    private void changeToHardPlayer(ActionEvent event){
        gamePlay.changeToHardMode();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1VS1 and 1VScomputer toggle group 
        modeToggleGroup = new ToggleGroup();
        oneOnOneToggle.setToggleGroup(modeToggleGroup);
        oneOnComputerToggle.setToggleGroup(modeToggleGroup);        
        
        // The disc choices for the OTTO game
        discGroupOne.setVisible(false);
        discGroupTwo.setVisible(false);
        
        // Listeners for the OTTO game disc choices
        discOne.setOnMouseClicked((MouseEvent me) -> {
            gamePlay.currentPlayer.setDiscTypeForPlayer(new disc(Color.WHITE, "O"));
            discOne.setStroke(Color.RED);
            discTwo.setStroke(Color.TRANSPARENT);
            discTwo.setScaleX(1);
            discOne.setScaleX(1.1);
        });
        
        discTwo.setOnMouseClicked((MouseEvent me) -> {
            gamePlay.currentPlayer.setDiscTypeForPlayer(new disc(Color.WHITE, "T"));
            discTwo.setStroke(Color.RED);
            discOne.setStroke(Color.TRANSPARENT);
            discOne.setScaleX(1);
            discTwo.setScaleX(1.1);
        });
        
        
    }
        
        
        
      
    
}

