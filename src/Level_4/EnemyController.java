package Level_4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Manages enemy related tasks in regard to the battle, such as spawning new enemies or managing the current enemies turn.
 * @author Izzy Carlson
 */
public class EnemyController {
    private BattleHandler battleUI;
    private Gleebus gleebus;
    private Random r = new Random();

    int prevEnemy;
    SpaceEnemy currentEnemy;
    SpaceEnemyFactory sef = new SpaceEnemyFactory();

    /**
     * Constructor to initialize the first enemy and set up necessary variables.
     * @param battleUI Panel where the battle is taking place
     * @param gleebus A reference to Gleebus (player) who is fighting the enemy
     */
    public EnemyController(BattleHandler battleUI, Gleebus gleebus){
        this.battleUI = battleUI;
        this.gleebus = gleebus;

        // spawn the first enemy
        this.prevEnemy = r.nextInt(3) + 1;
        this.currentEnemy = sef.spawnEnemy(prevEnemy);
    }

    /**
     * Manages the enemies turn, which happens on a delay after Gleebus' turn.
     * Most of the time, the enemy will attack Gleebus for whatever the current enemy's attack
     * damage is. On a rare occasion, Gleebus will dodge the attack. If the enemy damages Gleebus enough to kill him,
     * this method will call a game over method.
     * @param takeTurn Whether the enemy should take their turn (damage Gleebus).
     */
    public void enemyTurn(boolean takeTurn){
        ActionListener enemyTurn = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (takeTurn) {
                    double chance = r.nextDouble();
                    // gleebus has about a 5% chance to dodge the attack
                    if (chance < 0.05) {
                        battleUI.getDisplayMessage().setText("Gleebus was able to dodge the enemies attack!");
                    } else {
                        gleebus.takeDamage(currentEnemy.getAttackDamage());
                        battleUI.getDisplayMessage().setText("Gleebus took " + currentEnemy.getAttackDamage() + " damage from the enemy!");

                        // if Gleebus' health reaches zero or below, call game over
                        if (gleebus.getCurrentHealth() <= 0){
                            battleUI.gameOver();
                            return;
                        }
                    }
                    battleUI.drawHealth();
                    battleUI.updateUI();
                }
                battleUI.enableAllActionButtons();
            }
        };

        battleUI.disableAllActionButtons();

        // set up a timer while enemy is taking their turn so player can't just constantly attack
        int delay = 2000;
        Timer timer = new Timer(delay, enemyTurn);
        timer.setRepeats(false);

        timer.start();
    }

    /**
     * Spawns a new enemy without spawning the same kind of enemy twice in a row.
     */
    public void spawnEnemy() {
        int spawnNum;
        do {
            spawnNum = r.nextInt(3) + 1;
        } while (spawnNum == prevEnemy);
        prevEnemy = spawnNum; // update prev enemy to be current enemy
        this.currentEnemy = sef.spawnEnemy(spawnNum); // spawn the new enemy using the enemy factory
        battleUI.getDisplayMessage().setText(this.currentEnemy.getSpawnText()); // set the display message to the new enemy's spawn msg
        battleUI.enableAllActionButtons(); // ensure buttons are enabled
    }

    /**
     * Get the current enemy Gleebus' is fighting
     * @return A reference to the current enemy
     */
    public SpaceEnemy getCurrentEnemy(){ return this.currentEnemy; }
}
