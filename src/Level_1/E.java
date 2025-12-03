package Level_1;

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
