
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Represents that thinking player of the team
 * It analyzes the grid to guess which column is the best to play
 */
public class hard_player extends computerized_player{
    
    // Grid of the current game
    private main_grid grid;
    // Holds the generated numbers for each column
    int[] columns = {0,0,0,0,0,0,0};
    // a set of the columns that are overflowing
    private HashSet<Integer> maxedColumns = new HashSet();
    Random rand = new Random();
    
    public hard_player(String playerName) {
        super(playerName);
    }
    
    /**
     * Resets all columns to zero after each play
     */
    public void resetColumns(){
        for(int i = 0; i < 7; i++) columns[i] = 0;
    }
    
    /**
     * give ratings to the columns according to the status of the grid
     * Relies heavily on methods that are also used in the GamePlay class
     * 
     * @param grid the current grid that represents the game status
     * @return n the column number that is best to play
     */
    @Override
    public int ratePlaces(main_grid grid){
        this.grid = grid;
        // check horizonatally and vertically only, other checks might add complexity and affect performance
        checkHorizontal();
        checkVertical();
//        checkPositiveDiagonal();
//        checkNegativeDiagonal();
        
        randomizeColumns();
        capMaxedColumns();
        getMaxedColumns();
        int n = maxValue(columns);
        resetColumns();
        return n;
    }
    
    /**
     * Gets maxed out columns and sets their value to zero to avoid overfitting to one column
     */
    private void getMaxedColumns(){
        for(int i = 0; i < grid.getGrid()[5].length; i++){
            System.out.println(grid.getGrid()[5][i]);
            if(!grid.getGrid()[5][i].isEmpty()) columns[i] = 0;
        }

    }
    
    /**
     * Rates four discs at a time
     * Edits the columns array as it goes through the discs
     * @param four 
     */
    private void rateFourPlaces(List<disc> four){
        
        // Defence first
        // Looks for places where the opponent is close to winning
        int countConsecutives = 0;
        for(int i = 0; i < 4; i++){            
            if(four.get(i).equals(winningListForOpponent.get(i))){
                countConsecutives++;
            }
            else if(countConsecutives == 3)
                columns[four.get(i).getCoordinates().getY()] += rand.nextInt(10);
        }

        // Offence
        // Looks for places to attack, for example consecutive discs
        countConsecutives = 0;
        for(int i = 0; i < 4; i++){
            if(!four.get(i).isEmpty() && four.get(i).getCoordinates().getX() == 5) maxedColumns.add(i);
            if(four.get(i).equals(winningListForSelf.get(i))){
                columns[four.get(i).getCoordinates().getY()] += rand.nextInt(5);
                countConsecutives++;
            }
            else if(countConsecutives > 2)
                columns[four.get(i).getCoordinates().getY()] -= rand.nextInt(10);
        }        
    }
    
    /**
     * 
     * @param array columns array
     * @return the index of the maximum value in the columns array
     */
    public int maxValue(int array[]){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
          list.add(array[i]);
        }
       return list.indexOf(Collections.max(list));
    }
    
    /**
     * Adds an element of variety to the set to get new results
     */
    public void randomizeColumns(){
        Random rand = new Random();
        for(int i = 0; i < 7; i++){
            columns[i] += 10;
            columns[i] *= rand.nextInt(10);
        }
    }
    
    
    
    /**
     * Obtain and check all the horizontal sets of four
     */
    public void checkHorizontal(){
        System.out.println("Checking Horizontal");
        ArrayList<disc> inds = new ArrayList<disc>();
        for(disc[] i: grid.getGrid()){
            for(disc a: i){
                inds.add(a);
            }
            checkConsecutive(inds);
            inds.clear();
        }
    }
    
    /**
     * Obtain and check all the vertically sets of four
     */
    public void checkVertical(){
        System.out.println("Checking Vertical");
        ArrayList<disc> inds = new ArrayList<disc>();
        for(int i = 0; i<grid.getGrid()[0].length; i++){
            for(int j = 0; j<grid.getGrid().length; j++){
                inds.add(grid.getGrid()[j][i]);
            }
            checkConsecutive(inds);
            inds.clear();
        }
    }
    
    /**
     * Obtain and check all the positive diagonal sets of four
     */
    public void checkPositiveDiagonal(){
        System.out.println("Checking Diagonal");
        int n = 7;
        int m = 6;
        ArrayList<disc> inds = new ArrayList<disc>();
        for(int j=n-1; j>=0; j--){
            for(int k=0; k<m; k++){
                if((j + k) < n){
                    inds.add(grid.getGrid()[k][j + k]);
                } else {
                    break;
                }
            }
            checkConsecutive(inds);
            inds.clear();
        }
        for(int i=1; i<m; i++){
            for(int j=i, k=0; j<m && k<n; j++, k++){
                inds.add(grid.getGrid()[j][k]);
            }
            checkConsecutive(inds);
            inds.clear();
        }

    }
    
    
    /**
     * Obtain and check all the negative sets of four
     */
    public void checkNegativeDiagonal(){
        int n = 7;
        int m = 6;
        ArrayList<disc> inds = new ArrayList<disc>();
        for(int i = 0; i < Integer.min(n,m); i++){
            for(int j = 0; j <= i; j++){
                inds.add(grid.getGrid()[j][i-j]);
            }
            checkConsecutive(inds);
            inds.clear();
        }

        for(int i = 1; i < Integer.min(n, m); i++){
            for(int j = 0; (i + j) < Integer.max(n, m); j++){
                inds.add(grid.getGrid()[Integer.min(n, m)-j-1][j+i]);
            }
            checkConsecutive(inds);
            inds.clear();
        }
    }
    
    /**
     * checks and rates every consecutive value
     * @param inds 
     */
    public void checkConsecutive(ArrayList<disc> inds){
        System.out.println("Checking Consequtives");
        if(inds.size() < 4) return;
        for(int i = 0, j = 3; j < inds.size(); i += 1, j += 1){
            rateFourPlaces(inds.subList(i, j+1));
            
        }
    }

    private void capMaxedColumns() {
        for(int i: maxedColumns)
            columns[i] = 0;
    }
}


