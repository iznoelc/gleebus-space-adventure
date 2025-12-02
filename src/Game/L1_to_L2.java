package Game;

import Level_3.Level_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Iterator;

import javax.swing.*;
import java.awt.*;

public class L1_to_L2 extends JPanel {
    JFrame parent;
    private LinkedList<String> transitionText;
    private Image imageToDraw;

    public L1_to_L2(Game parent){
        this.parent = parent;
        this.transitionText = setUpText();

        //set image
        ImageIcon GleebusCrying = new ImageIcon("src/Images/Backstory4.png");
        imageToDraw = GleebusCrying.getImage();
        //set text iterator
        Iterator<String> textIterator = transitionText.iterator();

        setLayout(new BorderLayout());

        // set up the layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        // set up the drawing panel to draw the images and add it to the layered pane
        DrawPanel drawingPanel = new DrawPanel();
        drawingPanel.setBounds(0,0,1280,720);
        layeredPane.add(drawingPanel, JLayeredPane.DEFAULT_LAYER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        JPanel storyPanel = new JPanel();
        storyPanel.setLayout(new FlowLayout());
        storyPanel.setBounds(0,500,1280,720);
        storyPanel.setOpaque(false);
        JLabel storyLabel = new JLabel(textIterator.next());
        storyLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        storyLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        storyPanel.add(storyLabel);
        layeredPane.add(storyPanel, JLayeredPane.PALETTE_LAYER);

        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(100,50));
        nextButton.setFont(new Font("Dialog", Font.BOLD, 18));
        JButton skipStoryButton = new NextCard().getNextCardButton(parent, "Skip", 200, 50, "Level2", 18);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // repaint to first update the image
                // if there's another image to draw (iterator not null),
                // set it to imageToDraw.
                imageToDraw = GleebusCrying.getImage();
                if (textIterator.hasNext()) {
                    repaint();
                    storyLabel.setText(textIterator.next());


                    // then check the iterator again
                    // if the next is empty (current is the end)
                    // we want to update to remove the next button and force user to move
                    // to the next card
                    if (!textIterator.hasNext()) {
                        buttonsPanel.remove(nextButton);

                        skipStoryButton.setText("HELP GLEEBUS!");
                        repaint();
                    }
                }
            }
        });

        buttonsPanel.setOpaque(false);
        buttonsPanel.setBounds(0, 550, 1280, 100); // position at top
        layeredPane.add(buttonsPanel, JLayeredPane.PALETTE_LAYER);

        buttonsPanel.add(skipStoryButton);
        buttonsPanel.add(nextButton);
    }

    private LinkedList<String> setUpText(){
        LinkedList<String> transitionText = new LinkedList<>();

        String text1 = "Uh oh...Gleebus is locked out and forgot his password! He can reset it with security questions but... he forgot that too.";
        transitionText.add(text1);

        String text2 = "Its a good thing he pre-programmed hints for himself! Help Gleebus get into his ship by entering the answers.";
        transitionText.add(text2);

        return transitionText;
    }

    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(imageToDraw, 0,0, this);
        }
    }
}


