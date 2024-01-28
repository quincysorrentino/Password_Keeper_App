# Password Keeper App

The Password Keeper App is a Java-based application designed to simplify password management. The goal is to provide a user-friendly interface without the inconvenience of multiple login steps.

## Technologies Used

- Java 17
- Maven
- MySQL
- Hibernate
- JavaSwing

## Setup and Running

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/quincysorrentino/Password_Keeper_App.git
   cd Password_Keeper_App `

1.  Database Setup:

    -   Install MySQL on your local machine.
    -   Create a local database for the app

        sqlCopy code

        `CREATE DATABASE password_keeper_db;
        USE password_keeper_db;`

2.  Create the Credential Table:

    -   Execute the following SQL query to create the "credential" table:
        The table should have three columns: "description," "username," and "password."

        sqlCopy code

        `CREATE TABLE credential (
            description VARCHAR(255),
            username VARCHAR(255),
            password VARCHAR(255)
        );`

   

3.  Update Hibernate Configuration:

    -   Open the `hibernate.cfg.xml` file in the `src/main/resources` directory.

    -   Update the following properties with your database connection details:

        xmlCopy code

        `<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/password_keeper_db</property>
        <property name="hibernate.connection.username">your_username</property>
        <property name="hibernate.connection.password">your_password</property>`

    -   Ensure that the JDBC URL, username, and password match your local MySQL setup.

4.  Run the Application:

    -   After setting up the database and updating the Hibernate configuration, you can run the application using the provided Maven commands in the previous section.



Future Plans
------------

The project is currently configured to run locally, but future enhancements may include:

-   Hosting the database on Amazon RDS for multi-computer access.
-   Adding user authentication for enhanced security and multi-user functionality.

Demo Video
----------
[For a detailed walkthrough of the app's functionalities, watch the demo video.](https://youtu.be/5RMIfF60MSU)

Dependencies
------------

-   Java (version 17)
-   mysql-connector-java (version 8.0.33)
-   hibernate-core (version 6.4.1.Final)

Feel free to reach out for any questions or issues!
