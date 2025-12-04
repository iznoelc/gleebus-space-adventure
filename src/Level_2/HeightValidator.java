package Level_2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * checks that the height security question is answered 1'5 before passing to the next validator
 * gives hints when the user answers incorrectly
 * @author Esperanza Paulino
 */
public class HeightValidator implements Validator{
    // ------------------------------------------------------------
    //                  Variables
    // ------------------------------------------------------------
    private Validator nextValidator;

    // ------------------------------------------------------------
    //                  Methods
    // ------------------------------------------------------------

    /**
     *
     * @param nextValidator
     */
    //overide validator methods
    @Override
    public void setNextValidator(Validator nextValidator){
        //set next validator
        this.nextValidator = nextValidator;
    }

    /**
     *
     * @param registration
     * @throws ValidationException
     */
    @Override
    public void validate(UserRegistration registration) throws ValidationException{
        //define password to be validated
        String height = registration.getHeight();

        //validate
        if (!Objects.equals(height, "1'5")){
            // throw a new exception that it is invalid and include random hint
            ArrayList<String> hints = new ArrayList<String>();
            hints.add("I am shorter than 2'0");
            hints.add("I am taller than 1'0");
            hints.add("Both numbers are odd.");
            hints.add("The second number is not 1, 3,or 9.");
            Random random = new Random();
            int hint_num = random.nextInt(hints.toArray().length-1);
            throw new ValidationException("Uh oh, thats the wrong height, please try again.\n\nHint: " + hints.get(hint_num));
        }
        //else pass to the next validator
        else if (nextValidator != null){
            nextValidator.validate(registration);
        }
    }
}
