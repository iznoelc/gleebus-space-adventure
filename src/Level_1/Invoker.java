package Level_1;

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
