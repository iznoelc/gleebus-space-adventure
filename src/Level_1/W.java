package Level_1;

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
