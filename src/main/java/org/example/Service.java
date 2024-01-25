package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Random;

public class Service {



    public Session createHibernateConfiguration() {
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

    public Credential findLogin(Session session, String descriptionFieldTextFindLogin) {

        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery for Credential
        CriteriaQuery<Credential> criteriaQuery = builder.createQuery(Credential.class);
        Root<Credential> root = criteriaQuery.from(Credential.class);

        // Define the criteria
        criteriaQuery.select(root).where(builder.equal(root.get("description"), descriptionFieldTextFindLogin));

        // Execute the query
        Credential searchedLogin = session.createQuery(criteriaQuery).uniqueResult();

        return searchedLogin;

    }

    public Credential createLoginObject(String descriptionFieldTextCreateNewLogin, String usernameFieldText, String passwordFieldText, Session session) {
        Credential newCredential = new Credential();

        newCredential.setDescription(descriptionFieldTextCreateNewLogin);
        newCredential.setUsername(usernameFieldText);
        newCredential.setPassword(passwordFieldText);

        newCredential.println();

        return newCredential;
    }

    public boolean alreadyExistsCheck(Credential newCredential, Session session) {
        String descriptionOfOriginal = newCredential.getDescription();
        Credential searchedTarget = findLogin(session, descriptionOfOriginal);

        // Check if searchedTarget is null
        if (searchedTarget != null) {
            String descriptionOfNewLogin = searchedTarget.getDescription();
            if (descriptionOfOriginal.equals(descriptionOfNewLogin)) {
                System.out.println("\n Error! Login already exists");
                return true;
            } else {
                // return false if the two descriptions arnt equal
                return false;
            }
        } else {
            // Handle the case where findLogin returned null
            return false;
        }

    }

    public void saveCredential(Session session, Credential newCredential) {
        //save to MySQL operations
        session.beginTransaction(); // begin access of DB
        session.merge(newCredential);
        session.getTransaction().commit(); // commit changes to DB

        System.out.println("\nSaving data...");
        System.out.println("Saved to database");

    }

    public String createRandomPassword(String[] SPECIAL_CHARACTERS, String[] REGULAR_LETTERS,
                                              String[] NUMBERS, int passwordLengthFieldValue, int numSpecialCharactersFieldValue) {

        Random random = new Random();
        ArrayList<String> passwordStringList = new ArrayList<>();

        for (int i = 0; i < passwordLengthFieldValue; i++) {
            switch (random.nextInt(2)) {
                case 0:
                    passwordStringList.add(REGULAR_LETTERS[random.nextInt(REGULAR_LETTERS.length)]);
                    break;

                case 1:
                    passwordStringList.add(NUMBERS[random.nextInt(NUMBERS.length)]);
                    break;
            }
        }

        passwordStringList = addSpecialCharacters(SPECIAL_CHARACTERS, numSpecialCharactersFieldValue, passwordStringList, random);

        String passwordString = concatenateStrings(passwordStringList);

        return passwordString;
    }

    private static ArrayList<String> addSpecialCharacters(String[] specialCharacters, int numOfSpecialCharacters,
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

    private static String concatenateStrings(ArrayList<String> stringList) {
        StringBuilder result = new StringBuilder();

        // Iterate through the ArrayList and concatenate strings
        for (String str : stringList) {
            result.append(str);
        }

        return result.toString();
    }

    public void editLogin(String username, String passwordString, Credential searchedLogin, Session session) {

        searchedLogin.setUsername(username);
        searchedLogin.setPassword(passwordString);

        session.beginTransaction(); // begin access of DB
        session.merge(searchedLogin);
        session.getTransaction().commit(); // commit changes to DB

    }

    public void deleteLogin(Credential searchedLogin, Session session) {
        session.beginTransaction();
        session.remove(searchedLogin);
        session.getTransaction().commit();

    }

    public ArrayList<String> getLoginsDescriptions(Session session) {

        // Create CriteriaQuery for the primary key
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
            Root<Credential> root = criteriaQuery.from(Credential.class); // Use reflection to get the entity class

            // "description" is the primary key column
            criteriaQuery.select(root.<String>get("description"));

            // Execute the query
            Query<String> query = session.createQuery(criteriaQuery);
            ArrayList<String> results = (ArrayList<String>) query.getResultList();

            ArrayList<String> loginsDescriptions = new ArrayList<>(results);


        return loginsDescriptions;
    }


}