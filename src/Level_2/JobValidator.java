package Level_2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class JobValidator implements Validator{
    //attributes
    private Validator nextValidator;

    //override validation methods
    @Override
    public void setNextValidator(Validator nextValidator){
        //sets the next validator
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(UserRegistration registration) throws ValidationException{
        //defines phone # to be validated
        String job = registration.getJob();

        if (!Objects.equals(job, "Mechanic")){

            // throw a new exception that the email is invalid
            ArrayList<String> hints = new ArrayList<String>();
            hints.add("Without me, your car or spaceship won't get far.");
            hints.add("I use wrenches, screwdrivers, and sockets like a surgeon uses tools.");
            hints.add("Oil stains are my badge of honor.");
            hints.add("I work with my hands to fix what’s broken.");
            Random random = new Random();
            int hint_num = random.nextInt(hints.toArray().length-1);
            throw new ValidationException("Uh oh, thats the wrong job, please try again.\n\nHint: " + hints.get(hint_num));
        }
        // else pass to next validator
        else if (nextValidator != null){
               nextValidator.validate(registration);
        }
    }
}
