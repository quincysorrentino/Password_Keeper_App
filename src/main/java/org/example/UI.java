package org.example;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class UI extends JFrame {

    private JTextField descriptionField;
    public String descriptionFieldTextFindLogin;
    public String descriptionFieldTextCreateNewLogin;
    public String usernameFieldText;
    public String passwordFieldText;
    public int passwordLengthFieldValue;
    public int numSpecialCharactersFieldValue;
    public String randomPassword;
    private Credential searchedLogin;
    public String editLoginUsername;
    public String editLoginPassword;

    private JLabel descriptionDisplay;
    private JLabel usernameDisplay;
    private JLabel passwordDisplay;

    private JList<String> loginsDisplay;
    private ArrayList<String> loginsData;

    private String selectedLoginLoginDisplay;


    public UI(){

        Constants constants = new Constants();

        Service service = new Service();

        Session session = service.createHibernateConfiguration();


        createLoginsDisplay(service, session);

        createFindLogin(service, session);

        createCreateNewLogin(service, session, constants);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 800);
        this.setResizable(false);
        this.setVisible(true);

    }

    private void createLoginsDisplay(final Service service, final Session session){

        final JLabel descriptionLabel = new JLabel("Website/App Name:");
        descriptionLabel.setBounds(15, 10, 225, 15);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));

        descriptionDisplay = new JLabel();
        descriptionDisplay.setBounds(25, 40, 225, 15);
        descriptionDisplay.setFont(new Font("Arial", Font.PLAIN, 12));

        final JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(15, 70, 225, 15);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        usernameDisplay = new JLabel();
        usernameDisplay.setBounds(25, 100, 225, 15);
        usernameDisplay.setFont(new Font("Arial", Font.PLAIN, 12));

        final JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(15, 130, 225, 15);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));

        passwordDisplay = new JLabel();
        passwordDisplay.setBounds(25, 160, 225, 15);
        passwordDisplay.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel descriptionLabelEditPopup = new JLabel("Website/App Name:");
        descriptionLabelEditPopup.setSize(300, 30);

        final JLabel descriptionEditPopup = new JLabel();
        descriptionEditPopup.setSize(300, 30);

        JLabel usernameLabelEditPopup = new JLabel("Username:");
        usernameLabelEditPopup.setSize(300, 30);

        final JTextField usernameEditPopup = new JTextField();
        usernameEditPopup.setSize(300, 30);

        JLabel passwordLabelEditPopup = new JLabel("Password:");
        passwordLabelEditPopup.setSize(300, 30);

        final JTextField passwordEditPopup = new JTextField();
        passwordEditPopup.setSize(300, 30);

        JButton saveButtonEditPopup = new JButton("Save");
        saveButtonEditPopup.setSize(300, 30);


        final JDialog editPopup = new JDialog();
        editPopup.setLayout(new GridLayout(7, 1));
        editPopup.setSize(300, 250);
        editPopup.add(descriptionLabelEditPopup);
        editPopup.add(descriptionEditPopup);
        editPopup.add(usernameLabelEditPopup);
        editPopup.add(usernameEditPopup);
        editPopup.add(passwordLabelEditPopup);
        editPopup.add(passwordEditPopup);
        editPopup.add(saveButtonEditPopup);

        saveButtonEditPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editLoginUsername = usernameEditPopup.getText();
                editLoginPassword = passwordEditPopup.getText();

                service.editLogin(editLoginUsername, editLoginPassword, searchedLogin, session);

                usernameDisplay.setText(editLoginUsername);
                passwordDisplay.setText(editLoginPassword);

                editPopup.setVisible(false);
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.setBounds((int) 42.5, 205, 150, 25);
        editButton.setFocusable(false);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchedLogin != null) {
                    editPopup.setLocationRelativeTo(UI.this);
                    editPopup.setVisible(true);
                    descriptionEditPopup.setText(descriptionFieldTextFindLogin);
                }
            }
        });

        JLabel areYouSureLabel = new JLabel("Are you sure you want to delete?");
        JButton confirmButton = new JButton("Confirm");


        final JDialog deleteCheck = new JDialog();
        deleteCheck.setLayout(new GridLayout(2, 1));
        deleteCheck.setLocationRelativeTo(UI.this);
        deleteCheck.setSize(200, 100);
        deleteCheck.add(areYouSureLabel);
        deleteCheck.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                service.deleteLogin(searchedLogin, session);
                deleteCheck.setVisible(false);
                descriptionDisplay.setText(null);
                usernameDisplay.setText(null);
                passwordDisplay.setText(null);
                descriptionField.setText("Website/App Name:");

                loginsData = service.getLoginsDescriptions(session);
                String[] newData = loginsData.toArray(new String[0]);
                loginsDisplay.setListData(newData);

            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds((int) 42.5, 245, 150, 25);
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchedLogin != null) {
                    deleteCheck.setLocationRelativeTo(UI.this);
                    deleteCheck.setVisible(true);
                }
            }
        });

        JPanel manipulateLoginPanel = new JPanel();
        manipulateLoginPanel.setBounds(10, 70, 235, 345);
        manipulateLoginPanel.setBackground(Color.white);
        manipulateLoginPanel.setBorder(new LineBorder(Color.gray, 2));
        manipulateLoginPanel.setLayout(null);
        manipulateLoginPanel.add(descriptionLabel);
        manipulateLoginPanel.add(descriptionDisplay);
        manipulateLoginPanel.add(usernameLabel);
        manipulateLoginPanel.add(usernameDisplay);
        manipulateLoginPanel.add(passwordLabel);
        manipulateLoginPanel.add(passwordDisplay);
        manipulateLoginPanel.add(editButton);
        manipulateLoginPanel.add(deleteButton);


        // Logins Title
        JLabel loginsDisplayTitle = new JLabel("Logins");
        loginsDisplayTitle.setBounds(10, 420, 465, 50);
        loginsDisplayTitle.setFont(new Font("Arial", Font.PLAIN, 25));

        // Logins Display

        String[] data = service.getLoginsDescriptions(session).toArray(new String[0]); // Convert ArrayList to String array
        loginsDisplay = new JList<>(data);
        loginsDisplay.setBounds(10, 470, 465, 230);
        loginsDisplay.setBorder(new LineBorder(Color.gray, 2));
        loginsDisplay.setFont(new Font("Arial", Font.PLAIN, 15));
        loginsDisplay.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                selectedLoginLoginDisplay = loginsDisplay.getSelectedValue();
                searchedLogin = service.findLogin(session, selectedLoginLoginDisplay);

                descriptionDisplay.setText(searchedLogin.getDescription());
                usernameDisplay.setText(searchedLogin.getUsername());
                passwordDisplay.setText(searchedLogin.getPassword());

                descriptionField.setText("Website/App Name");


            }
        });

        JScrollPane scrollPane = new JScrollPane(loginsDisplay); // Wrap JList inside a JScrollPane
        scrollPane.setBounds(10, 470, 465, 230);
        scrollPane.setBorder(new LineBorder(Color.gray, 2));

        this.add(manipulateLoginPanel);
        this.add(loginsDisplayTitle);
        this.add(scrollPane);
    }

    private void createFindLogin(final Service service, final Session session){
        // Find Login title
        final JLabel findLoginTitle = new JLabel("Find Login");
        findLoginTitle.setFont(new Font("Arial", Font.PLAIN, 25));
        findLoginTitle.setBounds(15, 15, 230, 50);
        findLoginTitle.setBackground(Color.CYAN);

        // Enter description field
        descriptionField = new JTextField("Website/App Name");
        descriptionField.setBounds(255, 70, 220, 30);
        descriptionField.setBorder(new LineBorder(Color.gray, 2));


        JLabel noLoginPopupMessage = new JLabel("No Login Found, Try Again!");

        final JDialog noLoginPopup = new JDialog();
        noLoginPopup.setSize(200, 100);
        noLoginPopup.add(noLoginPopupMessage);

        // Search Button
        final JButton searchButton = new JButton("Search");
        searchButton.setBounds(315, 110, 100, 25);
        searchButton.setFocusable(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descriptionFieldTextFindLogin = descriptionField.getText();
                searchedLogin = service.findLogin(session, descriptionFieldTextFindLogin);

                if (searchedLogin != null) {
                    searchedLogin.println();

                    descriptionDisplay.setText(searchedLogin.getDescription());
                    usernameDisplay.setText(searchedLogin.getUsername());
                    passwordDisplay.setText(searchedLogin.getPassword());

                    descriptionField.setText("Website/App Name");

                } else {
                    System.out.println("No login found");
                    noLoginPopup.setLocationRelativeTo(UI.this);
                    noLoginPopup.setVisible(true);

                }

            }
        });

        this.add(findLoginTitle);
        this.add(descriptionField);
        this.add(descriptionField);
        this.add(searchButton);
    }

    public void createCreateNewLogin(final Service service, final Session session, final Constants constants){
        // Create new login title
        JLabel createNewLoginTitle = new JLabel("Create New Login");
        createNewLoginTitle.setBounds(255, 180, 230, 50);
        createNewLoginTitle.setFont(new Font("Arial", Font.PLAIN, 25));

        // description field in create new login
        final JTextField descriptionFieldCreateNewLogin = new JTextField("Website/App Name");
        descriptionFieldCreateNewLogin.setBounds(255, 245, 220, 30);
        descriptionFieldCreateNewLogin.setBorder(new LineBorder(Color.gray, 2));

        // username field in create new login
        final JTextField usernameField = new JTextField("Username");
        usernameField.setBounds(255, 280, 220, 30);
        usernameField.setBorder(new LineBorder(Color.gray, 2));

        // password field in create new login
        final JTextField passwordField = new JTextField("Password");
        passwordField.setBounds(255, 315, 220, 30);
        passwordField.setBorder(new LineBorder(Color.gray, 2));

        // generate password checkbox
        JCheckBox generatePassword = new JCheckBox("Generate password?");
        generatePassword.setBounds(255, 350, 220, 30);
        generatePassword.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Open a popup when checkBox is checked
                    showPopupInMiddle(service, constants, passwordField);
                }
            }
        });

        JLabel alreadyExistsPopupMessage = new JLabel("Login Already Exists, Try Again!");

        final JDialog alreadyExistsPopup = new JDialog();
        alreadyExistsPopup.setSize(200, 100);
        alreadyExistsPopup.add(alreadyExistsPopupMessage);


        //Save and exit button
        JButton saveFinalLoginButton = new JButton("Save Login");
        saveFinalLoginButton.setBounds(315, 390, 100, 25);
        saveFinalLoginButton.setFocusable(false);
        saveFinalLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descriptionFieldTextCreateNewLogin = descriptionFieldCreateNewLogin.getText();
                usernameFieldText = usernameField.getText();
                passwordFieldText = passwordField.getText();
                Credential newLogin = service.createLoginObject(descriptionFieldTextCreateNewLogin, usernameFieldText, passwordFieldText, session);
                boolean alreadyExists = service.alreadyExistsCheck(newLogin, session);

                if (!alreadyExists){
                    service.saveCredential(session, newLogin);
                    descriptionFieldCreateNewLogin.setText("Website/App Name");
                    usernameField.setText("Username");
                    passwordField.setText("Password");

                } else {
                    alreadyExistsPopup.setLocationRelativeTo(UI.this);
                    alreadyExistsPopup.setVisible(true);
                }

                loginsData = service.getLoginsDescriptions(session);
                String[] newData = loginsData.toArray(new String[0]);
                loginsDisplay.setListData(newData);

            }
        });

        this.add(createNewLoginTitle);
        this.add(descriptionFieldCreateNewLogin);
        this.add(usernameField);
        this.add(passwordField);
        this.add(generatePassword);
        this.add(saveFinalLoginButton);
    }

    private void showPopupInMiddle(final Service service, final Constants constants, final JTextField passwordField) {

            // Password length spinner title
            JLabel passwordLengthSpinnerTitle = new JLabel("Set password length");

            //password length spinner
            final JSpinner passwordLengthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));


            // Number of Special Characters spinner title
            JLabel numSpecialCharactersTitle = new JLabel("Set number of special characters");

            // number of special characters spinner
            final JSpinner numSpecialCharactersSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 19, 1));

            final JLabel generatedPassword = new JLabel();

            // password length error popup
            JLabel passwordLengthError = new JLabel("Number of special characters must be less then password length");
            final JDialog passwordLengthErrorPopup = new JDialog();
            passwordLengthErrorPopup.setSize(400, 100);
            passwordLengthErrorPopup.setLocationRelativeTo(UI.this);
            passwordLengthErrorPopup.add(passwordLengthError);


            // generate button
            JButton generateButton = new JButton("Generate");
            generateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    passwordLengthFieldValue = (int) passwordLengthSpinner.getValue();
                    numSpecialCharactersFieldValue = (int) numSpecialCharactersSpinner.getValue();

                    if (passwordLengthFieldValue < numSpecialCharactersFieldValue){
                        passwordLengthErrorPopup.setVisible(true);
                    } else {
                        randomPassword = service.createRandomPassword(constants.SPECIAL_CHARACTERS, constants.REGULAR_LETTERS, constants.NUMBERS, passwordLengthFieldValue, numSpecialCharactersFieldValue);
                        generatedPassword.setText(randomPassword);
                        passwordField.setText(randomPassword);
                    }
                }
            });


            // Create a dialog for the popup
            final JDialog popupDialog = new JDialog(this, "Generate Password");
            popupDialog.setLocationRelativeTo(this);
            popupDialog.setSize(300, 200);
            popupDialog.setResizable(false);
            popupDialog.setLayout(new GridLayout(7, 1));
            popupDialog.add(passwordLengthSpinnerTitle);
            popupDialog.add(passwordLengthSpinner);
            popupDialog.add(numSpecialCharactersTitle);
            popupDialog.add(numSpecialCharactersSpinner);
            popupDialog.add(generateButton);
            popupDialog.add(generatedPassword);
            popupDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            popupDialog.setVisible(true);

            //Save button
            JButton savebutton = new JButton("Save");
            savebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    popupDialog.setVisible(false);
                }
            });
            popupDialog.add(savebutton);


    }

}
