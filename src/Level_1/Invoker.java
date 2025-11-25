package Level_1;

public class Invoker {
    //variables
    Command command;

    //methods
    public void setCommand(Command command){
        this.command = command;
    }
    public void executeCommand(Map map){
        command.execute(map);
    }
}
