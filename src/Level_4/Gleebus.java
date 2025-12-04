package Level_4;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Manages Gleebus as an attackable entity. Actions he can take (attack, rest, etc. are handled separately (see GleebusController).
 * @author Izzy Carlson
 */
public class Gleebus {
    private int maxHealth;
    private int currentHealth;
    private int attackDamageUpperBound;
    private Image image;
    private Random r = new Random();

    /**
     * Constructor to initialize Gleebus' max health, attack damage, and image.
     * @param maxHealth The maximum amount of health Gleebus should have
     * @param attackDamageUpperBound The maximum amount of damage Gleebus should be able to do in one turn
     */
    public Gleebus(int maxHealth, int attackDamageUpperBound){
        this.maxHealth = 10;
        this.currentHealth = this.maxHealth;
        this.attackDamageUpperBound = attackDamageUpperBound;
        this.image = new ImageIcon("src/Images/Puzzle4/P4_GleebusInHisShipModded.png").getImage();
    }

    /**
     * @return The image of Gleebus riding his spaceship, flipped.
     */
    public Image getImage(){ return this.image; }

    /**
     * @return The current amount of health Gleebus has.
     */
    public int getCurrentHealth() { return currentHealth; }

    /**
     * Attack a random amount between 1 and Gleebus' damage upper bound.
     * @return The attack damage value generated
     */
    public int attack() { return r.nextInt(attackDamageUpperBound) + 1; }

    /**
     * Take a specified amount of damage. If damage ever reaches below zero, explicitly set it to be 0.
     * @param amount of damage to be taken.
     */
    public void takeDamage(int amount){
        this.currentHealth -= amount;
        if (this.currentHealth <= 0){
            this.currentHealth = 0;
        }
    }

    /**
     * Heal a specified number of health points. If health points ever expand past Gleebus' max health, explicitly set it
     * to be the max health.
     * @param amount of health points to heal
     */
    public void heal(int amount){
        this.currentHealth += amount;
        if (this.currentHealth > this.maxHealth){
            this.currentHealth = this.maxHealth;
        }
    }

    /**
     * Reset Gleebus' health to full.
     */
    public void resetHealth(){ this.currentHealth = this.maxHealth; }

    /**
     * @return The max health Gleebus can have.
     */
    public int getMaxHealth(){ return this.maxHealth; }

    /**
     * @return The maximum amount of damage Gleebus can do.
     */
    public int getAttackDamageUpperBound(){ return this.attackDamageUpperBound; }
}
