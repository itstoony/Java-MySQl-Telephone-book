package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    // mysql username
    private static final String USERNAME = "root";

    // password
    private static final String PASSWORD = "Tony.256895";

    // database path, port, database's name
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    /*
     * Database connection
     */

    public static Connection createConnectionToMySQL() throws Exception {
        // makes the class be loaded by the JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        // creates connection with the database
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception {

        // recover database connection
        Connection con = createConnectionToMySQL();

        // test if connection is null
        if(con != null){
            System.out.println("Connection accomplished successfully!");
            con.close();
        }

    }
}
