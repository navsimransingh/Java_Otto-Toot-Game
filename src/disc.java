
/**
 * A flexible and handy disc class 

 */
import java.util.Objects;
import javafx.scene.paint.Color;
public class disc implements disc_int{
    private String label;
    private Color color;
    private boolean labelVisibility;
    private boolean active;
    private coordinates coordinates;
    
    public disc(int x, int y){
        this.color = Color.TRANSPARENT;
        this.label = "";
        this.labelVisibility = false;
        this.active = false;
        this.coordinates = new coordinates(x,y);
    }
    
    public disc(Color color, String label){
        this.color = color;
        this.label = label;
    }
    
    public coordinates getCoordinates(){
        return coordinates;
    }
    
    public boolean isLabelVisible(){
        return labelVisibility;
    }
    
    @Override
    public void setColor(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }
    
    @Override
    public void setLabel(String label){
        this.label = label;
    }
    
    public void activate(){
        this.active = true;
    }
    
    public String getLabel(){
        return this.label;
    }
    
    public boolean isEmpty(){
        return label.isEmpty();
    }
    
    @Override
    public String toString(){
        return getLabel() + " ";
    }

    @Override
    public void setLabelVisibility(boolean activeLabel) {
        this.labelVisibility = activeLabel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final disc other = (disc) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }
    
    
}

