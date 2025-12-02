package Level_4;

import java.awt.*;

public interface SpaceEnemy {
    int getHealth();
    int getAttackDamage();
    String getSpawnText();
    void takeDamage(int amount);
    Image getImage();
}
