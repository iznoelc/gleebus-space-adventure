package Level_1;

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
