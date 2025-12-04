package Level_3;

import javax.swing.*;
import java.awt.*;

/**
 * Concrete decorator to extend the spaceship decorator and add the lever component to the spaceship's
 * list of components in order to "decorate" the broken spaceship with the fixed lever.
 * @author Izzy Carlson
 */
public class LeverDecorator extends SpaceshipDecorator {
    private Image image;
    private boolean visibility;

    public LeverDecorator(Spaceship gleebusShip) {
        super(gleebusShip);
        this.image = new ImageIcon("src/Images/Puzzle3/P3_Lever.png").getImage();
        this.visibility = false;
    }

    /**
     * @return The lever's image
     */
    @Override
    public Image getImage() { return this.image; }

    /**
     * @param visibility Sets the current visibility of the lever
     */
    @Override
    public void setVisible(boolean visibility) { this.visibility = visibility; }

    /**
     * @return The current visibility of the lever
     */
    @Override
    public boolean getVisibility() { return this.visibility; }
}
