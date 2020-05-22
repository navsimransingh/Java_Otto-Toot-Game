
public class player {
    private String playerName;
    private disc discType;
    
    public player(String playerName){
        this.playerName = playerName;
    }
    
    /**
     * Sets the disc type that this player carries
     * Same as in picking a disc to play in real life
     * 
     * @param disc 
     * 
     */
    public void setDiscTypeForPlayer(disc disc){
        this.discType = disc;
    }
    
    /**
     * 
     * @return diskType: The disk type that this user carries
     */
    public disc getDiscTypeForPlayer(){
        return discType;
    }
    
    public String getName(){
        return playerName;
    }

    /**
     * A dummy method here but an essential one in the subclasses
     * 
     * @param gamePlay contains the grid and game status
     */
    void play(game_play gamePlay) {
        System.out.println(getName() + " is playing!");
    }
    
    @Override
    public String toString(){
        return getName();
    }
}

