package Level_4;

import javax.swing.*;
import java.awt.*;

public class SpaceshipFlyingAlien implements SpaceEnemy {
    int health;
    int attackDamage;
    String spawnText;
    Image image;

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

    @Override
    public Image getImage(){ return this.image; }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public String getSpawnText() {
        return this.spawnText;
    }

    @Override
    public void takeDamage(int amount) {
        this.health -= amount;
    }
}
