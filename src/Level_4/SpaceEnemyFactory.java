package Level_4;

import java.util.Random;

public class SpaceEnemyFactory {
    public SpaceEnemy spawnEnemy(int type){
        String spawnText = "";
        switch (type){
            case 1:
                spawnText = "An asteroid riding alien has spawned!";
                return new AsteroidRidingAlien(5, 1, spawnText);
            case 2:
                spawnText = "An alien flying a spaceship has spawned!";
                return new SpaceshipFlyingAlien(7, 2, spawnText);
            case 3:
                spawnText = "An alien that can shoot lasers from its eyes has spawned!";
                return new LaserAlien(5, 1, spawnText);
            default:
                // if invalid input, generate a random valid integer to spawn a random enemy
                Random r = new Random();
                return spawnEnemy(r.nextInt(3) + 1);
        }
    }
}
