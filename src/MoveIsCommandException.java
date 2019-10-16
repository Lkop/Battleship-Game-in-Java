package battleship;

/**
 *
 * @author Lkop
 */
public class MoveIsCommandException extends InvalidLocationException {
    
    private Command command;
    
    public MoveIsCommandException(Command cmd){
    
        command = cmd;
    }

    public Command getCommand(){
    
        return command;
    }
}
