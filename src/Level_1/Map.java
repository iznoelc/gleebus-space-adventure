package Level_1;

import java.util.Arrays;
import java.util.Random;

public class Map {
    //variables for constructor
    int[] player_position;
    int[] ship_position;
    int[][] ship_range;
    int[][] item_positions;
    int max;
    Random random = new Random();

    //constructor
    Map(int max){
        this.max = max;
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

    public void update(String direction){
        // update player location
        switch (direction) {
            case "west":
                if (player_position[0] > 0)
                {player_position[0]-=1;} else {System.out.println("Gleebus is too far west, he's no longer getting a status signal from his ship!");}
                break;
            case "east":
                if (player_position[0] < max)
                {player_position[0]+=1;}else {System.out.println("Gleebus is too far east, he's no longer getting a status signal from his ship!");}
                break;
            case "north":
                if (player_position[1] > 0)
                {player_position[1]-=1;}else {System.out.println("Gleebus is too far north, he's no longer getting a status signal from his ship!");}
                break;
            case "south":
                if (player_position[1] < max)
                {player_position[1]+=1;}else {System.out.println("Gleebus is too far south, he's no longer getting a status signal from his ship!");}
                break;
        }

        // check if Gleebus found his spaceship
        if (Arrays.equals(player_position, ship_position)) {
            System.out.println("SHIP FOUND!");
            return;
        }

        // give hot/cold hint based on ship and player position
        String[] hot_cold = {"hot", "warm", "cool", "cold"};
        for (int[] range : ship_range) {
            if (player_position[0] > range[0] && player_position[0] < range[1] && player_position[1] > range[2] && player_position[1] < range[3]) {
                System.out.println("Location Status: " + hot_cold[range[4]-1]);
                break;
            }
        }

        // check if Gleebus found any other items, stretch goal
        String[] item_names = {
                "cat-nip...he'll take that with him.",
                "comb, pretty but useless against his tangled fur.",
                "yarn! He thought he had lost this!"
        };
        for (int[] item : item_positions) {
            if (player_position[0] == item[0] && player_position[1] == item[1]) {
                System.out.println("Gleebus has stumbled upon his " + item_names[item[2]-1]);
            }
        }
    }
}
