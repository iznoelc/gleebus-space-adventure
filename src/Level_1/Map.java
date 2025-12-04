package Level_1;

import java.util.Arrays;
import java.util.Random;

public class Map {

    // ------------------------------------------------------------
    //                  Variables
    // ------------------------------------------------------------

    int[] player_position;
    int[] ship_position;
    int[][] ship_range;
    int[][] item_positions;
    int max;
    boolean isEdge;
    Random random = new Random();

    // ------------------------------------------------------------
    //                  Constructor
    // ------------------------------------------------------------

    /**
     *
     * @param max
     * setups position of player, ship, and items
     */
    Map(int max){
        this.max = max;
        this.isEdge = false;
        this.player_position = new int[]{max/2, max/2};
        this.ship_position = new int[]{random.nextInt(max+1), random.nextInt(max+1)};
        this.ship_range = new int[][] {
                {ship_position[0]-2, ship_position[0]+2, ship_position[1]-2, ship_position[1]+2, 1},
                {ship_position[0]-3, ship_position[0]+3, ship_position[1]-3, ship_position[1]+3, 2},
                {ship_position[0]-4, ship_position[0]+4, ship_position[1]-4, ship_position[1]+4, 3},
                {ship_position[0]-50, ship_position[0]+50, ship_position[1]-50, ship_position[1]+50, 4}
        };
        this.item_positions = new int[][] {
                {random.nextInt(max+1), random.nextInt(max+1), 1},
                {random.nextInt(max+1), random.nextInt(max+1), 2},
                {random.nextInt(max+1), random.nextInt(max+1), 3}
        };
    }

    // ------------------------------------------------------------
    //                  Update Method
    // ------------------------------------------------------------

    /**
     *
     * @param direction
     * @return String
     * update position and check if Gleebus has found anything
     */
    public String update(String direction){
        // ----------------------
        //      Player
        // ----------------------

        // update player location on map
        switch (direction) {
            case "west":
                if (player_position[0] > 0)
                {player_position[0]-=1;} else {isEdge = true;}
                break;
            case "east":
                if (player_position[0] < max)
                {player_position[0]+=1;}else {isEdge = true;}
                break;
            case "north":
                if (player_position[1] > 0)
                {player_position[1]-=1;}else {isEdge = true;}
                break;
            case "south":
                if (player_position[1] < max)
                {player_position[1]+=1;}else {isEdge = true;}
                break;
        }

        // ----------------------
        //      Spaceship
        // ----------------------

        // check if Gleebus found his spaceship
        if (Arrays.equals(player_position, ship_position)) {
            return "Location Status: Boiling Hot\nSHIP FOUND!";
        }

        // ----------------------
        //      Edge
        // ----------------------

        if(isEdge == true){
            //reset for next time
            isEdge = false;
            return "Gleebus has gone too far in this direction! He is out of the ships range, try heading back the way you came.";
        }


        // ----------------------
        //      Ship Status
        // ----------------------

        //status setup
        String status = "";

        // give hot/cold hint based on ship and player position
        String[] hot_cold = {"hot", "warm", "cool", "cold"};
        for (int[] range : ship_range) {
            if (player_position[0] > range[0] && player_position[0] < range[1] && player_position[1] > range[2] && player_position[1] < range[3]) {
                status = "Location Status: " + hot_cold[range[4]-1];
                break;
            }
        }

        // ----------------------
        //      Item Found?
        // ----------------------

        // check if Gleebus found any other items
        String[] item_names = {
                "cat-nip...he'll take that with him.",
                "comb, pretty but useless against his tangled fur.",
                "yarn! He thought he had lost this!"
        };
        for (int[] item : item_positions) {
            if (player_position[0] == item[0] && player_position[1] == item[1]) {
                status += "\nGleebus has stumbled upon his " + item_names[item[2]-1];
            }
        }

        // ----------------------
        //      Return
        // ----------------------

        return status;
    }
}
