/**
 * Handles set-up for the start screen.
 * @author Izzy Carlson
 */
package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Handles the JavaSwing set up for the start card
 * @author Izzy Carlson
 */
public class Start extends JPanel {
    JFrame parent;

    /**
     * Constructor to set up the start card.
     * @param parent the Game JFrame
     */
    public Start(Game parent){
        this.parent = parent;
        setLayout(new BorderLayout());

        // sets up the layered pane to store logo and button on
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        // drawing panel to draw the logo on, set as default layer of layered pane
        DrawPanel drawingPanel = new DrawPanel();
        drawingPanel.setBounds(0,0,1280,720);
        layeredPane.add(drawingPanel, JLayeredPane.DEFAULT_LAYER);

        // set up the START button
        NextCard nextCard = new NextCard();
        JPanel nextCardButtonPanel = new JPanel(new FlowLayout());
        JButton startButton = nextCard.getNextCardButton(parent, "START", 200, 75, "Backstory", 24);
        nextCardButtonPanel.setOpaque(false);
        nextCardButtonPanel.setBounds(0, 450, 1280, 100); // position at top
        nextCardButtonPanel.add(startButton);
        layeredPane.add(nextCardButtonPanel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * Handles drawing the logo.
     */
    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image logo = new ImageIcon("src/Images/Logo.png").getImage();
            g.drawImage(logo,148, 100, this);
        }
    }
}
