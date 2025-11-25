package Level_1;

public class N implements Command{
    @Override
    public void execute(Map map) {
        System.out.println("Gleebus is going north.");
        map.update("north");
    }
}
