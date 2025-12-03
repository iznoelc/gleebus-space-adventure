package Level_2;

import Game.Game;
import Game.NextCard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level_2 extends JPanel {

    // ------------------------------------------------------------
    //                  Variables
    // ------------------------------------------------------------

    private Game parent;
    private ImageIcon backgroundImage;
    private ImageIcon foregroundImage;
    private  JButton nextLevelButton;

    // ------------------------------------------------------------
    //                  Constructor
    // ------------------------------------------------------------

    /**
     *
     * @param parent
     */
    public Level_2(Game parent) {
        // ----------------------
        //      Setup Screen
        // ----------------------

        this.parent = parent;
        // Load background image
        this.backgroundImage = new ImageIcon("src/Images/P1_Screen.png");
        //seperate for more control in overlay transparency in later editing
        this.foregroundImage = new ImageIcon("src/Images/P1_ScreenOverlay.png");

        JTextArea outputArea = new JTextArea(5, 50);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Event Output"));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ----------------------
        //      Instructions
        // ----------------------

        //position
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        //size
        gbc.gridwidth = 2;
        //text
        JLabel instructions= new JLabel("<html><div style='text-align:center'>Uh oh... Gleebus is locked out! And he's forgotten his password.<br>He can reset it with security questions but... he forgot that too. <br><br>Help Gleebus get into his ship by entering the answers!<br><br>");
        //font type
        Font mediumFont = new Font("Serif", Font.BOLD, 20);
        instructions.setFont(mediumFont);
        //add
        add(instructions, gbc);
        //reset for everything else
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // ----------------------
        //      Title
        // ----------------------

        //position
        gbc.gridy = 1;
        //text
        JLabel title = new JLabel("Security Questions: ");
        //font type
        Font bigFont = new Font("Serif", Font.BOLD, 50);
        title.setFont(bigFont);
        //add
        add(title, gbc);

        // ----------------------
        //      Questions
        // ----------------------

        //done in the same way as the title but with a different font type

        // Question 1
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel planetLabel = new JLabel("What is the name of Gleebus's home planet?");
        add(planetLabel, gbc);
        JTextField planetField = new JTextField(20);
        Font smallFont = new Font("Serif", Font.PLAIN, 20);
        planetLabel.setFont(smallFont);
        planetField.setFont(smallFont);
        gbc.gridx = 1;
        add(planetField, gbc);

        // Add Question 2
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel heightLabel = new JLabel("How tall is Gleebus? (use number'number notation)");
        add(heightLabel, gbc);
        JTextField heightField = new JTextField(20);
        heightLabel.setFont(smallFont);
        heightField.setFont(smallFont);
        gbc.gridx = 1;
        add(heightField, gbc);

        // Add Question 3
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel foodLabel = new JLabel("What is Gleebus's favorite earth food?");
        add(foodLabel, gbc);
        foodLabel.setFont(smallFont);
        JTextField foodField = new JTextField(20);
        foodField.setFont(smallFont);
        gbc.gridx = 1;
        add(foodField, gbc);

        // Add Question 4
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel jobLabel = new JLabel("What is Gleebus's day job?");
        add(jobLabel, gbc);
        jobLabel.setFont(smallFont);
        JTextField jobField = new JTextField(20);
        jobField.setFont(smallFont);
        gbc.gridx = 1;
        add(jobField, gbc);

        // ----------------------
        //      Buttons
        // ----------------------

        // Add an Enter button
        gbc.gridx = 0;
        gbc.gridy = 6;
        JButton registerButton = new JButton("Check Answers");
        registerButton.setFont(smallFont);
        gbc.gridx = 1;
        add(registerButton, gbc);
        registerButton.addActionListener(new ActionListener() {
            // if the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the registerUser method
                registerUser(planetField, heightField, foodField, jobField);
            }
        });

        // Next button at bottom
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        nextLevelButton = new NextCard().getNextCardButton(
                parent, "<html>That's Correct! <br>Enter Spaceship.<br><font size = 3>(for real this time)", 400, 100, "Level3", 18
        );
        nextLevelButton.setEnabled(false);
        nextLevelButton.setVisible(false);
        add(nextLevelButton, gbc);
    }

    // ------------------------------------------------------------
    //                  Helper Methods
    // ------------------------------------------------------------

    /**
     *
     * @param g the <code>Graphics</code> object to protect
     * used to set background image of screen
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 1280, 720, this);
        g.drawImage(foregroundImage.getImage(), 0, 0, 1280, 720, this);

    }

    /**
     *
     * @param planetField
     * @param heightField
     * @param foodField
     * @param jobField
     */
        // Allows the checks registration
        public void registerUser(JTextField planetField, JTextField heightField, JTextField foodField, JTextField jobField){
            // create a new JFrame
            JFrame valid = new JFrame("Confirmation");
            valid.setSize(400, 300);
            valid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            valid.setLocationRelativeTo(null);

            // create a new user and get their credentials based on the fields they typed in
            UserRegistration user = new UserRegistration(
                    planetField.getText(),
                    heightField.getText(),
                    foodField.getText(),
                    jobField.getText()
            );

            // set up the validator chain
            Validator validatorChain = setUpValidatorChain();

            try{
                // start validating the credentials
                validatorChain.validate(user);

                // if everything is fine
                nextLevelButton.setVisible(true);
                nextLevelButton.setEnabled(true);


            } catch (ValidationException ex) {
                // one or more of the credentials were incorrect so we instead show an error message
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    /**
     *
     * @return
     */
    public static Validator setUpValidatorChain(){
            // creating the validators
            Validator planetValidator = new PlanetValidator();
            Validator heightValidator = new HeightValidator();
            Validator foodValidator = new FruitValidator();
            Validator jobValidator = new JobValidator();

            // setting up the validators
            planetValidator.setNextValidator(heightValidator);
            heightValidator.setNextValidator(foodValidator);
            foodValidator.setNextValidator(jobValidator);

            // returning the first validator
            return planetValidator;
        }
    }
