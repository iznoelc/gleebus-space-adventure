package Level_2;

public interface Validator {
    /**
     *
     * @param nextValidator
     */
    void setNextValidator(Validator nextValidator);

    /**
     *
     * @param registration
     * @throws ValidationException
     */
    void validate(UserRegistration registration) throws ValidationException;
}
