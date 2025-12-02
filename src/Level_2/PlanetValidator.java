package Level_2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class PlanetValidator implements Validator{
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
        //define username to validate
        String planet = registration.getPlanet();

        // if username is null or less than 5 characters long throw error
        if (!Objects.equals(planet, "Gleebus")){

            // throw a new exception that the email is invalid
            ArrayList<String> hints = new ArrayList<String>();
            hints.add("My name is in the question.");
            hints.add("Many citizens are named after me.");
            hints.add("My name starts with a 'G'.");
            Random random = new Random();
            int hint_num = random.nextInt(hints.toArray().length-1);
            throw new ValidationException("Uh oh, thats the wrong name, please try again.\n\nHint: " + hints.get(hint_num));
        }
        //else pass to next validator
        else if (nextValidator != null) {
            nextValidator.validate(registration);
        }
    }
}
