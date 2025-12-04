package Level_1;

/**
 * invoker, can be used to set and trigger any command (N,S,E,W)
 * @author Esperanza Paulino
 */
public class Invoker {
    //variables
    Command command;

    //methods
    /**
     *
     * @param command
     */
    public void setCommand(Command command){
        this.command = command;
    }

    /**
     *
     * @param map
     * @return String
     */
    public String executeCommand(Map map) {
        if (command != null) {
            return command.execute(map);
        }
        return "No command set";
    }
}
