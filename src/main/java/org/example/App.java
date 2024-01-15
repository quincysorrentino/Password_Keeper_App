package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class App {
    public static void main(String[] args) {

        //call function to configure hibernate
        Session session = createHibernateConfiguration();

        Scanner scnr = new Scanner(System.in);

        String passwordString;

        final String[] SPECIAL_CHARACTERS = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "<", ">"};

        final String[] REGULAR_LETTERS = {"a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "g", "G", "h", "H", "i",
                "I", "j", "J", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O", "p", "P", "q", "Q",
                "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y", "z",
                "Z"};

        final String[] NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};


        System.out.println("\n\nEnter your selection (either \"1\" or \"2\")\n");
        System.out.println("----------------------------------");
        System.out.println("(1) Find an existing password");
        System.out.println("(2) Generate a new login");
        System.out.println("----------------------------------");
        System.out.println("Enter: ");

        int choice;

        choice = choicePicker(scnr);

        switch (choice) {
            case 1: {
                System.out.println("Case 1");
                System.out.println("Not working yet");
                break;
            }

            case 2: {
                System.out.println("Do you want to enter a password manually (1) or generate your own password(2)");
                System.out.print("Enter: ");

                choice = choicePicker(scnr);

                switch (choice) {
                    case 1: {
                        System.out.println("Enter website/app name: ");
                        String description = scnr.next();

                        System.out.println("Enter username: ");
                        String username = scnr.next();

                        System.out.println("Enter password: ");
                        passwordString = scnr.next();

                        Credential credential = createLoginObject(description, username, passwordString);

                        saveCredential(session, credential);
                        break;
                    }

                    case 2: {

                        System.out.println("Enter website/app name: ");
                        String description = scnr.next();

                        System.out.println("Enter username: ");
                        String username = scnr.next();

                        int passwordLength = passwordLengthQuestion(scnr);

                        int numSpecialCharacters = numbOfSpecialCharactersQuestion(scnr);

                        passwordString = createRandomPassword(SPECIAL_CHARACTERS, REGULAR_LETTERS, NUMBERS, passwordLength, numSpecialCharacters);

                        Credential credential = createLoginObject(description, username, passwordString);


                        while (true) {
                            System.out.println("\nDo you want to keep the current password (1) or generate a new one (2)");
                            choice = choicePicker(scnr);

                            if (choice == 1) {
                                System.out.println("Saving data...");
                                System.out.println("Saved to database");

                                saveCredential(session, credential);
                                break;

                            } else { // if choice == 2
                                passwordString = createRandomPassword(SPECIAL_CHARACTERS, REGULAR_LETTERS, NUMBERS, passwordLength, numSpecialCharacters);

                                credential = createLoginObject(description, username, passwordString);

                                //saveCredential(session, credential);
                            }
                        }
                        break;

                    }
                }
            }

        }

    }


    public static String createRandomPassword(String[] SPECIAL_CHARACTERS, String[] REGULAR_LETTERS,
                                              String[] NUMBERS, int passwordLength, int numOfSpecialCharacters) {

        Random random = new Random();
        ArrayList<String> passwordStringList = new ArrayList<>();

        for (int i = 0; i < passwordLength; i++) {
            switch (random.nextInt(2)) {
                case 0:
                    passwordStringList.add(REGULAR_LETTERS[random.nextInt(REGULAR_LETTERS.length)]);
                    break;

                case 1:
                    passwordStringList.add(NUMBERS[random.nextInt(NUMBERS.length)]);
                    break;
            }
        }

        passwordStringList = addSpecialCharacters(SPECIAL_CHARACTERS, numOfSpecialCharacters, passwordStringList, random);

        String passwordString = concatenateStrings(passwordStringList);

        return passwordString;
    }

    public static ArrayList<String> addSpecialCharacters(String[] specialCharacters, int numOfSpecialCharacters,
                                                         ArrayList<String> passwordStringList, Random random) {

        int indexToChange;
        boolean isSpecialCharacter = false;
        int specialCharacterCount = 0;

        while (specialCharacterCount < numOfSpecialCharacters) {
            indexToChange = random.nextInt(passwordStringList.size());
            for (String specialCharacter : specialCharacters) {
                if (passwordStringList.get(indexToChange).equals(specialCharacter)) {
                    isSpecialCharacter = true;
                    break;
                }

            }

            if (!isSpecialCharacter) {
                passwordStringList.set(indexToChange, specialCharacters[random.nextInt(specialCharacters.length)]);
                specialCharacterCount++;
            }

            isSpecialCharacter = false;
        }

        return passwordStringList;
    }

    public static void saveCredential(Session session, Credential credential) {

        //save to MySQL operations
        session.beginTransaction(); // begin access of DB
        session.save(credential);
        session.getTransaction().commit(); // commit changes to DB

        System.out.println("\nSaving data...");
        System.out.println("Saved to database");

    }

    public static String concatenateStrings(ArrayList<String> stringList) {
        StringBuilder result = new StringBuilder();

        // Iterate through the ArrayList and concatenate strings
        for (String str : stringList) {
            result.append(str);
        }

        return result.toString();
    }

    public static int passwordLengthQuestion(Scanner scnr) {

        System.out.println("Enter the length of your password: ");
        int passwordLength;

        while (true) {
            try {
                passwordLength = scnr.nextInt();
                if (passwordLength > 0) {
                    return passwordLength;

                } else {
                    System.out.println("Password length must be greater than 0: ");
                    scnr.nextLine();

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid password length. Please enter a non negative integer: ");
                scnr.nextLine();
            }
        }
    }

    public static int numbOfSpecialCharactersQuestion(Scanner scnr) {

        System.out.println("Enter the number of special characters:");
        int numOfSpecialCharacters = -1;
        while (scnr.hasNext()) {
            try {
                int inputLength = scnr.nextInt();
                if (inputLength >= 0) {
                    numOfSpecialCharacters = inputLength;
                    return numOfSpecialCharacters;

                } else {
                    System.out.println("Invalid number of special characters. Please enter a non negative integer: ");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid number of special characters. Please enter a non negative integer: ");
                scnr.nextLine();
            }
        }

        return 0;
    }

    public static Session createHibernateConfiguration() {
        //create configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        //add the name of the class
        configuration.addAnnotatedClass(Credential.class);

        //create session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //initialize the session object
        Session session = sessionFactory.openSession();

        return session;
    }

    public static Credential createLoginObject(String description, String username, String passwordString) {
        Credential credential = new Credential();

        credential.setDescription(description);
        credential.setUsername(username);
        credential.setPassword(passwordString);

        credential.println();

        return credential;
    }

    public static int choicePicker(Scanner scnr) {
        int choice;
        while (true) {
            try {
                choice = scnr.nextInt();

                // Validate the input
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Error! Please input either \"1\" or \"2\"");
                    System.out.print("Enter: ");
                }
            } catch (InputMismatchException e) {
                scnr.nextLine();
                System.out.println("Error! Please input either \"1\" or \"2\"");
                System.out.print("Enter: ");
            }
        }
        return choice;
    }


} //public class main


