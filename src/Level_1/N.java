package Level_1;

import javax.swing.*;

public class N implements Command{
    /**
     * @param map
     * @return String with direction specific add on
     */
    @Override
    public String execute(Map map) {
        return "Heading North......\n" + map.update("north") + "\n";
    }
}
