

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 * Initializes the underlying grid
 * Puts a grid on the view
 * Adds the disc holder
 * Useful printing methods
 */
public class main_grid {
    private int columns;
    private int rows;
    private disc[][] mainGrid;
    private final int discRadius = 80;
    private Shape gridShape;
    private Pane discRoot = new Pane();
    
    public main_grid(int dimV, int dimH, Pane gameplayPane){
        this.rows = dimV;
        this.columns = dimH;
        this.mainGrid = new disc[dimV][dimH];
        initializeGrid();
        gameplayPane.getChildren().clear();
        gridShape = makeGrid(gameplayPane);
        System.out.println("MainGrid constructed!");
    }
    
    private void initializeGrid(){
        for(int i = 0; i < mainGrid.length; i++){
            for(int j = 0; j < mainGrid[i].length; j++){
                mainGrid[i][j] = new disc(i, j);
            }
        }
    } 
    
    public void setBackgroundColour(Color backgroundColour){
        gridShape.setFill(backgroundColour);
    }
    
    public int getLength(){
        return mainGrid.length;
    }
    
    public disc[][] getGrid(){
        return mainGrid;
    }
    
    @Override
    public String toString(){
        String res = "";
        for(disc[] a: mainGrid){
            for(disc i: a){
                res = res + i.getCoordinates() + " ";
            }
            res += "\n";
        }
        return res;
    }
    
    public String contentsToString(){
        String res = "";
        for(disc[] a: mainGrid){
            for(disc i: a){
                res = res + String.format("%s   ", i.getLabel());
//                res = res + i.getCoordinates() + " ";
            }
            res += "\n";
        }
        return res;
    }
    
    
    
    
    
    public Shape makeGrid(Pane gameplayPane){
        Shape shape = new Rectangle((columns + 1) * discRadius, (rows + 1) * discRadius);
        for(int y = 0; y < rows; y++){
            for(int x = 0; x < columns; x++){
                Circle circle = new Circle(discRadius / 2);
                circle.setCenterX(discRadius / 2);
                circle.setCenterY(discRadius / 2);
                circle.setTranslateX(x * (discRadius + 5) + discRadius / 4);
                circle.setTranslateY(y * (discRadius + 5) + discRadius / 4);
                
                shape = shape.subtract(shape, circle);
            }
        }
        
        shape.setFill(Color.BROWN);
        gameplayPane.getChildren().add(discRoot);
        gameplayPane.getChildren().add(shape);
        return shape;
    }   

    
    public static void main(String[] args){
        System.out.println("MainGrid started!");
        main_grid mainGrid = new main_grid(6,7, new Pane());
        
        System.out.println(mainGrid);
    }
}

