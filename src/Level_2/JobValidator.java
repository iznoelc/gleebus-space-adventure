package Level_2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class JobValidator implements Validator{
    // ------------------------------------------------------------
    //                  Variables
    // ------------------------------------------------------------
    private Validator nextValidator;

    // ------------------------------------------------------------
    //                  Methods
    // ------------------------------------------------------------

    /**
     * @param nextValidator
     */
    //override validation methods
    @Override
    public void setNextValidator(Validator nextValidator){
        //sets the next validator
        this.nextValidator = nextValidator;
    }

    /**
     *
     * @param registration
     * @throws ValidationException
     */
    @Override
    public void validate(UserRegistration registration) throws ValidationException{
        //defines phone # to be validated
        String job = registration.getJob();

        //validate
        if (job.equals("mechanic") || job.equals("Mechanic")
                && nextValidator != null){
            nextValidator.validate(registration);
        }
        // else pass to next validator
        else if (nextValidator != null){
            // throw a new exception that it is invalid, plus a random hint
            ArrayList<String> hints = new ArrayList<String>();
            hints.add("Without me, your car or spaceship won't get far.");
            hints.add("I use wrenches, screwdrivers, and sockets like a surgeon uses tools.");
            hints.add("Oil stains are my badge of honor.");
            hints.add("I work with my hands to fix what’s broken.");
            Random random = new Random();
            int hint_num = random.nextInt(hints.toArray().length-1);
            throw new ValidationException("Uh oh, thats the wrong job, please try again.\n\nHint: " + hints.get(hint_num));
        }
    }
}
