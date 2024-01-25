package org.example;


//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Root;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import java.util.*;

public class App {
    public static void main(String[] args) {

        new UI();
//
//        //call function to configure hibernate
//        Session session = createHibernateConfiguration();
//
//        Scanner scnr = new Scanner(System.in);
//
//        String passwordString;
//
//        final String[] SPECIAL_CHARACTERS = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "<", ">"};
//
//        final String[] REGULAR_LETTERS = {"a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "g", "G", "h", "H", "i",
//                "I", "j", "J", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O", "p", "P", "q", "Q",
//                "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y", "z",
//                "Z"};
//
//        final String[] NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
//
//
//        System.out.println("\n\nEnter your selection (either \"1\" or \"2\")\n");
//        System.out.println("----------------------------------");
//        System.out.println("(1) Find an existing password");
//        System.out.println("(2) Generate a new login");
//        System.out.println("----------------------------------");
//        System.out.println("Enter: ");
//
//        int choice;
//
//        choice = choicePicker2(scnr);
//
//        switch (choice) {
//            case 1: {
//
//                System.out.println("Enter website/app name: ");
//                String searchTarget = scnr.next();
//                Credential searchedLogin = findLogin(session, searchTarget);
//
//                if (searchedLogin != null){
//                    searchedLogin.println();
//                    System.out.println("\nEnter your selection (either \"1\" or \"2\" or \"3\")");
//                    System.out.println("(1) Edit");
//                    System.out.println("(2) Delete Login");
//                    System.out.println("(3) Exit");
//                    System.out.println("Enter: ");
//                    choice = choicePicker3(scnr);
//
//
//                    switch (choice) {
//                        case 1: {
//                            // Edit functionality
//                            System.out.println("Enter new username :");
//                            String username = scnr.next();
//                            System.out.println("Enter new password: ");
//                            passwordString = scnr.next();
//
//                            editLogin(username, passwordString, searchedLogin, session);
//
//                            break;
//                        }
//
//                        case 2: {
//                            // Delete functionality
//                            System.out.println("Are you sure you want to delete?");
//                            System.out.println("(1) Delete");
//                            System.out.println("(2) Cancel");
//                            System.out.println("Enter: ");
//                            choice = choicePicker2(scnr);
//
//                            if (choice == 1) {
//                                System.out.println("Deleting login...");
//                                deleteLogin(searchedLogin, session);
//
//                            } else {
//                                System.out.println("Canceling...");
//                            }
//
//                            break;
//                        }
//
//                        case 3: {
//                            //Exit functionality
//                            System.out.println("Exiting...");
//
//                            break;
//                        }
//                    }
//
//
//                } else {
//                    System.out.println("Error! no login found");
//                }
//
//                break;
//            }
//
//            case 2: {
//                System.out.println("Do you want to enter a password manually (1) or generate your own password(2)");
//                System.out.print("Enter: ");
//
//                choice = choicePicker2(scnr);
//
//                switch (choice) {
//                    case 1: {
//                        System.out.println("Enter website/app name: ");
//                        String description = scnr.next();
//
//                        System.out.println("Enter username: ");
//                        String username = scnr.next();
//
//                        System.out.println("Enter password: ");
//                        passwordString = scnr.next();
//
//                        Credential credential = createLoginObject(description, username, passwordString);
//
//                        boolean alreadyExists = alreadyExistsCheck(credential, session);
//
//                        if (!alreadyExists) {
//                            saveCredential(session, credential);
//                        } else {
//                            break;
//                        }
//
//
//                        break;
//                    }
//
//                    case 2: {
//
//                        System.out.println("Enter website/app name: ");
//                        String description = scnr.next();
//
//                        System.out.println("Enter username: ");
//                        String username = scnr.next();
//
//                        int passwordLength = passwordLengthQuestion(scnr);
//
//                        int numSpecialCharacters = numbOfSpecialCharactersQuestion(scnr);
//
//                        passwordString = createRandomPassword(SPECIAL_CHARACTERS, REGULAR_LETTERS, NUMBERS, passwordLength, numSpecialCharacters);
//
//                        Credential credential = createLoginObject(description, username, passwordString);
//
//
//                        while (true) {
//                            System.out.println("\nDo you want to keep the current password (1) or generate a new one (2)");
//                            choice = choicePicker2(scnr);
//
//                            if (choice == 1) {
//
//                                saveCredential(session, credential);
//                                break;
//
//                            } else { // if choice == 2
//                                passwordString = createRandomPassword(SPECIAL_CHARACTERS, REGULAR_LETTERS, NUMBERS, passwordLength, numSpecialCharacters);
//
//                                credential = createLoginObject(description, username, passwordString);
//
//                                //saveCredential(session, credential);
//                            }
//                        }
//                        break;
//
//                    }
//                }
//            }
//
//        }
//
//    }
//
//
//    public static String createRandomPassword(String[] SPECIAL_CHARACTERS, String[] REGULAR_LETTERS,
//                                              String[] NUMBERS, int passwordLength, int numOfSpecialCharacters) {
//
//        Random random = new Random();
//        ArrayList<String> passwordStringList = new ArrayList<>();
//
//        for (int i = 0; i < passwordLength; i++) {
//            switch (random.nextInt(2)) {
//                case 0:
//                    passwordStringList.add(REGULAR_LETTERS[random.nextInt(REGULAR_LETTERS.length)]);
//                    break;
//
//                case 1:
//                    passwordStringList.add(NUMBERS[random.nextInt(NUMBERS.length)]);
//                    break;
//            }
//        }
//
//        passwordStringList = addSpecialCharacters(SPECIAL_CHARACTERS, numOfSpecialCharacters, passwordStringList, random);
//
//        String passwordString = concatenateStrings(passwordStringList);
//
//        return passwordString;
//    }
//
//    public static ArrayList<String> addSpecialCharacters(String[] specialCharacters, int numOfSpecialCharacters,
//                                                         ArrayList<String> passwordStringList, Random random) {
//
//        int indexToChange;
//        boolean isSpecialCharacter = false;
//        int specialCharacterCount = 0;
//
//        while (specialCharacterCount < numOfSpecialCharacters) {
//            indexToChange = random.nextInt(passwordStringList.size());
//            for (String specialCharacter : specialCharacters) {
//                if (passwordStringList.get(indexToChange).equals(specialCharacter)) {
//                    isSpecialCharacter = true;
//                    break;
//                }
//
//            }
//
//            if (!isSpecialCharacter) {
//                passwordStringList.set(indexToChange, specialCharacters[random.nextInt(specialCharacters.length)]);
//                specialCharacterCount++;
//            }
//
//            isSpecialCharacter = false;
//        }
//
//        return passwordStringList;
//    }
//
//    public static void saveCredential(Session session, Credential credential) {
//
//        //save to MySQL operations
//        session.beginTransaction(); // begin access of DB
//        session.merge(credential);
//        session.getTransaction().commit(); // commit changes to DB
//
//        System.out.println("\nSaving data...");
//        System.out.println("Saved to database");
//
//    }
//
//    public static String concatenateStrings(ArrayList<String> stringList) {
//        StringBuilder result = new StringBuilder();
//
//        // Iterate through the ArrayList and concatenate strings
//        for (String str : stringList) {
//            result.append(str);
//        }
//
//        return result.toString();
//    }
//
//    public static int passwordLengthQuestion(Scanner scnr) {
//
//        System.out.println("Enter the length of your password: ");
//        int passwordLength;
//
//        while (true) {
//            try {
//                passwordLength = scnr.nextInt();
//                if (passwordLength > 0) {
//                    return passwordLength;
//
//                } else {
//                    System.out.println("Password length must be greater than 0: ");
//                    scnr.nextLine();
//
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid password length. Please enter a non negative integer: ");
//                scnr.nextLine();
//            }
//        }
//    }
//
//    public static int numbOfSpecialCharactersQuestion(Scanner scnr) {
//
//        System.out.println("Enter the number of special characters:");
//        int numOfSpecialCharacters = -1;
//        while (scnr.hasNext()) {
//            try {
//                int inputLength = scnr.nextInt();
//                if (inputLength >= 0) {
//                    numOfSpecialCharacters = inputLength;
//                    return numOfSpecialCharacters;
//
//                } else {
//                    System.out.println("Invalid number of special characters. Please enter a non negative integer: ");
//                }
//            } catch (NoSuchElementException e) {
//                System.out.println("Invalid number of special characters. Please enter a non negative integer: ");
//                scnr.nextLine();
//            }
//        }
//
//        return 0;
//    }
//
//    public static Session createHibernateConfiguration() {
//        //create configuration
//        Configuration configuration = new Configuration();
//        configuration.configure("hibernate.cfg.xml");
//        //add the name of the class
//        configuration.addAnnotatedClass(Credential.class);
//
//        //create session factory
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//
//        //initialize the session object
//        Session session = sessionFactory.openSession();
//
//        return session;
//    }
//
//    public static Credential createLoginObject(String description, String username, String passwordString) {
//        Credential credential = new Credential();
//
//        credential.setDescription(description);
//        credential.setUsername(username);
//        credential.setPassword(passwordString);
//
//        credential.println();
//
//        return credential;
//    }
//
//    public static int choicePicker2(Scanner scnr) {
//        int choice;
//        while (true) {
//            try {
//                choice = scnr.nextInt();
//
//                // Validate the input
//                if (choice == 1 || choice == 2) {
//                    break;
//                } else {
//                    System.out.println("Error! Please input either \"1\" or \"2\"");
//                    System.out.print("Enter: ");
//                }
//            } catch (InputMismatchException e) {
//                scnr.nextLine();
//                System.out.println("Error! Please input either \"1\" or \"2\"");
//                System.out.print("Enter: ");
//            }
//        }
//        return choice;
//    }
//
//    public static int choicePicker3(Scanner scnr) {
//        int choice;
//        while (true) {
//            try {
//                choice = scnr.nextInt();
//
//                // Validate the input
//                if (choice == 1 || choice == 2 || choice == 3) {
//                    break;
//                } else {
//                    System.out.println("Error! Please input either \"1\" or \"2\"");
//                    System.out.print("Enter: ");
//                }
//            } catch (InputMismatchException e) {
//                scnr.nextLine();
//                System.out.println("Error! Please input either \"1\" or \"2\"");
//                System.out.print("Enter: ");
//            }
//        }
//        return choice;
//    }
//
//    public static Credential findLogin(Session session, String searchTarget){
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//
//        // Create CriteriaQuery for Credential
//        CriteriaQuery<Credential> criteriaQuery = builder.createQuery(Credential.class);
//        Root<Credential> root = criteriaQuery.from(Credential.class);
//
//        // Define the criteria
//        criteriaQuery.select(root).where(builder.equal(root.get("description"), searchTarget));
//
//        // Execute the query
//        Credential searchedLogin = session.createQuery(criteriaQuery).uniqueResult();
//
//        return searchedLogin;
//
//    }
//
//    public static void editLogin(String username, String passwordString, Credential searchedLogin, Session session){
//
//        searchedLogin.setUsername(username);
//        searchedLogin.setPassword(passwordString);
//
//        session.beginTransaction(); // begin access of DB
//        session.merge(searchedLogin);
//        session.getTransaction().commit(); // commit changes to DB
//
//    }
//
//    public static void deleteLogin(Credential searchedLogin, Session session){
//        session.beginTransaction();
//        session.remove(searchedLogin);
//        session.getTransaction().commit();
//
//    }
//
//    public static boolean alreadyExistsCheck(Credential credential, Session session){
//        String descriptionOfOriginal = credential.getDescription();
//        Credential searchedTarget =  findLogin(session, descriptionOfOriginal);
//
//        // Check if searchedTarget is null
//        if (searchedTarget != null) {
//            String descriptionOfNewLogin = searchedTarget.getDescription();
//            if (descriptionOfOriginal.equals(descriptionOfNewLogin)){
//                System.out.println("\n Error! Login already exists");
//                return true;
//            } else {
//                // return false if the two descriptions arnt equal
//                return false;
//            }
//        } else {
//            // Handle the case where findLogin returned null
//            return false;
//        }

    }


} //public class main


