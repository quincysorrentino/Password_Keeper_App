package org.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        String passwordString = null;

        final String[] SPECIAL_CHARACTERS = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "<", ">"};

        final String[] REGULAR_LETTERS = {"a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "g", "G", "h", "H", "i",
                "I", "j", "J", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O", "p", "P", "q", "Q",
                "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y", "z",
                "Z"};

        final String[] NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};



        System.out.println("\n\nRandom Password Maker");
        System.out.println("Do you want to enter an existing password (1) or create a new one (2)?");

        while (scnr.hasNext()) {
            try {
                int choice = scnr.nextInt();
                if (choice == 1) {
                    System.out.println("choice 1");
                    break;

                } else if (choice == 2) {
                    System.out.println("Enter the length of your password: ");
                    int passwordLength = -1;

                    while(scnr.hasNext()) {
                        try {
                            int passwordLengthInput = scnr.nextInt();
                            if(passwordLengthInput > 0) {
                                passwordLength = passwordLengthInput;
                                break;

                            } else {
                                System.out.println("Password length must be greater than 0: ");
                                scnr.nextLine();

                            }
                        } catch (NoSuchElementException e){
                            System.out.println("Invalid password length. Please enter a non negative integer: ");
                            scnr.nextLine();
                        }

                    }

                    System.out.println("Enter the number of special characters:");
                    int numOfSpecialCharacters = -1;
                    while (scnr.hasNext()){
                        try {
                            int inputLength = scnr.nextInt();
                            if (inputLength >= 0) {
                                numOfSpecialCharacters = inputLength;
                                break;

                            } else {
                                System.out.println("Invalid number of special characters. Please enter a non negative integer: ");
                            }
                        } catch (NoSuchElementException e){
                            System.out.println("Invalid number of special characters. Please enter a non negative integer: ");
                            scnr.nextLine();
                        }
                    }


                    passwordString = concatenateStrings(createRandomPassword(SPECIAL_CHARACTERS, REGULAR_LETTERS,
                                                        NUMBERS, passwordLength, numOfSpecialCharacters));


                    System.out.println("Generating Password...");
                    System.out.println("Your generated password is: " + passwordString);

                    while (choice != 1){
                        System.out.println("Keep password (1) or generate another (2): ");
                        choice = -1;

                        while (scnr.hasNext()) {
                            try {
                                int inputChoice = scnr.nextInt();
                                if (inputChoice == 1 || inputChoice == 2) {
                                    choice = inputChoice;
                                    break;

                                } else {
                                    System.out.println("Invalid choice. Please enter either \"1\" or \"2\": ");
                                }
                            } catch (NoSuchElementException e) {
                                System.out.println("Invalid choice. Please enter either \"1\" or \"2\": ");
                                scnr.nextLine();
                            }
                        }

                        if (choice == 1) {
                            System.out.println("Saving Credentials...");
                            break;
                        } else {


                            passwordString = concatenateStrings(createRandomPassword(SPECIAL_CHARACTERS, REGULAR_LETTERS,
                                    NUMBERS, passwordLength, numOfSpecialCharacters));


                            System.out.println("Generating Password...");
                            System.out.println("Your generated password is: " + passwordString);

                        }
                    }
                    break;

                } else {
                    System.out.println("Invalid input. Please enter \"1\" if you want to manually enter a password " +
                            "or \"2\" if you want to create a random password: ");
                    scnr.nextLine();
                }

            } catch (NoSuchElementException e) {
                scnr.nextLine();
                System.out.println("Invalid input. Please enter \"1\" if you want to manually enter a password " +
                        "or \"2\" if you want to create a random password: ");

            }
        }

        saveCredential(passwordString);

        scnr.close();

    }


    public static ArrayList<String> createRandomPassword(String[] SPECIAL_CHARACTERS, String[] REGULAR_LETTERS,
                                                         String[] NUMBERS, int passwordLength, int numOfSpecialCharacters) {

        Random random = new Random();
        ArrayList<String> passwordStringList = new ArrayList<String>();

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

        return passwordStringList;
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


    public static void saveCredential(String passwordString) {
        Credential credential = new Credential();
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter a website or app name: ");
        String description = scnr.next();

        System.out.println("Enter a username: ");
        String username = scnr.next();


        credential.setDescription(description);
        credential.setUsername(username);
        credential.setPassword(passwordString);

        credential.println();


        scnr.close();
    }

    public static String concatenateStrings(ArrayList<String> stringList) {
        StringBuilder result = new StringBuilder();

        // Iterate through the ArrayList and concatenate strings
        for (String str : stringList) {
            result.append(str);
        }

        return result.toString();
    }



} //public class main


