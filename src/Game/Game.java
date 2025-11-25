package Game;

import Level_1.Level_1;
import Level_2.Level_2;
import Level_3.Level_3;
import Level_4.Level_4;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    //variables for screen
    private JTextArea output_area;
    CardLayout cardLayout = new CardLayout();
    JPanel cards;

    public Game(){
        //game screen setup

        setTitle("Gleebus' Space Adventure");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);


        JPanel startCard = new Start(this);
        JPanel backstoryCard = new Backstory(this);
        JPanel level1 = new Level_1(this);
        JPanel level2 = new Level_2(this);
        JPanel level3 = new Level_3(this);
        JPanel level4 = new Level_4(this);
        JPanel endCard = new End();

        cards = new JPanel(cardLayout);
        cards.add(startCard, "Start");
        cards.add(backstoryCard, "Backstory");
        cards.add(level1, "Level1");
        cards.add(level2, "Level2");
        cards.add(level3, "Level3");
        cards.add(level4, "Level4");
        cards.add(endCard, "End");

        // FOR TESTING ->
        // change "Level3" to WHICHEVER LEVEL YOU WANT TO TEST
        cardLayout.show(cards, "Start");

//        // Output area at bottom
//        output_area = new JTextArea(5, 50);
//        output_area.setEditable(false);
//        output_area.setBorder(BorderFactory.createTitledBorder("Event Output"));
//        JScrollPane scrollPane = new JScrollPane(output_area);

        add(cards);
    }

    public CardLayout getCardLayout(){
        return this.cardLayout;
    }

    public JPanel getCards(){
        return this.cards;
    }

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
