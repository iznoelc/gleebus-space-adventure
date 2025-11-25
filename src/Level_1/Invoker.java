package Level_1;

public class Invoker {
    //variables
    Command command;

    //methods
    public void setCommand(Command command){
        this.command = command;
    }
    public String executeCommand(Map map) {
        if (command != null) {
            return command.execute(map);
        }
        return "No command set";
    }
}
