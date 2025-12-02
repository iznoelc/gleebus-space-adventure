package Level_2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class FruitValidator implements Validator{
    //attributes
    private Validator nextValidator;

    //override validator methods
    @Override
    public void setNextValidator(Validator nextValidator){
        // sets the next validator
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(UserRegistration registration) throws ValidationException{

        String fruit = registration.getFruit();

        if (!Objects.equals(fruit, "Banana")){

            // throw a new exception that the email is invalid
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
        // else pass to the next validator
        else if (nextValidator != null){
                nextValidator.validate(registration);
            }
    }
}
