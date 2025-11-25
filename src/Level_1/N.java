package Level_1;

public class N implements Command{
    @Override
    public void execute(Map map) {
        System.out.println(map.update("north"));
    }
}
