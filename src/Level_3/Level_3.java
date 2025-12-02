package Level_3;

import Game.Game;
import Game.NextCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle JavaSwing set-up for level 3.
 */
public class Level_3 extends JPanel {
    private Spaceship gleebusShip = new BrokenSpaceship();
    private Game parent;

    private boolean leverComponentBuilt = false;
    private boolean buttonPanelComponentBuilt = false;
    private boolean screenComponentBuilt = false;

    JCheckBox hammer, wrench, pliers, screwdriver;

    public Level_3(Game parent){
        this.parent = parent;
        setLayout(new BorderLayout());

        // use a layered pane since we need to layer buttons, checkboxes, etc. over drawings
        // set up the layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        // set up the drawing panel to draw the images and add it to the layered pane
        DrawPanel drawingPanel = new DrawPanel();
        drawingPanel.setBounds(0,-25,1280,720);
        layeredPane.add(drawingPanel, JLayeredPane.DEFAULT_LAYER);

        JPanel checkboxPanel = getCheckBoxPanel(parent);
        checkboxPanel.setOpaque(false);
        checkboxPanel.setBounds(0, 250, 850, 300);
        layeredPane.add(checkboxPanel, JLayeredPane.PALETTE_LAYER);

        // set up a button panel for the "repair" button
        JPanel buttonPanel = getButtonJPanel(parent, checkboxPanel);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 290, 850, 100); // position at top
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        // set up the base component for the decorator (gleebu' broken spaceship)
        gleebusShip.addComponent();
        System.out.println("Gleebus ship : " + gleebusShip.getComponents().getFirst());

        JPanel textPanel = getTextPanel();
        textPanel.setOpaque(false);
        textPanel.setBounds(0, 50, 850,300);
        layeredPane.add(textPanel, JLayeredPane.PALETTE_LAYER);
    }

    private JPanel getButtonJPanel(JFrame frame, JPanel checkboxPanel) {
        JButton repairButton = new JButton("REPAIR");
        repairButton.setPreferredSize(new Dimension(300,50));
        repairButton.setFont(new Font("Dialog", Font.BOLD, 18));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        repairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((hammer.isSelected() &&  wrench.isSelected() && !screwdriver.isSelected() && !pliers.isSelected())
                        && !screenComponentBuilt){
                    Spaceship screenComponent = new ScreenDecorator(gleebusShip);
                    screenComponent.addComponent();
                    screenComponent.setVisible(true);
                    screenComponentBuilt = true;
                } else if ((pliers.isSelected() && wrench.isSelected() && screwdriver.isSelected() && !hammer.isSelected())
                        && !buttonPanelComponentBuilt){
                    Spaceship buttonPanelComponent = new ButtonPanelDecorator(gleebusShip);
                    buttonPanelComponent.addComponent();
                    buttonPanelComponent.setVisible(true);
                    buttonPanelComponentBuilt = true;
                } else if ((pliers.isSelected() && screwdriver.isSelected() && !wrench.isSelected() && !hammer.isSelected())
                        && !leverComponentBuilt){
                    Spaceship leverComponent = new LeverDecorator(gleebusShip);
                    leverComponent.addComponent();
                    leverComponent.setVisible(true);
                    leverComponentBuilt = true;
                }

                repaint();

                if (screenComponentBuilt && buttonPanelComponentBuilt && leverComponentBuilt){
                    repairButton.setVisible(false);
                    NextCard nextCard = new NextCard();
                    JButton nextLevelButton = nextCard.getNextCardButton(parent, "Fly the Spaceship!", 300, 50, "Level4", 18);
                    buttonPanel.add(nextLevelButton);
                    hammer.setEnabled(false);
                    wrench.setEnabled(false);
                    screwdriver.setEnabled(false);
                    pliers.setEnabled(false);
                }
            }
        });

        buttonPanel.add(repairButton);
        return buttonPanel;
    }

    private JPanel getCheckBoxPanel(JFrame frame){
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new FlowLayout());

        Font checkboxFont = new Font("Dialog", Font.PLAIN, 24);

        hammer = new JCheckBox("Hammer");
        hammer.setFont(checkboxFont);
        wrench = new JCheckBox("Wrench");
        wrench.setFont(checkboxFont);
        screwdriver = new JCheckBox("Screwdriver");
        screwdriver.setFont(checkboxFont);
        pliers = new JCheckBox("Pliers");
        pliers.setFont(checkboxFont);

        checkboxPanel.add(screwdriver);
        checkboxPanel.add(hammer);
        checkboxPanel.add(pliers);
        checkboxPanel.add(wrench);

        return checkboxPanel;
    }

    private JPanel getTextPanel(){
        JLabel title = new JLabel("REPAIR THE SPACESHIP CONTROL PANEL");
        title.setFont(new Font("Dialog", Font.PLAIN, 40));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel descriptionLabel = new JLabel("Some of the control panel components broke in the crash!");
        descriptionLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        descriptionLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel descriptionLabel2 = new JLabel("Help Gleebus each one by selecting the appropriate tools");
        descriptionLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
        descriptionLabel2.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel descriptionLabel3 = new JLabel("and then hitting the repair button.");
        descriptionLabel3.setFont(new Font("Dialog", Font.PLAIN, 18));
        descriptionLabel3.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // box layout, stack components vertically

        textPanel.add(title);
        textPanel.add(descriptionLabel);
        textPanel.add(descriptionLabel2);
        textPanel.add(descriptionLabel3);


        return textPanel;
    }

    private class DrawPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Image manual = new ImageIcon("src/Images/Puzzle3/P3_Manual.png").getImage();
            g.drawImage(manual, 875,10, this);

            // handle drawing spaceship
            if (gleebusShip.getComponents() != null){
                for(Spaceship spaceship : gleebusShip.getComponents()) {

                    if (spaceship.getVisibility()){
                        Image img = spaceship.getImage();
                        //System.out.println("Image is null? " + (img == null));

                        g.drawImage(spaceship.getImage(), 0,0, this);
                    }
                }
            } else {
                System.out.println("Ship component array list is null");
            }

        }
    }
}
