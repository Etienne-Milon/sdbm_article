package fr.fs.sdbm.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SDBMConnect2 {
    // Declare the JDBC objects.
    private static Connection connexion;

    private SDBMConnect2() {

    }

    public static Connection getInstance() {
        if (connexion == null) {
            try {
                String dbURL = "jdbc:sqlserver://127.0.0.1:1402;databaseName=SDBM;encrypt=false";
                String user = "sa";
                String pass = "azerty@123456";
                connexion = DriverManager.getConnection(dbURL, user, pass);
            }

            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connexion;
    }
}
