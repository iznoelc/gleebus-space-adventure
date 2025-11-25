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

        //button setup
        addButton("P1_NorthArrow", "up","N");
        addButton("P1_SouthArrow", "down","S");
        addButton("P1_EastArrow", "right","E");
        addButton("P1_WestArrow", "left","W");
    }

    //helper method
    public void addButton(String image,  String position, String debug_message){
        //make button
        ImageIcon myImage = new ImageIcon("src/Images/Puzzle1/" + image + ".png");
        // Rescale the image to 50x50 pixels
        Image scaledImage = myImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

// Wrap the scaled image back into an ImageIcon
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JButton imageButton = new JButton(scaledIcon);

        imageButton.addActionListener(e -> System.out.println(debug_message));
        //define side for positioning
        JPanel Side = new JPanel();
        //adds padding to the side
        Side.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //position boxes and button
        if(position.equals("left") || position.equals("right")){
            Side.setLayout(new BoxLayout(Side, BoxLayout.Y_AXIS));
            Side.add(Box.createVerticalGlue());
            Side.add(imageButton);
            Side.add(Box.createVerticalGlue());
            //position relative to screen side
            if(position.equals("left")){
                add(Side, BorderLayout.WEST);
            } else{
                add(Side, BorderLayout.EAST);
            }
        } else if(position == "up" || position == "down"){
            Side.setLayout(new BoxLayout(Side, BoxLayout.X_AXIS));
            Side.add(Box.createHorizontalGlue());
            Side.add(imageButton);
            Side.add(Box.createHorizontalGlue());
            //position relative to screen side
            if(position.equals("up")){
                add(Side, BorderLayout.NORTH);
            } else{
                add(Side, BorderLayout.SOUTH);
            }
        }
        else{
            System.out.println("Error: Invalid Input");
            return;
        }
    }
}
