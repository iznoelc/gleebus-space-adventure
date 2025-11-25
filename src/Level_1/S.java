package Level_1;

public class S implements Command{
    @Override
    public void execute(Map map) {
        System.out.println("Gleebus is going south.");
        map.update("south");
    }
}
