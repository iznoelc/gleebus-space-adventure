package Level_2;

public class UserRegistration {
    //Attributes
    private String planet;
    private String height;
    private String fruit;
    private String job;

    // Constructor
    public UserRegistration(String planet, String height, String fruit, String job){
        this.planet = planet;
        this.height = height;
        this.fruit = fruit;
        this.job = job;
    }

    // returns the username
    public String getPlanet(){
        return planet;
    }

    // returns the password
    public String getHeight(){
        return height;
    }

    // returns the email
    public String getFruit(){
        return fruit;
    }

    // returns the phone number
    public String getJob(){
        return job;
    }
}