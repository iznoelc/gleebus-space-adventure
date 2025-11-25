package Level_1;

public class Invoker {
    Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void executeCommand(Map map){
        command.execute(map);
    }
}
