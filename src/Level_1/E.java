package Level_1;

/**
 * Command for moving east (tell which direction player is headed, update map, get relevant location information)
 * @author Esperanza Paulino
 */
public class E implements Command{
    /**
     * @param map
     * @return String with direction specific add on
     */
    @Override
    public String execute(Map map) {
        return "Heading East......\n" + map.update("east") + "\n";
    }
}
