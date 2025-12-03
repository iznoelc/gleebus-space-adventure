package Level_4;

import Game.Game;
import Game.NextCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;

public class BattleHandler extends JPanel {
    private static final int MAX_ENEMIES = 5;
    private static final double SUCCESS_RATE = 0.07;
    private static final double FAIL_RATE = 0.96;

    private Game parent;
    private Gleebus gleebus;
    private SpaceEnemy currentEnemy;
    private SpaceEnemyFactory sef = new SpaceEnemyFactory();

    private JButton attack, rest, guard, ability;
    private JPanel gleebusHealthPanel, alienHealthPanel, actionButtonPanel;
    private JLabel displayMessage;

    private Random r = new Random();
    private ArrayList<JButton> actionButtons;
    private int prevEnemy, totalEnemiesKilled;
    private boolean endBattle = false;

    public BattleHandler(Game parent, Gleebus gleebus){
        this.parent = parent;
        this.gleebus = gleebus;
        actionButtons = new ArrayList<>();
        setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        prevEnemy = r.nextInt(3) + 1;
        currentEnemy = sef.spawnEnemy(prevEnemy);

        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setBounds(0,0,1280,720);
        layeredPane.add(drawPanel, JLayeredPane.DEFAULT_LAYER);

        gleebusHealthPanel = new JPanel(new FlowLayout());
        gleebusHealthPanel.setOpaque(false);
        gleebusHealthPanel.setBounds(0,0,1280,100);
        alienHealthPanel = new JPanel(new FlowLayout());
        alienHealthPanel.setOpaque(false);
        alienHealthPanel.setBounds(0,620,1280,720);

        drawHealth(gleebusHealthPanel, alienHealthPanel);

        layeredPane.add(gleebusHealthPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(alienHealthPanel, JLayeredPane.PALETTE_LAYER);

        actionButtonPanel = setUpActionButtons();
        actionButtonPanel.setBounds(490,400, 300,100);
        //actionButtonPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        layeredPane.add(actionButtonPanel, JLayeredPane.PALETTE_LAYER);

        JPanel messagePanel = new JPanel();
        messagePanel.setOpaque(false);
        displayMessage = new JLabel(currentEnemy.getSpawnText());
        displayMessage.setFont(new Font("Dialog", Font.ITALIC, 24));
        messagePanel.setBounds(0,100,1280,720);
        messagePanel.add(displayMessage);
        layeredPane.add(messagePanel, JLayeredPane.PALETTE_LAYER);

        repaint();
    }

    public void resetBattle(){
        totalEnemiesKilled = 0;
        gleebus.resetHealth();
        spawnEnemy();
        enableAllActionButtons();
        drawHealth(gleebusHealthPanel,alienHealthPanel);
    }

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

        attack.addActionListener(e -> attackerHelper());
        rest.addActionListener(e -> restHelper());
        guard.addActionListener(e -> guardHelper());
        ability.addActionListener(e -> abilityHelper(SUCCESS_RATE, FAIL_RATE));

        for (JButton button : actionButtons){ actionButtonPanel.add(button); }
        actionButtonPanel.setOpaque(false);
        return actionButtonPanel;
    }

    private void drawHealth(JPanel gleebusHealthPanel, JPanel alienHealthPanel){
        gleebusHealthPanel.removeAll();
        alienHealthPanel.removeAll();

        ImageIcon gleebusHealth = new ImageIcon("src/Images/Puzzle4/P4_GleebusHealth.png");
        ImageIcon scaledGleebusHealth = new ImageIcon(gleebusHealth.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        for (int i = 0; i < gleebus.getCurrentHealth(); i++){
            JLabel healthPointLabel = new JLabel(scaledGleebusHealth);
            healthPointLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            gleebusHealthPanel.add(healthPointLabel);
        }

        ImageIcon alienHealth = new ImageIcon("src/Images/Puzzle4/P4_AlienHealth.png");
        ImageIcon scaledAlienHealth = new ImageIcon(alienHealth.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));

        if (!endBattle){
            for (int i = 0; i < currentEnemy.getHealth(); i++){
                JLabel healthPointLabel = new JLabel(scaledAlienHealth);
                healthPointLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                alienHealthPanel.add(healthPointLabel);
            }
        } else {
            alienHealthPanel.removeAll();
        }

        gleebusHealthPanel.revalidate();
        gleebusHealthPanel.repaint();
        alienHealthPanel.revalidate();
        alienHealthPanel.repaint();
    }

    private void enemyTurn(boolean takeTurn){
        ActionListener enemyTurn = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (takeTurn) {
                    double chance = r.nextDouble();
                    // gleebus has about a 5% chance to dodge the attack
                    if (chance < 0.05) {
                        displayMessage.setText("Gleebus was able to dodge the enemies attack!");
                    } else {
                        gleebus.takeDamage(currentEnemy.getAttackDamage());
                        displayMessage.setText("Gleebus took " + currentEnemy.getAttackDamage() + " damage from the enemy!");
                        if (gleebus.getCurrentHealth() <= 0){
                            // game over
                            resetBattle();
                            parent.getCardLayout().show(parent.getCards(), "GameOver");
                            return;
                        }
                    }


                    drawHealth(gleebusHealthPanel, alienHealthPanel);
                    revalidate();
                    repaint();
                }
                for (JButton button : actionButtons){
                    button.setEnabled(true);
                }
            }
        };

        for (JButton button : actionButtons){
            button.setEnabled(false);
        }

        int delay = 2000;
        Timer timer = new Timer(delay, enemyTurn);
        timer.setRepeats(false);

        timer.start();
    }

    private void guardHelper(){
        double chance = r.nextDouble();
        if (chance < 0.95){
            displayMessage.setText("Gleebus successfully guarded against the enemy's attack! He takes no damage.");
            enemyTurn(false); // simulate enemy taking turn (for game flow with the timer) but don't actually do any damage
        } else {
            displayMessage.setText("Gleebus' guard efforts failed!");
            enemyTurn(true);
        }
        revalidate();
        repaint();
    }

    private void restHelper(){
        int amnt = r.nextInt(5) + 1;
        gleebus.heal(amnt);
        displayMessage.setText("Gleebus rested and healed for " + amnt + " health points!");
        drawHealth(gleebusHealthPanel,alienHealthPanel);
        enemyTurn(true);

        revalidate();
        repaint();
    }

    private void abilityHelper(double successRate, double failRate){
        double chance = r.nextDouble();
        ActionListener abilityListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chance <= FAIL_RATE && chance >= SUCCESS_RATE){
                    // gleebus fails to use his ability, but it doesn't backfire, so he attacks normally
                    attackerHelper();
                } else if (chance < SUCCESS_RATE) {
                    // gleebus uses his ability, so the enemy is instantly killed a new one must be spawned
                    spawnEnemy();
                    revalidate();
                    repaint();
                } else {
                    // gleebus ability backfires, so enemy should take a turn after gleebus takes damage
                    enemyTurn(true);
                }
            }
        };

        if (chance < SUCCESS_RATE) {
            currentEnemy.takeDamage(currentEnemy.getHealth());
            displayMessage.setText("[RARE] Gleebus used his special ability and instantly killed the enemy!");
        } else if (chance > FAIL_RATE) {
            displayMessage.setText("[RARE] Gleebus' ability BACKFIRES and he took damage instead!");
            gleebus.takeDamage(3);
            drawHealth(gleebusHealthPanel, alienHealthPanel);
            revalidate();
            repaint();
        } else {
            displayMessage.setText("Gleebus failed to use his ability and attacks normally.");
        }
        disableAllActionButtons();

        int delay = 1500; // 1.5-second delay, slightly faster than enemy turn
        Timer t = new Timer(delay, abilityListener);
        t.setRepeats(false);
        t.start();

        revalidate();
        repaint();
    }

    private void attackerHelper(){
        int atk = gleebus.attack();
        displayMessage.setText("Gleebus attacked the current enemy for " + atk + " damage.");
        this.currentEnemy.takeDamage(atk);

        // when an enemy is killed, spawn a new one or end level if total number of enemies needed are killed
        if (this.currentEnemy.getHealth() <= 0){
            totalEnemiesKilled++;
            if (totalEnemiesKilled == MAX_ENEMIES){
                endLevel();
                return;
            }
            spawnEnemy();
        } else {
            enemyTurn(true);
        }
        drawHealth(gleebusHealthPanel, alienHealthPanel);
        revalidate();
        repaint();
    }

    private void spawnEnemy() {
        // don't spawn the same kind enemy twice in a row
        int spawnNum;
        do {
            spawnNum = r.nextInt(3) + 1;
        } while (spawnNum == prevEnemy);

        prevEnemy = spawnNum;
        this.currentEnemy = sef.spawnEnemy(spawnNum);
        displayMessage.setText(this.currentEnemy.getSpawnText());

        // ensure buttons are enabled
        for (JButton button : actionButtons){
            button.setEnabled(true);
        }
    }

    private void endLevel(){
        endBattle = true;
        for (JButton button : actionButtons){
            button.setVisible(false);
        }

        displayMessage.setText("Gleebus was able to defeat all the enemies!");
        NextCard next = new NextCard();
        JButton nextLevelButton = next.getNextCardButton(this.parent, "Go Home!", 300, 50, "End", 18);
        actionButtonPanel.setLayout(new FlowLayout());
        actionButtonPanel.add(nextLevelButton);
        drawHealth(gleebusHealthPanel,alienHealthPanel);

        revalidate();
        repaint();
    }

    private void enableAllActionButtons(){ for (JButton button : actionButtons){ button.setEnabled(true); } }

    private void disableAllActionButtons(){ for (JButton button : actionButtons){ button.setEnabled(false); } }

    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(gleebus.getImage(), 25,200, this);
            if (!endBattle){
                g.drawImage(currentEnemy.getImage(), 850, 200, this);
            }
        }
    }
}
