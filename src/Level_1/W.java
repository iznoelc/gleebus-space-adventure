package Level_1;

public class W implements Command{
    @Override
    public void execute(Map map) {
        System.out.println(map.update("west"));
    }
}
