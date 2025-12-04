package Level_3;

import javax.swing.*;
import java.awt.*;

/**
 * Concrete decorator to extend the spaceship decorator and add the screen component to the spaceship's
 * list of components in order to "decorate" the broken spaceship with the fixed screen.
 * @author Izzy Carlson
 */
public class ScreenDecorator extends SpaceshipDecorator {
    private Image image;
    private boolean visibility;

    public ScreenDecorator(Spaceship gleebusShip) {
        super(gleebusShip);
        this.image = new ImageIcon("src/Images/Puzzle3/P3_Screen.png").getImage();
        this.visibility = false;
    }

    /**
     * @return The screen's image
     */
    @Override
    public Image getImage() { return this.image; }

    /**
     * @param visibility The visibility to set the screen to
     */
    @Override
    public void setVisible(boolean visibility) { this.visibility = visibility; }

    /**
     * @return The current visibility of the screen
     */
    @Override
    public boolean getVisibility() {
        return this.visibility;
    }
}
