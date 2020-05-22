

import java.util.Random;



/**
 * This player gets the next column with a random number generator
 */
public class easy_player extends computerized_player {
    
    public easy_player(String playerName) {
        super(playerName);
    }
    
    
    @Override
    public int ratePlaces(main_grid grid){
        Random rand = new Random();
        int  n = rand.nextInt(6);
        return n;
    }
    
}
