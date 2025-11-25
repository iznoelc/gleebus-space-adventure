package Game;

import Level_3.Level_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Iterator;

import javax.swing.*;
import java.awt.*;

public class Backstory extends JPanel {
    JFrame parent;
    private LinkedList<Image> backstoryImages;
    private LinkedList<String> backstoryText;
    private Image imageToDraw;

    public Backstory(Game parent){
        this.parent = parent;
        this.backstoryImages = setUpBackstoryImages();
        this.backstoryText = setUpBackstoryText();

        Iterator<Image> imageIterator = backstoryImages.iterator();
        Iterator<String> textIterator = backstoryText.iterator();
        this.imageToDraw = imageIterator.next();

        setLayout(new BorderLayout());

        // use a layered pane since we need to layer buttons, checkboxes, etc. over drawings
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
        JButton skipStoryButton = new NextCard().getNextCardButton(parent, "Skip Story", 200, 50, "Level1", 18);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // repaint to first update the image
                // if there's another backstory image to draw (iterator not null),
                // set it to imageToDraw.
                if (imageIterator.hasNext()) {
                    repaint();
                    imageToDraw = imageIterator.next();
                    storyLabel.setText(textIterator.next());

                    // then check the iterator again
                    // if the next is empty (current is the end)
                    // we want to update to remove the next button and force user to move
                    // to the next card
                    if (!imageIterator.hasNext()) {
                        imageToDraw = backstoryImages.getLast();
                        buttonsPanel.remove(nextButton);

                        skipStoryButton.setText("LET'S DO IT");
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

    private LinkedList<Image> setUpBackstoryImages(){
        LinkedList<Image> backstoryImages = new LinkedList<>();

        backstoryImages.add(new ImageIcon("src/Images/Backstory1.png").getImage());
        backstoryImages.add(new ImageIcon("src/Images/Backstory2.png").getImage());
        backstoryImages.add(new ImageIcon("src/Images/Backstory3.png").getImage());
        backstoryImages.add(new ImageIcon("src/Images/Backstory4.png").getImage());
        backstoryImages.add(new ImageIcon("src/Images/Backstory5.png").getImage());

        return backstoryImages;
    }

    private LinkedList<String> setUpBackstoryText(){
        LinkedList<String> backstoryText = new LinkedList<>();

        String introText = "Gleebus was on his way to Earth...";
        backstoryText.add(introText);

        String story1 = "When a giant asteroid crashed into his ship!";
        backstoryText.add(story1);

        String story2 = "He crash landed onto Earth. He made a few friends during his stay. ^_^";
        backstoryText.add(story2);

        String story3 = "But now Gleebus wants to go back to his home planet: Planet Gleebus!";
        backstoryText.add(story3);

        String endingText = "Help Gleebus find and repair his spaceship so he can go home!";
        backstoryText.add(endingText);

        return backstoryText;
    }

    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(imageToDraw, 0,0, this);
        }
    }
}

