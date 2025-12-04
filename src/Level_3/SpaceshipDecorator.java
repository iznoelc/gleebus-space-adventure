package Level_3;

import java.util.ArrayList;

/**
 * Hold a reference to spaceship decorator and delegate the calls.
 * @author Izzy Carlson
 */
public abstract class SpaceshipDecorator implements Spaceship {
    protected Spaceship decoratedGleebusShip;

    public SpaceshipDecorator(Spaceship gleebusShip){
        decoratedGleebusShip = gleebusShip;
    }

    /**
     * Adds a component to decorate the base spaceship with to the spaceship ArrayList of all components.
     * It is stored like this due to the nature of JavaSwing, so that the components can be drawn on top of each other,
     * effectively "decorating" the concrete component, or base, broken spaceship.
     */
    @Override
    public void addComponent() {
        decoratedGleebusShip.getComponents().add(this);
    }

    /**
     * Gets all the components currently decorating the spaceship, including the concrete component.
     * @return All the components as an ArrayList
     */
    @Override
    public ArrayList<Spaceship> getComponents(){
        return this.decoratedGleebusShip.getComponents();
    }

}
