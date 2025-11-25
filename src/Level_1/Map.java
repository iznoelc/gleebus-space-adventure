package Level_1;

import java.util.Random;

public class Map {
    //change these to be arraylists or just list
    //so that I can set them later by adding
    int[] player_position;
    int[] ship_position;
    int[][] item_positions;
    int max;
    Random random = new Random();
    Invoker invoker = new Invoker();

    Map(int max){
        this.max = max;
        //int rand_num = random.nextInt((max) + 1);

        this.player_position = new int[]{max/2,max/2};
        this.ship_position = new int[]{random.nextInt(max+1), random.nextInt(max+1)};
        this.item_positions = new int[][] {
                {random.nextInt(max+1), random.nextInt(max+1),1},
                {random.nextInt(max+1), random.nextInt(max+1),2},
                {random.nextInt(max+1), random.nextInt(max+1),3}
        };
        String[][] item_names = new String[][]{
                {"Item 1"}, {"Item 2"}, {"Item 3"}
        };
    }

    public void update(String direction){
        //update position
        switch (direction){
            case "left":
                this.player_position[0] -= 1;
                break;
            case "right":
                this.player_position[0] += 1;
                break;
            case "up":
                this.player_position[1] -= 1;
                break;
            case "down":
                this.player_position[1] += 1;
                break;
        }
        //say hot or cold based on possition to ship

        //check if they've found an object, optional stretch goal
        for (int[] item : item_positions) {
            if(item[0] == player_position[0] || item[1] == player_position[1]){
                System.out.println("Gleebus has stumbled upon " + item[2]);
            } else{
                System.out.println("Gleebus did not find anything, debugging");
            }
        }
    }

}
