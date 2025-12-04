package Level_4;

import Game.Game;
import Game.NextCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Manages the UI and transitions to next card for the battle stage of level 4.
 * @author Izzy Carlson
 */
public class BattleHandler extends JPanel {
    // ------------------------------------------------------------
    //                  Variables
    // ------------------------------------------------------------
    private static final double SUCCESS_RATE = 0.07; // the success rate of gleebus' ability
    private static final double FAIL_RATE = 0.96; // the fail rate of gleebus' ability

    // manage game variables
    private Game parent;
    private Gleebus gleebus;
    private GleebusController gleebusController;
    private EnemyController enemyController;
    private boolean endBattle = false;
    private int totalEnemiesKilled;

    // component variables
    private JButton attack, rest, guard, ability;
    private JPanel gleebusHealthPanel, alienHealthPanel, actionButtonPanel;
    private JLabel displayMessage;
    private ArrayList<JButton> actionButtons;
    private ImageIcon scaledGleebusHealth;
    private ImageIcon scaledAlienHealth;

    // ------------------------------------------------------------
    //                  Constructor
    // ------------------------------------------------------------
    /**
     * Constructor to set up the base layout for level 4's battle display.
     * @param parent Parent JFrame that BattleHandler should be added to.
     * @param gleebus Gleebus from initial Level 4 set up to manage health and attack.
     */
    public BattleHandler(Game parent, Gleebus gleebus){
        this.parent = parent;
        this.gleebus = gleebus;
        this.enemyController = new EnemyController(this, this.gleebus); // manage enemy
        gleebusController = new GleebusController(this, this.gleebus, this.enemyController); // manage gleebus
        actionButtons = new ArrayList<>();
        setLayout(new BorderLayout());

        // set up image icons (drawing handled by separate method, see drawHealth())
        ImageIcon gleebusHealth = new ImageIcon("src/Images/Puzzle4/P4_GleebusHealth.png");
        scaledGleebusHealth = new ImageIcon(gleebusHealth.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        ImageIcon alienHealth = new ImageIcon("src/Images/Puzzle4/P4_AlienHealth.png");
        scaledAlienHealth = new ImageIcon(alienHealth.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));

        // store everything on a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        // set up the draw panel to draw gleebus and the current enemy
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setBounds(0,0,1280,720);
        layeredPane.add(drawPanel, JLayeredPane.DEFAULT_LAYER);

        // set up the gleebus and alien health panels, then pass them into the DrawHealth helper method to put them on screen
        gleebusHealthPanel = new JPanel(new FlowLayout());
        gleebusHealthPanel.setOpaque(false);
        gleebusHealthPanel.setBounds(0,0,1280,100);
        alienHealthPanel = new JPanel(new FlowLayout());
        alienHealthPanel.setOpaque(false);
        alienHealthPanel.setBounds(0,620,1280,720);
        drawHealth();
        layeredPane.add(gleebusHealthPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(alienHealthPanel, JLayeredPane.PALETTE_LAYER);

        // set up the player's action buttons using the helper method
        actionButtonPanel = setUpActionButtons();
        actionButtonPanel.setBounds(490,400, 300,100);
        layeredPane.add(actionButtonPanel, JLayeredPane.PALETTE_LAYER);

        // set up the message panel to display relevant information for the battle
        JPanel messagePanel = new JPanel();
        messagePanel.setOpaque(false);
        displayMessage = new JLabel(enemyController.getCurrentEnemy().getSpawnText());
        displayMessage.setFont(new Font("Dialog", Font.ITALIC, 24));
        messagePanel.setBounds(0,100,1280,720);
        messagePanel.add(displayMessage);
        layeredPane.add(messagePanel, JLayeredPane.PALETTE_LAYER);

        repaint();
    }

    // ------------------------------------------------------------
    //                  UI Set-Up Helper Methods
    // ------------------------------------------------------------
    /**
     * Helper method to set up the action buttons (attack, heal, guard, ability).
     * @return The JPanel with the action buttons set-up.
     */
    private JPanel setUpActionButtons(){
        JPanel actionButtonPanel = new JPanel(new GridLayout(2,2));
        Font actionButtonFont = new Font("Dialog", Font.PLAIN, 18);
        Dimension actionButtonDimension = new Dimension(150,50);

        attack = new JButton("ATTACK");
        attack.setFont(actionButtonFont);
        attack.setPreferredSize(actionButtonDimension);
        actionButtons.add(attack);

        rest = new JButton("REST");
        rest.setFont(actionButtonFont);
        rest.setPreferredSize(actionButtonDimension);
        actionButtons.add(rest);

        guard = new JButton("GUARD");
        guard.setFont(actionButtonFont);
        guard.setPreferredSize(actionButtonDimension);
        actionButtons.add(guard);

        ability = new JButton("ABILITY");
        ability.setFont(actionButtonFont);
        ability.setPreferredSize(actionButtonDimension);
        actionButtons.add(ability);

        attack.addActionListener(e -> gleebusController.attackerHelper());
        rest.addActionListener(e -> gleebusController.restHelper());
        guard.addActionListener(e -> gleebusController.guardHelper());
        ability.addActionListener(e -> gleebusController.abilityHelper(SUCCESS_RATE, FAIL_RATE));

        for (JButton button : actionButtons){ actionButtonPanel.add(button); }
        actionButtonPanel.setOpaque(false);
        return actionButtonPanel;
    }

    /**
     * Draws Gleebus' and current enemy's health according to how many health points they currently have.
     */
    public void drawHealth(){
        gleebusHealthPanel.removeAll();
        alienHealthPanel.removeAll();

        // draw gleebus' health
        for (int i = 0; i < gleebus.getCurrentHealth(); i++){
            JLabel healthPointLabel = new JLabel(scaledGleebusHealth);
            healthPointLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            gleebusHealthPanel.add(healthPointLabel);
        }

        // draw alien health ONLY if the battle is ongoing. if gleebus defeats all enemies, ensure health is NOT drawn
        if (!endBattle){
            for (int i = 0; i < enemyController.getCurrentEnemy().getHealth(); i++){
                JLabel healthPointLabel = new JLabel(scaledAlienHealth);
                healthPointLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                alienHealthPanel.add(healthPointLabel);
            }
        } else {
            alienHealthPanel.removeAll();
        }

        // make sure everything properly updates on the screen
        gleebusHealthPanel.revalidate();
        gleebusHealthPanel.repaint();
        alienHealthPanel.revalidate();
        alienHealthPanel.repaint();
    }

    // ------------------------------------------------------------
    //               Level Management Helper Methods
    // ------------------------------------------------------------
    /**
     * Ends the level by replacing action buttons with a next level button and removing all drawings related to the enemy.
     */
    public void endLevel(){
        endBattle = true;
        for (JButton button : actionButtons){ button.setVisible(false); } // hide all buttons

        displayMessage.setText("Gleebus was able to defeat all the enemies!");

        // set up next card and display it
        NextCard next = new NextCard();
        JButton nextLevelButton = next.getNextCardButton(this.parent, "Go Home!", 300, 50, "End", 18);
        actionButtonPanel.setLayout(new FlowLayout());
        actionButtonPanel.add(nextLevelButton);

        drawHealth(); // update health to hide enemy health
        revalidate();
        repaint();
    }

    /**
     * Helper method to reset the battle if the player dies and loses and then must come back.
     */
    private void resetBattle(){
        totalEnemiesKilled = 0;
        gleebus.resetHealth();
        enemyController.spawnEnemy();
        enableAllActionButtons();
        drawHealth();
    }

    public void gameOver(){
        resetBattle();
        parent.getCardLayout().show(parent.getCards(), "GameOver");
    }

    /**
     * Reallows user to interact with action buttons.
     */
    public void enableAllActionButtons(){ for (JButton button : actionButtons){ button.setEnabled(true); } }

    /**
     * Prevents player from using the action buttons.
     */
    public void disableAllActionButtons(){ for (JButton button : actionButtons){ button.setEnabled(false); } }

    /**
     * Helper method to update the UI outside of this class, simplifying the process.
     * (i.e. certain abilities used by Gleebus require the UI to
     * be updated before the method is over, such as his ability.)
     */
    public void updateUI(){
        revalidate();
        repaint();
    }

    // ------------------------------------------------------------
    //                  Getters
    // ------------------------------------------------------------

    /**
     * Get the display message in order to update it
     * @return The display message
     */
    public JLabel getDisplayMessage(){ return displayMessage; }

    /**
     * Get the current total number of enemies killed
     * @return number of enemies killed
     */
    public int getTotalEnemiesKilled(){ return this.totalEnemiesKilled; }

    /**
     * Update the total number of enemies killed
     * @param amount to increase total number of enemies killed by
     */
    public void updateTotalEnemiesKilled(int amount){ this.totalEnemiesKilled += amount; }

    // ------------------------------------------------------------
    //                  Drawing
    // ------------------------------------------------------------

    /**
     * Helper class to draw Gleebus and the Enemy on the screen.
     * @author Izzy Carlson
     */
    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(gleebus.getImage(), 25,200, this);
            if (!endBattle){
                g.drawImage(enemyController.getCurrentEnemy().getImage(), 850, 200, this);
            }
        }
    }
}
