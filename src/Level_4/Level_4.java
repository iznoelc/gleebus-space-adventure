package Level_4;

import Game.Game;
import Level_3.Level_3;
import Level_3.Spaceship;
import Game.NextCard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Handles JavaSwing set-up for the introductory screen to level 4.
 * @author Izzy Carlson
 */
public class Level_4 extends JPanel {
    private Game parent;
    private Gleebus gleebus;

    /**
     * Constructor to initialize frame that level 4 panel will be drawn to and initialize Gleebus.
     * @param parent The parent JFrame that level 4 should be added to
     */
    public Level_4(Game parent) {
        this.parent = parent;
        gleebus = new Gleebus(10, 3); // set-up gleebus
        setLayout(new BorderLayout());

        // set up the layered pane where everything will sit
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        // set up the drawing and put it as the default layer
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setBounds(450,125,1280,720);
        layeredPane.add(drawPanel, JLayeredPane.DEFAULT_LAYER);

        // set up the instructions for the level
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("SPACE BATTLE");
        title.setFont(new Font("Dialog", Font.BOLD, 40));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel details = new JLabel("Gleebus' spaceship is finally fixed. He can almost go home! Help him fight off" +
                "enemy aliens in this final stage!");
        details.setFont(new Font("Dialog", Font.PLAIN, 24));
        details.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        instructionsPanel.add(title);
        instructionsPanel.add(details);
        instructionsPanel.setBounds(0,50,1280,720);
        instructionsPanel.setOpaque(false);

        layeredPane.add(instructionsPanel, JLayeredPane.PALETTE_LAYER);

        // set up the move on button for the player to begin the battle
        JButton moveOnButton = new JButton("Let's Battle!");
        moveOnButton.setPreferredSize(new Dimension(200,50));
        moveOnButton.setFont(new Font("Dialog", Font.BOLD, 18));

        // add listener to move onto battle screen when clicked
        moveOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPane.setVisible(false);
                BattleHandler battleHandler = new BattleHandler(parent, gleebus);
                add(battleHandler, BorderLayout.CENTER);
                repaint();
            }
        });

        // set up tool tip button that tells user what gleebus' abilities do
        JButton seeTooltipButton = new JButton("See Tooltip");
        seeTooltipButton.setPreferredSize(new Dimension(200,50));
        seeTooltipButton.setFont(new Font("Dialog", Font.BOLD, 18));

        // set up tool tip action listener to display what gleebus' actions can do
        boolean tooltipEnabled = false;
        seeTooltipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<JLabel> toolTips = new ArrayList<>();
                JLabel attack = new JLabel("Attack -> Attacks the enemy for 1 to " + gleebus.getAttackDamageUpperBound() + " damage.");
                JLabel rest = new JLabel(" Rest -> Heals Gleebus for 1 to " + (gleebus.getMaxHealth() / 2) + " health points.");
                JLabel guard = new JLabel("Guard -> Guards Gleebus from the enemies next attack!");
                JLabel ability = new JLabel("Ability -> Gleebus has the chance to use an instakill ability! But be careful... He hasn't mastered it yet.");
                toolTips.add(attack);
                toolTips.add(rest);
                toolTips.add(guard);
                toolTips.add(ability);

                JPanel tooltipPanel = new JPanel();
                tooltipPanel.setLayout(new BoxLayout(tooltipPanel, BoxLayout.Y_AXIS));

                for (JLabel tip : toolTips){
                    tip.setAlignmentX(Component.CENTER_ALIGNMENT);
                    tip.setFont(new Font("Dialog", Font.BOLD, 14));
                    tooltipPanel.add(tip);
                }

                tooltipPanel.setBounds(0,575, 1280,720);

                layeredPane.add(tooltipPanel, JLayeredPane.PALETTE_LAYER);
                seeTooltipButton.setEnabled(false);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 500, 1280, 100);
        buttonPanel.add(seeTooltipButton);
        buttonPanel.add(moveOnButton);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * Private helper class to draw Gleebus on the screen.
     * @author Izzy Carlson
     */
    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(gleebus.getImage(), 0,0, this);
        }
    }
}
