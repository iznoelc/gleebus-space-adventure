package Level_1;

public class W implements Command{
    @Override
    public void execute(Map map) {
        System.out.println("Gleebus is going west.");
        map.update("west");
    }
}
