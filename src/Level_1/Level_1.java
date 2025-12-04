package Level_1;

import Game.Game;
import javax.swing.*;
import java.awt.*;
import Game.NextCard;

public class Level_1 extends JPanel {
    // ------------------------------------------------------------
    //                  Variables
    // ------------------------------------------------------------
    private Game parent;
    private JTextArea statusArea;
    JButton N, S, W, E;
    public boolean ship_found = false;
    JLabel GleebusLabel;

    // ------------------------------------------------------------
    //                  Constructor
    // ------------------------------------------------------------
    /**
     *
     * @param parent
     */
    public Level_1(Game parent) {
        //setup the window
        this.parent = parent;
        setLayout(new BorderLayout());

        // ----------------------
        //      Center Panel
        // ----------------------

        //make Gleebus
        ImageIcon Gleebus = new ImageIcon("src/Images/Puzzle1/P1_Gleebus.png");
        //rescale
        Image scaledGleebus = Gleebus.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledGleebusIcon = new ImageIcon(scaledGleebus);
        //make label to hold him
        GleebusLabel = new JLabel(scaledGleebusIcon);
        //center him
        GleebusLabel.setHorizontalAlignment(JLabel.CENTER);
        GleebusLabel.setVerticalAlignment(JLabel.CENTER);

        //make Ship Status text area
        statusArea = new JTextArea(5, 50);
        statusArea.setEditable(false);
        statusArea.setBorder(BorderFactory.createTitledBorder("Ship Signal"));
        JScrollPane scrollPane = new JScrollPane(statusArea);
        add(scrollPane, BorderLayout.PAGE_END);

        //make a container for Gleebus and Ship Status Text to share in the middle
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        //add Gleebus and Text to centerPanel
        centerPanel.add(GleebusLabel, BorderLayout.CENTER);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        //add centerPanel to main panel
        add(centerPanel, BorderLayout.CENTER);

        // ----------------------
        //      Side Panel
        // ----------------------

        //make map
        Map map = new Map(5);

        //button setup
        this.N = addButton("P1_NorthArrow", "north", map, centerPanel);
        this.S = addButton("P1_SouthArrow", "south", map, centerPanel);
        this.E = addButton("P1_EastArrow", "east", map, centerPanel);
        this.W = addButton("P1_WestArrow", "west", map, centerPanel);
    }

    // ------------------------------------------------------------
    //                  Helper Methods
    // ------------------------------------------------------------

    /**
     *
     * @param panel
     * used to display transition screen at the end of the puzzle
     */
    public void shipFound(JPanel panel){
        //set buttons to be disabled
        N.setEnabled(false);
        S.setEnabled(false);
        W.setEnabled(false);
        E.setEnabled(false);

        //add Gleebus's Spaceship
        ImageIcon SpaceShip = new ImageIcon("src/Images/Puzzle1/P1_GleebusSpaceship.png");
        //rescale
        Image scaledSpaceShip = SpaceShip.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledSpaceShipIcon = new ImageIcon(scaledSpaceShip);
        //make label to hold it
        GleebusLabel.setIcon(scaledSpaceShipIcon);
        //center
        GleebusLabel.setHorizontalAlignment(JLabel.CENTER);
        GleebusLabel.setVerticalAlignment(JLabel.CENTER);

        //add next level button
        JButton NextLevelButton = new NextCard().getNextCardButton(parent, "Enter Spaceship!", 200, 50, "Level2", 18);
        panel.add(NextLevelButton, BorderLayout.NORTH);

        //paint this over what's currently there
        repaint();
    }

    /**
     *
     * @param image
     * @param position
     * @param map
     * @param panel
     * @return JButton
     * a helper button to make the directional buttons
     */
    public JButton addButton(String image,  String position, Map map, JPanel panel){

        // ----------------------
        //      Image Setup
        // ----------------------

        //make button with image
        ImageIcon ButtonImage = new ImageIcon("src/Images/Puzzle1/" + image + ".png");
        // rescale image
        Image scaledImage = ButtonImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        // cast image back into a ImageIcon for use after scaling
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JButton imageButton = new JButton(scaledIcon);

        // ----------------------
        //      Positioning
        // ----------------------

        //command is assumed based on position
        Command command = null;
        //define panel
        JPanel Side = new JPanel();
        //adds padding to the side
        Side.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //center button
        if(position.equals("east") || position.equals("west")){
            Side.setLayout(new BoxLayout(Side, BoxLayout.Y_AXIS));
            Side.add(Box.createVerticalGlue());
            Side.add(imageButton);
            Side.add(Box.createVerticalGlue());
            //place button on correct side (left,right) and define trigger
            if(position.equals("west")){
                add(Side, BorderLayout.WEST);
                command = new W();
            } else{
                add(Side, BorderLayout.EAST);
                command = new E();
            }
        }
        //center button
        else if(position == "north" || position == "south"){
            Side.setLayout(new BoxLayout(Side, BoxLayout.X_AXIS));
            Side.add(Box.createHorizontalGlue());
            Side.add(imageButton);
            Side.add(Box.createHorizontalGlue());
            //place button on correct side (up,down) and define trigger
            if(position.equals("north")){
                add(Side, BorderLayout.NORTH);
                command = new N();
            } else{
                add(Side, BorderLayout.SOUTH);
                command = new S();
            }
        }
        else{
            System.out.println("Invalid Input");
            return null;
        }

        // ----------------------
        //      Listener
        // ----------------------

        //setup for triggering commands
        Invoker invoker = new Invoker();
        //finalize and activate trigger based on action listener
        invoker.setCommand(command);
        invoker.setCommand(command);
        imageButton.addActionListener(e -> {
            String result = invoker.executeCommand(map);
            if (statusArea != null) {
                statusArea.append(result + "\n");
            }
            if(result.endsWith("SHIP FOUND!\n")){
                ship_found = true;
                shipFound(panel);
            }
        });

        // ----------------------
        //      Return
        // ----------------------
        return imageButton;
    }
}
