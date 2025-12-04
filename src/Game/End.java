package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Handles JavaSwing setup for the ending card.
 * @author Izzy Carlson
 */
public class End extends JPanel {
    private Game parent;

    /**
     * Constructor to handle all component set up for the ending card
     * @param parent The parent JFrame that ending should be added to
     */
    public End(Game parent){
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

        // sets up the panel and text for the title announcing gleebus' arrival home
        JPanel endComponentPanel = new JPanel();
        endComponentPanel.setLayout(new BoxLayout(endComponentPanel, BoxLayout.Y_AXIS));
        JLabel endText = new JLabel("★ GLEEBUS HAS RETURNED HOME! ★");
        endText.setFont(new Font("Dialog", Font.BOLD, 40));
        endText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        endComponentPanel.add(endText);
        endComponentPanel.setBounds(0,0,1280,100);
        endComponentPanel.setOpaque(false);

        // set up exit button
        JButton endGameButton = new JButton("EXIT");
        endGameButton.setPreferredSize(new Dimension(150, 50));
        endGameButton.setFont(new Font("Dialog", Font.PLAIN, 24));
        endGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endGameButton.addActionListener(e -> System.exit(0)); // add listener that closes window when exit button is pressed
        endComponentPanel.add(endGameButton);
        layeredPane.add(endComponentPanel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * Handles drawing the ending screen image of Gleebus on his home planet
     */
    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image logo = new ImageIcon("src/Images/Ending.png").getImage();
            g.drawImage(logo,0, -10, this);
        }
    }
}
