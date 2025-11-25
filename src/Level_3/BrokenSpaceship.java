package Level_3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Concrete component of the decorator; the base spaceship to be decorated with components (fixed pieces
 * of the spaceship.)
 * @author Izzy Carlson
 */
public class BrokenSpaceship implements Spaceship {
    private ArrayList<Spaceship> gleebusShip = new ArrayList<>();
    private Image image;
    String imageID;
    boolean visibility;

    public BrokenSpaceship(){
        this.image = new ImageIcon("src/Images/Puzzle3/P3_BaseSpaceship.png").getImage();
        this.visibility = true;
        this.imageID = "Broken Spaceship";
    }

    @Override
    public void addComponent() {
        System.out.println("Successfully added component");
        gleebusShip.add(this);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setVisible(boolean visibility) {
        this.visibility = visibility;
    }

    @Override
    public boolean getVisibility() {
        return visibility;
    }

    @Override
    public ArrayList<Spaceship> getComponents() {
        return gleebusShip;
    }
}
