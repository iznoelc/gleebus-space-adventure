package Level_2;

/**
 * allows for customized error pop-ups based on the passed in message
 * @author Esperanza Paulino
 */
public class ValidationException extends Exception{
    /**
     *
     * @param message
     */
    public ValidationException(String message){
        super(message);
    }
}
