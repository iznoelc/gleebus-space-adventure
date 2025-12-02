package Level_4;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Gleebus {
    int maxHealth;
    int currentHealth;
    int attackDamageUpperBound;
    boolean alive;
    Image image;

    public Gleebus(int attackDamageUpperBound){
        this.maxHealth = 10;
        this.currentHealth = this.maxHealth;
        this.attackDamageUpperBound = attackDamageUpperBound;
        this.alive = true;
        this.image = new ImageIcon("src/Images/Puzzle4/P4_GleebusInHisShipModded.png").getImage();
    }

    public Image getImage(){ return this.image; }

    public int getCurrentHealth() { return currentHealth; }

    public int attack() {
        Random r = new Random();
        int dmg = r.nextInt(attackDamageUpperBound) + 1;
        System.out.println("Gleebus attacked for " + dmg);
        return dmg;
    }

    public void takeDamage(int amount){
        this.currentHealth -= amount;
        if (this.currentHealth <= 0){
            alive = false;
        }
    }

    public void heal(int amount){
        this.currentHealth += amount;
        if (this.currentHealth > this.maxHealth){
            this.currentHealth = this.maxHealth;
        }
    }
}
