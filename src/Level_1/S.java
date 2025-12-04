package Level_1;

/**
 * Command for moving south (tell which direction player is headed, update map, get relevant location information)
 * @author Esperanza Paulino
 */
public class S implements Command{
    /**
     * @param map
     * @return String with direction specific add on
     */
    @Override
    public String execute(Map map) {
        return "Heading South......\n" + map.update("south") + "\n";
    }
}
