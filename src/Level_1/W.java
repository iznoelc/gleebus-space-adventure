package Level_1;

public class W implements Command{
    @Override
    public String execute(Map map) {
        return map.update("west");
    }
}
