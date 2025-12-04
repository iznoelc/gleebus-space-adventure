package Level_1;

import javax.swing.*;

/**
 * Command for moving north (tell which direction player is headed, update map, get relevant location information)
 * @author Esperanza Paulino
 */
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
