package Level_1;

public class E implements Command{
    @Override
    public void execute(Map map) {
        System.out.println(map.update("east"));
    }
}
