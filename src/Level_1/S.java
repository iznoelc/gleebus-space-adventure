package Level_1;

public class S implements Command{
    @Override
    public String execute(Map map) {
        return map.update("south");
    }
}
