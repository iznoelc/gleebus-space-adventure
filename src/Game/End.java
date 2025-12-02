package Game;

import javax.swing.*;
import Game.Game;
import Level_4.BattleHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class End extends JPanel {
    Game parent;

    public End(Game parent){
        this.parent = parent;
        setLayout(new BorderLayout());

        // sets up the layered pane to store logo and button on
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280,720));
        add(layeredPane, BorderLayout.CENTER);

        // drawing panel to draw the logo on, set as default layer of layered pane
        DrawPanel drawingPanel = new DrawPanel();
        drawingPanel.setBounds(0,0,1280,720);
        layeredPane.add(drawingPanel, JLayeredPane.DEFAULT_LAYER);

        //
        JPanel endComponentPanel = new JPanel();
        endComponentPanel.setLayout(new BoxLayout(endComponentPanel, BoxLayout.Y_AXIS));
        JLabel endText = new JLabel("★ GLEEBUS HAS RETURNED HOME! ★");
        endText.setFont(new Font("Dialog", Font.BOLD, 40));
        endText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        endComponentPanel.add(endText);
        endComponentPanel.setBounds(0,0,1280,100);
        endComponentPanel.setOpaque(false);

        JButton endGameButton = new JButton("EXIT");
        endGameButton.setPreferredSize(new Dimension(150, 50));
        endGameButton.setFont(new Font("Dialog", Font.PLAIN, 24));
        endGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        endComponentPanel.add(endGameButton);

        layeredPane.add(endComponentPanel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * Handles drawing the logo.
     */
    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image logo = new ImageIcon("src/Images/Ending.png").getImage();
            g.drawImage(logo,0, -10, this);
        }
    }
}
