
import java.util.ArrayList;
import java.util.HashSet;
/**
 * An abstract class representing the basis for a computerized player for other players to inherit from
 * 
 */

public abstract class computerized_player extends player {
    protected ArrayList<disc> winningListForSelf;
    protected ArrayList<disc> winningListForOpponent;
    
    public computerized_player(String playerName) {
        super(playerName);
    }
    
    @Override
    public void play(game_play gamePlay){
        System.out.println("Computer is playing");
        winningListForSelf = gamePlay.winningTwo;
        winningListForOpponent = gamePlay.winningOne;
        if(!gamePlay.playTurn(ratePlaces(gamePlay.getGrid())))
            gamePlay.playTurn(ratePlaces(gamePlay.getGrid()));
    }
    
    public abstract int ratePlaces(main_grid grid);
    
}

