package Level_4;

import java.awt.*;

/**
 * Common interface all SpaceEnemy objects must implement.
 * @author Izzy Carlson
 */
public interface SpaceEnemy {
    /**
     * @return The space enemy's current total health.
     */
    int getHealth();

    /**
     * @return The space enemy's attack damage.
     */
    int getAttackDamage();

    /**
     * @return The text that should display when a space enemy spawns as a String.
     */
    String getSpawnText();

    /**
     * Take a specified amount of damage.
     * @param amount of damage to be taken.
     */
    void takeDamage(int amount);

    /**
     * @return The image of the space enemy.
     */
    Image getImage();
}
