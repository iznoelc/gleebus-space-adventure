package Level_3;

import javax.swing.*;
import java.awt.*;

/**
 * Concrete decorator to extend the spaceship decorator and add the button panel component to the spaceship's
 * list of components in order to "decorate" the broken spaceship with the fixed button panel.
 * @author Izzy Carlson
 */
public class ButtonPanelDecorator extends SpaceshipDecorator {
    private Image image;
    private boolean visibility;

    public ButtonPanelDecorator(Spaceship gleebusShip) {
        super(gleebusShip);
        this.image = new ImageIcon("src/Images/Puzzle3/P3_ButtonPanel.png").getImage();
        this.visibility = false;
    }

    /**
     * @return The button panel's image
     */
    @Override
    public Image getImage() { return this.image; }

    /**
     * @param visibility to set for the button panel
     */
    @Override
    public void setVisible(boolean visibility) { this.visibility = visibility; }

    /**
     * @return The current visibility of the button panel
     */
    @Override
    public boolean getVisibility() { return this.visibility; }
}
