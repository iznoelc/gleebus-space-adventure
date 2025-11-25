package Level_1;

import javax.swing.*;

public class N implements Command{
    @Override
    public String execute(Map map) {
        return map.update("north");
    }
}
