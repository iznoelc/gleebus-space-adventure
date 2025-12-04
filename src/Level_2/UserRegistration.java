package Level_2;

/**
 * sets variables for user input so that they can be run through the validation chain
 * @author Esperanza Paulino
 */
public class UserRegistration {
    // ------------------------------------------------------------
    //                  Variables
    // ------------------------------------------------------------
    private String planet;
    private String height;
    private String fruit;
    private String job;

    // ------------------------------------------------------------
    //                  Constructor
    // ------------------------------------------------------------

    /**
     *
     * @param planet
     * @param height
     * @param fruit
     * @param job
     */
    public UserRegistration(String planet, String height, String fruit, String job){
        this.planet = planet;
        this.height = height;
        this.fruit = fruit;
        this.job = job;
    }

    // ------------------------------------------------------------
    //                  Getters
    // ------------------------------------------------------------

    /**
     *
     * @return String
     */
    public String getPlanet(){
        return planet;
    }

    /**
     *
     * @return String
     */
    public String getHeight(){
        return height;
    }

    /**
     *
     * @return String
     */
    public String getFruit(){
        return fruit;
    }

    /**
     *
     * @return String
     */
    public String getJob(){
        return job;
    }
}