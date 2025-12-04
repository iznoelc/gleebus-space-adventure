package Level_2;

import java.util.ArrayList;
import java.util.Random;

/**
 * checks that the favorite food security question is answered banana before passing to the next validator
 * gives hints when the user answers incorrectly
 * @author Esperanza Paulino
 */
public class FruitValidator implements Validator{
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
    //override validator methods
    @Override
    public void setNextValidator(Validator nextValidator){
        // sets the next validator
        this.nextValidator = nextValidator;
    }

    /**
     *
     * @param registration
     * @throws ValidationException
     */
    @Override
    public void validate(UserRegistration registration) throws ValidationException{
        String fruit = registration.getFruit();

        if (fruit.equals("bananas") || fruit.equals("Bananas") || fruit.equals("banana") || fruit.equals("Banana")) {
            if (nextValidator != null) {
                nextValidator.validate(registration);
            }
        }
        // else pass to the next validator
        else {
            // throw a new exception that it is invalid and include random hint
            ArrayList<String> hints = new ArrayList<String>();
            hints.add("I'm long and curved like a smile.");
            hints.add("I change from green to yellow.");
            hints.add("You peel me before you eat me.");
            hints.add("I hate being lonely, I usually hang out in a bunch.");
            hints.add("I'm a bit of a trickster, I always slip people up.");
            Random random = new Random();
            int hint_num = random.nextInt(hints.toArray().length-1);
            throw new ValidationException("Uh oh, thats the wrong fruit, please try again.\n\nHint: " + hints.get(hint_num));
            }
    }
}
