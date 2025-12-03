package Level_1;

public interface Command {
    /**
     * @param map
     * @return String
     * interface for directional commands (North,South,East,West)
     */
    public String execute(Map map);
}
