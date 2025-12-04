package Level_4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Manages all actions Gleebus can take during the battle.
 * @author Izzy Carlson
 */
public class GleebusController {
    private static final int MAX_ENEMIES = 5; // the maximum number of enemies gleebus has to fight before he can move on

    private BattleHandler battleUI;
    private Gleebus gleebus;
    private EnemyController enemyController;
    private Random r = new Random();

    /**
     * Constructor to initialize set up necessary references.
     * @param battleUI, Panel where battle is taking place.
     * @param gleebus, A reference to the actual Gleebus object (where his attack damage, health, etc. are stored)
     * @param enemyController, A reference to the enemies actions.
     */
    public GleebusController(BattleHandler battleUI, Gleebus gleebus, EnemyController enemyController){
        this.battleUI = battleUI;
        this.gleebus = gleebus;
        this.enemyController = enemyController;
    }

    /**
     * Manages what Gleebus does when he guards--blocking the enemy's attack. It will be effective
     * most of the time, but has a slim chance to fail. In that case, the enemy will still take a turn and damage Gleebus.
     */
    public void guardHelper(){
        double chance = r.nextDouble();
        if (chance < 0.95){
            battleUI.getDisplayMessage().setText("Gleebus successfully guarded against the enemy's attack! He takes no damage.");
            enemyController.enemyTurn(false); // simulate enemy taking turn (for game flow with the timer) but don't actually do any damage
        } else {
            battleUI.getDisplayMessage().setText("Gleebus' guard efforts failed!");
            enemyController.enemyTurn(true);
        }
        battleUI.updateUI();
    }

    /**
     * Manages what Gleebus does when he rests--heal for a random number of health points between 1 and
     * half his max health.
     */
    public void restHelper(){
        int amnt = r.nextInt(gleebus.getMaxHealth() / 2) + 1;
        gleebus.heal(amnt);
        battleUI.getDisplayMessage().setText("Gleebus rested and healed for " + amnt + " health points!");
        battleUI.drawHealth();
        enemyController.enemyTurn(true);
        battleUI.updateUI();
    }

    /**
     * Manages what Gleebus does when he uses his ability. When Gleebus uses his ability, there is a rare chance
     * he will successfully use it. In this case, he instantly kills his enemy. There is also a rare chance for it to
     * backfire, where Gleebus will damage himself AND the enemy will take a turn. Most of the time, though, Gleebus may
     * fail to use his ability. In this case, he will attack normally.
     * @param successRate The success rate of Gleebus' ability
     * @param failRate The fail rate of Gleebus' ability
     */
    public void abilityHelper(double successRate, double failRate){
        double chance = r.nextDouble();
        ActionListener abilityListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chance < successRate){
                    // gleebus uses his ability, so the enemy is instantly killed a new one must be spawned
                    enemyController.spawnEnemy();
                    battleUI.updateUI();
                } else if (chance > failRate) {
                    // gleebus ability backfires, so enemy should take a turn after gleebus takes damage
                    enemyController.enemyTurn(true);
                } else {
                    // gleebus fails to use his ability, but it doesn't backfire, so he attacks normally
                    attackerHelper();
                }
            }
        };

        // handle updating the display message based on success of his ability
        if (chance < successRate) {
            enemyController.getCurrentEnemy().takeDamage(enemyController.getCurrentEnemy().getHealth());
            battleUI.getDisplayMessage().setText("[RARE] Gleebus used his special ability and instantly killed the enemy!");
        } else if (chance > failRate) {
            battleUI.getDisplayMessage().setText("[RARE] Gleebus' ability BACKFIRES and he took damage instead!");
            gleebus.takeDamage(3);
            battleUI.drawHealth();
            battleUI.updateUI();
        } else {
            battleUI.getDisplayMessage().setText("Gleebus failed to use his ability and attacks normally.");
        }
        battleUI.disableAllActionButtons();

        // short timer after gleebus uses his ability to disable all buttons, helps flow of the game and prevent button spamming
        int delay = 1500; // 1.5-second delay, slightly faster than enemy turn
        Timer t = new Timer(delay, abilityListener);
        t.setRepeats(false);
        t.start();

        battleUI.updateUI();
    }

    /**
     * Manages what Gleebus does when attacks normally.
     */
    public void attackerHelper(){
        // generate a value for gleebus to attack the enemy for, update the UI, and then make the enemy take the damage
        int atk = gleebus.attack();
        battleUI.getDisplayMessage().setText("Gleebus attacked the current enemy for " + atk + " damage.");
        enemyController.getCurrentEnemy().takeDamage(atk);

        // when an enemy is killed
        if (enemyController.getCurrentEnemy().getHealth() <= 0){
            battleUI.updateTotalEnemiesKilled(1); // update number of enemies killed

            // if the number of enemies killed is the max number needed to move on, end the level
            if (battleUI.getTotalEnemiesKilled() == MAX_ENEMIES){
                battleUI.endLevel();
                return;
            }

            // otherwise, we need to spawn a new enemy for gleebus to kill
            enemyController.spawnEnemy();
        } else { // otherwise, the enemy is not killed so move on to the enemy turn
            enemyController.enemyTurn(true);
        }
        battleUI.drawHealth();
        battleUI.updateUI();
    }
}
