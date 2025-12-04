/**
 * Helper class to handle transition between cards.
 * @author Izzy Carlson
 */
package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to provide helpers for moving throughout cards in the game's card layout
 * @author Izzy Carlson
 */
public class NextCard {
    /**
     * Creates a button that, when clicked, moves to the next specified card.
     * @param parent Game JFrame
     * @param buttonText What the button should say (i.e. START, NEXT LEVEL)
     * @param width Button's width
     * @param height Button's height
     * @param nextCard The name of the card that the button should move to when clicked
     * @param buttonFontSize The font size of the button
     * @return a JPanel with the next card button
     */
    public JButton getNextCardButton(Game parent, String buttonText, int width, int height, String nextCard, int buttonFontSize){
        JButton nextCardButton = new JButton(buttonText);
        nextCardButton.setPreferredSize(new Dimension(width,height));
        nextCardButton.setFont(new Font("Dialog", Font.BOLD, buttonFontSize));

        nextCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getCardLayout().show(parent.getCards(), nextCard);
            }
        });

        return nextCardButton;
    }
}
