package Level_1;

/**
 * interface for directional commands (North,South,East,West)
 * @author Esperanza Paulino
 */
public interface Command {
    /**
     * @param map
     * @return String
     */
    public String execute(Map map);
}
