package Level_2;

public interface Validator {
    void setNextValidator(Validator nextValidator);

    void validate(UserRegistration registration) throws ValidationException;
}
