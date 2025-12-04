package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Handles JavaSwing set up for the Game Over card
 * @author Izzy Carlson
 */
public class GameOver extends JPanel {
    private Game parent;

    /**
     * Constructor to initialize all components for the Game Over screen
     * @param parent The parent JFrame that Game Over should be added to
     */
    public GameOver(Game parent) {
        this.parent = parent;

        // store everything on a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        // set ip panel to store game over image of gleebus
        JPanel gameOverPanel = new JPanel();
        ImageIcon gameOverImage = new ImageIcon("src/Images/Puzzle4/P4_GameOver.png");
        JLabel gameOverScreen = new JLabel(gameOverImage);
        gameOverPanel.add(gameOverScreen);
        gameOverPanel.setBounds(0,-10,1280,720);
        layeredPane.add(gameOverPanel, JLayeredPane.DEFAULT_LAYER);

        // set up a next card button to retry
        NextCard next = new NextCard();
        JButton restartButton = next.getNextCardButton(this.parent, "RESTART", 300, 50, "Level4", 18);

        // set up a quit button to exit
        JButton quitButton = new JButton("EXIT");
        quitButton.setPreferredSize(new Dimension(300, 50));
        quitButton.setFont(new Font("Dialog", Font.BOLD, 18));

        quitButton.addActionListener(e -> System.exit(0)); // exit program if user hits quit button

        // set up a panel to hold both buttons
        JPanel restart = new JPanel(new FlowLayout());
        restart.add(restartButton);
        restart.add(quitButton);
        restart.setOpaque(false);
        restart.setBounds(0,500,1280,100);
        layeredPane.add(restart, JLayeredPane.PALETTE_LAYER);

        // set up a panel and label to hold the title text
        JLabel title = new JLabel("Gleebus died in battle... Try again?");
        title.setFont(new Font("Dialog", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(0,100,1280,100);
        titlePanel.setOpaque(false);
        titlePanel.add(title);
        layeredPane.add(titlePanel, JLayeredPane.PALETTE_LAYER);
    }
}
