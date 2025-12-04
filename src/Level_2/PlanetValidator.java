package Level_2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * checks that the planet name security question is answered Gleebus before passing to the next validator
 * gives hints when the user answers incorrectly
 * @author Esperanza Paulino
 */
public class PlanetValidator implements Validator{
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
        //define username to validate
        String planet = registration.getPlanet();

        // validate name
        if (!Objects.equals(planet, "Gleebus")){
            // throw a new exception that its invalid and add a random hint
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
