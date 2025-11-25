package Level_1;

public class E implements Command{
    @Override
    public String execute(Map map) {
        return map.update("east");
    }
}
