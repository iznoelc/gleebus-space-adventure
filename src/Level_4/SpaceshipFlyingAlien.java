package Level_4;

import javax.swing.*;
import java.awt.*;

/**
 * Concrete SpaceEnemy implementation of a spaceship flying space enemy.
 * @author Izzy Carlson
 */
public class SpaceshipFlyingAlien implements SpaceEnemy {
    int health;
    int attackDamage;
    private String spawnText;
    private Image image;

    /**
     * Constructor to initialize the spaceship flying space enemy. Ensures health and attack damage are valid.
     * @param health Total health space enemy should have
     * @param attackDamage Total attack damage space enemy should have
     * @param spawnText Text that should display when a space enemy of this kind spawns
     */
    public SpaceshipFlyingAlien(int health, int attackDamage, String spawnText){
        // ensure valid health value
        if (health <= 10 && health > 0){
            this.health = health;
        } else {
            this.health = 10;
        }

        // ensure valid attack damage value
        if (attackDamage <= 5 && attackDamage > 0){
            this.attackDamage = attackDamage;
        } else {
            this.attackDamage = 1;
        }

        this.spawnText = spawnText;
        this.image = new ImageIcon("src/Images/Puzzle4/P4_SpaceshipAlien.png").getImage();
    }

    /**
     * Take a specified amount of damage; if health goes below zero, explicitly set it to zero
     * @param amount of damage to be taken
     */
    @Override
    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health < 0){ this.health = 0; }
    }

    // ------------------------------------------------------------
    //           Getters, see SpaceEnemy for details
    // ------------------------------------------------------------

    @Override
    public Image getImage(){ return this.image; }

    @Override
    public int getHealth() { return this.health; }

    @Override
    public int getAttackDamage() { return this.attackDamage; }

    @Override
    public String getSpawnText() { return this.spawnText; }
}
