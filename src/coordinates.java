/**
 * represents the location in column and row notation
 */

public class coordinates {
    private int x;
    private int y;
    
    public coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    @Override
    public String toString(){
        return "X: " + getX() + " "
                + "Y: " + getY();
    }
    
    public static void main(String[] args){
        System.out.println("Started Coordinates!");
        coordinates test = new coordinates(3,4);
        System.out.println(test);
    }
}


