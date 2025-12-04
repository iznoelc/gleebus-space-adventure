package Game;

import Level_1.Level_1;
import Level_2.Level_2;
import Level_3.Level_3;
import Level_4.Level_4;

import javax.swing.*;
import java.awt.*;

/**
 * Main JFrame that utilizes a card layout to cycle through each level.
 * @author Izzy Carlson
 * @author Esperanza Paulino
 */
public class Game extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JPanel cards;

    /**
     * Constructor to initialize JFrame and a card layout to hold each level as well as start, backstory, game over,
     * and end cards.
     */
    public Game(){
        // set up the frame
        setTitle("Gleebus' Space Adventure");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // set frame icon
        ImageIcon frameIcon = new ImageIcon("src/Images/IconNoOutline.png");
        setIconImage(frameIcon.getImage());

        // initialize all level panels and then add them to the card layout
        JPanel startCard = new Start(this);
        JPanel backstoryCard = new Backstory(this);
        JPanel level1 = new Level_1(this);
        JPanel level2 = new Level_2(this);
        JPanel level3 = new Level_3(this);
        JPanel level4 = new Level_4(this);
        JPanel endCard = new End(this);
        JPanel gameOverCard = new GameOver(this);

        cards = new JPanel(cardLayout);
        cards.add(startCard, "Start");
        cards.add(backstoryCard, "Backstory");
        cards.add(level1, "Level1");
        cards.add(level2, "Level2");
        cards.add(level3, "Level3");
        cards.add(level4, "Level4");
        cards.add(endCard, "End");
        cards.add(gameOverCard, "GameOver");

        // set first card shown to the start screen
        cardLayout.show(cards, "Start");
        add(cards);
    }

    /**
     * @return The frame's card layout
     */
    public CardLayout getCardLayout(){ return this.cardLayout; }

    /**
     * @return The cards in the frame's card layout
     */
    public JPanel getCards(){ return this.cards; }

    /**
     * Main method to start the game and set the frame to visible
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Game demo = new Game();
            demo.setResizable(false);
            demo.setVisible(true);
        });
    }
}
