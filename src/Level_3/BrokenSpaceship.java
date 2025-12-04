package Level_3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Concrete component of the decorator; the base spaceship to be decorated with components (fixed pieces
 * of the spaceship)
 * @author Izzy Carlson
 */
public class BrokenSpaceship implements Spaceship {
    private ArrayList<Spaceship> gleebusShip = new ArrayList<>();
    private Image image;
    String imageID;
    boolean visibility;

    /**
     * Constructor to initialize aspects of the base spaceship
     */
    public BrokenSpaceship(){
        this.image = new ImageIcon("src/Images/Puzzle3/P3_BaseSpaceship.png").getImage();
        this.visibility = true;
        this.imageID = "Broken Spaceship";
    }

    /**
     * Adds a component to Gleebus' ship by adding the component itself to the spaceship's array of parts
     */
    @Override
    public void addComponent() { gleebusShip.add(this); }

    /**
     * @return The base, broken spaceship's image
     */
    @Override
    public Image getImage() { return image; }

    /**
     * @param visibility to be set of the base, broken spaceship
     */
    @Override
    public void setVisible(boolean visibility) { this.visibility = visibility; }

    /**
     * @return The visibility of the base, broken spaceship
     */
    @Override
    public boolean getVisibility() { return visibility; }

    /**
     * @return All components of Gleebus' ship
     */
    @Override
    public ArrayList<Spaceship> getComponents() { return gleebusShip; }
}
