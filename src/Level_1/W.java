package Level_1;

/**
 * Command for moving west (tell which direction player is headed, update map, get relevant location information)
 * @author Esperanza Paulino
 */
public class W implements Command{
    /**
     * @param map
     * @return String with direction specific add on
     */
    @Override
    public String execute(Map map) {
        return "Heading West......\n" + map.update("west") + "\n";
    }
}
