package Level_4;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleHandler extends JPanel {
    private Game parent;
    private JButton attack, rest;
    private JPanel gleebusHealthPanel, alienHealthPanel;
    private JLabel displayMessage;
    private Gleebus gleebus;
    private SpaceEnemy currentEnemy;
    private SpaceEnemyFactory sef = new SpaceEnemyFactory();
    private Random r = new Random();
    int prevEnemy;

    public BattleHandler(Game parent, Gleebus gleebus){
        this.parent = parent;
        this.gleebus = gleebus;
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

        JPanel actionButtonPanel = setUpActionButtons();
        actionButtonPanel.setBounds(0,400, 1280,720);
        layeredPane.add(actionButtonPanel, JLayeredPane.PALETTE_LAYER);

        JPanel messagePanel = new JPanel();
        messagePanel.setOpaque(false);
        displayMessage = new JLabel(currentEnemy.getSpawnText());
        displayMessage.setFont(new Font("Dialog", Font.ITALIC, 14));
        messagePanel.setBounds(0,300,1280,720);
        messagePanel.add(displayMessage);
        layeredPane.add(messagePanel, JLayeredPane.PALETTE_LAYER);

        battle();

        repaint();
    }

    private JPanel setUpActionButtons(){
        JPanel actionButtonPanel = new JPanel(new FlowLayout());
        Font actionButtonFont = new Font("Dialog", Font.PLAIN, 18);
        Dimension actionButtonDimension = new Dimension(150,50);

        attack = new JButton("ATTACK");
        attack.setFont(actionButtonFont);
        attack.setPreferredSize(actionButtonDimension);

        rest = new JButton("REST");
        rest.setFont(actionButtonFont);
        rest.setPreferredSize(actionButtonDimension);

        actionButtonPanel.add(attack);
        actionButtonPanel.add(rest);

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

        for (int i = 0; i < currentEnemy.getHealth(); i++){
            JLabel healthPointLabel = new JLabel(scaledAlienHealth);
            healthPointLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            alienHealthPanel.add(healthPointLabel);
        }

        gleebusHealthPanel.revalidate();
        gleebusHealthPanel.repaint();
        alienHealthPanel.revalidate();
        alienHealthPanel.repaint();
    }

    private void battle(){
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attackerHelper();
            }
        });
    }

    private void attackerHelper(){
        System.out.println("Attacking enemy");
        int atk = gleebus.attack();
        displayMessage.setText("Gleebus attacked the current enemy for " + atk + " damage.");
        this.currentEnemy.takeDamage(atk);
        System.out.println("New enemy health " + currentEnemy.getHealth());
        if (this.currentEnemy.getHealth() <= 0){
            System.out.println("Enemy killed! Spawning new one");
            // dont spawn the same enemy twice in a row
            int spawnNum = r.nextInt(3) + 1;
            System.out.println("Spawning " + spawnNum);
            while (spawnNum == prevEnemy){
                spawnNum = r.nextInt(3) + 1;
            }
            prevEnemy = spawnNum;
            this.currentEnemy = sef.spawnEnemy(spawnNum);
            displayMessage.setText(this.currentEnemy.getSpawnText());
        }
        drawHealth(gleebusHealthPanel,alienHealthPanel);
        revalidate();
        repaint();
    }

    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(gleebus.getImage(), 25,200, this);
            g.drawImage(currentEnemy.getImage(), 850, 200, this);
        }
    }

}
