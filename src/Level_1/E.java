package Level_1;

public class E implements Command{
    @Override
    public void execute(Map map) {
        System.out.println("Gleebus is going east.");
        map.update("east");
    }
}
