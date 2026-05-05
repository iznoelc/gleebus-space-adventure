# Gleebus' Space Adventure
This project aims to implement **four object-oriented design patterns** in *Java* to support the core functionality of a short text-based story game, with the components being created and displayed in *JavaSwing*. The game follows *Gleebus*, a cat-like alien creature who crash landed on Earth. He's spent some time on the planet, but now he's ready to go home! The player will help him find his spaceship, repair it, and then battle enemies, all with the goal of getting Gleebus home!

## Demo
See the demo [here](https://www.youtube.com/watch?v=u01gnLfvYys) on YouTube

## Brief Overview of Patterns
### Level 1 -> Command
Level 1 lets the player move Gleebus around a 5x5 map using directional arrow buttons. The goal is for Gleebus to find his spaceship. Each button is a product of the Command pattern which tells the user which direction Gleebus moved in, if he's found the spaceship (or any other items), and hints at how close the ship is on a cold to hot scale.

### Level 2 -> Chain of Responsibility (COR)
Level 2 simulates Gleebus' spaceship password system as it asks security questions. Chain of Responsibility was used for the validators(handlers) since the information had to pass a multi-level authentication system. As an add on hints are randomly generated when the user recieves an error.

### Level 3 -> Decorator
Level 3 allows the player to repair Gleebus' spaceship. Decorator was used because we needed a base component (the broken spaceship) and a way to "decorate" this base component (repairs, such as the lever and button panel). 

### Level 4 -> Factory
Level 4 aims to create a short turn-based combat system. Instantiation and creation of enemies was done using the Factory pattern. This was done in order to be able to treat all enemies the same; each enemy needs to have their health decreased when attacked, be able to retrieve their attack damage when attacking the player, etc. Using factory pattern allows for the instantiation of new enemy objects that can be treated similarly due to their common interface.

## Resources
### Code Resources
- https://stackoverflow.com/questions/29879040/java-cardlayout-wrong-parent 
- https://stackoverflow.com/questions/60422149/vertically-center-content-with-boxlayout 
- https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html 
- https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java 
- https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel 
- https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html 
- https://examples.javacodegeeks.com/java-development/desktop-java/swing/java-swing-boxlayout-example/
- https://stackoverflow.com/questions/5895829/resizing-image-in-java 
- https://docs.oracle.com/javase/tutorial/uiswing/painting/step2.html
- https://stackoverflow.com/questions/8776540/painting-over-the-top-of-components-in-swing
- https://stackoverflow.com/questions/1614772/how-to-change-jframe-icon

### Asset Resources
- All Gleebus asset art found in src/[Images](https://github.com/iznoelc/GleebusSpaceAdventure/tree/main/src/Images) folder done by Izzy Carlson

#### Fonts Used
- [Alien Space](https://www.1001fonts.com/alien-font.html)
- [Alien Dot](https://www.dafont.com/aliendot.font)
- [Astroz](https://www.dafont.com/astroz.font)
