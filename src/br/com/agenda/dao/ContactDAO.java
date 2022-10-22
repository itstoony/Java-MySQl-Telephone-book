package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contact;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ContactDAO {
    // Class responsible for making the DAO Connection

    // DAO = Data Access Object
    /*
    * CRUD
    * c: CREATE - Done!
    * r: READ
    * u: UPDATE
    * d: DELETE
    */

    public void save(Contact contact) {
        //query sql
        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        /*
        * AUTO-DETECTS "jdbc" BECAUSE "com.mysql.jdbc.Driver"
        * WAS PRE-LOADED BY THE JVM IN ORDER TO MAKE JVM UNDERSTAND THAT
        * WE ARE WORKING WITH MySQL
        */
        PreparedStatement pstm = null;

        try {
            // Create connection with the database
            conn = ConnectionFactory.createConnectionToMySQL();
            // creates a "parse" preparedstatement using the String "sql" create before
            // executes a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            // add values to index's from "?,?,?" at the String "sql"
            pstm.setString(1, contact.getNome());
            pstm.setInt(2, contact.getIdade());
            pstm.setDate(3, new Date(contact.getDataCadastro().getTime()));
            // execute the query
            pstm.execute();
            System.out.println("Contact Saved Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("https://github.com/itstoony");
        } finally{
            // close connections
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }


    }

}
