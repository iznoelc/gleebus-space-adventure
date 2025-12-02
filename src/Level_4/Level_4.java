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

public class Level_4 extends JPanel {
    private Game parent;
    private Gleebus gleebus = new Gleebus(3);

    public Level_4(Game parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        JLayeredPane introPane = levelIntro(this.parent);

    }

    public JLayeredPane levelIntro(Game parent){
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setBounds(450,125,1280,720);
        layeredPane.add(drawPanel, JLayeredPane.DEFAULT_LAYER);

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

        JButton moveOnButton = new JButton("Let's Battle!");
        moveOnButton.setPreferredSize(new Dimension(300,50));
        moveOnButton.setFont(new Font("Dialog", Font.BOLD, 18));

        moveOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPane.setVisible(false);
                BattleHandler battleHandler = new BattleHandler(parent, gleebus);
                add(battleHandler, BorderLayout.CENTER);
                repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 500, 1280, 100);
        buttonPanel.add(moveOnButton);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        return layeredPane;
    }

    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(gleebus.getImage(), 0,0, this);
        }
    }
}
