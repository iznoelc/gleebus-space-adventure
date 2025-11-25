package Level_1;

import Game.Game;
import javax.swing.*;
import java.awt.*;

public class Level_1 extends JPanel {
    private Game parent;

    //constructor
    public Level_1(Game parent) {
        //setup window layout
        this.parent = parent;
        setLayout(new BorderLayout());

        //make map
        Map map = new Map(5);

        //button setup
        addButton("P1_NorthArrow", "north", map);
        addButton("P1_SouthArrow", "south", map);
        addButton("P1_EastArrow", "east", map);
        addButton("P1_WestArrow", "west", map);
    }



    //helper method
    public void addButton(String image,  String position, Map map){
        //setup for triggering commands
        Invoker invoker = new Invoker();
        Command command = null;

        //make button with image
        ImageIcon ButtonImage = new ImageIcon("src/Images/Puzzle1/" + image + ".png");
        // rescale image
        Image scaledImage = ButtonImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        // cast image back into a ImageIcon for use after scaling
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JButton imageButton = new JButton(scaledIcon);

        //define panel used for positioning
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
            return;
        }

        //finalize and activate trigger based on action listener
        invoker.setCommand(command);
        imageButton.addActionListener(e -> invoker.executeCommand(map));
    }
}
