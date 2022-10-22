package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contact;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    // Class responsible for making the DAO Connection

    // DAO = Data Access Object
    /*
    * CRUD
    * c: CREATE - INSERT - Done!
    * r: READ - SELECT - Done!
    * u: UPDATE - UPDATE - Done!
    * d: DELETE - DELETE - Done!
    */

    public void save(Contact contact) {
        //query sql
        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        /*
        * AUTO-DETECTS "jdbc" BECAUSE "com.mysql.jdbc.Driver"
        * WAS PRE-LOADED AT ConnectionFactory BY THE JVM IN ORDER TO MAKE JVM UNDERSTAND THAT
        * WE ARE WORKING WITH MySQL
        */
        PreparedStatement pstm = null;

        try {
            // Create connection with the database
            conn = ConnectionFactory.createConnectionToMySQL();
            // creates a "parse" preparedstatement using the String "sql" create before
            // executes a query
            pstm = conn.prepareStatement(sql);
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

    public void update(Contact contact){

        String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? " +
                "WHERE id = ?";

        // preparing connections
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // Create connection with the database
            conn = ConnectionFactory.createConnectionToMySQL();
            // Create the class to execute the query
            pstm = conn.prepareStatement(sql);
            // add the values to update
            pstm.setString(1,contact.getNome());
            pstm.setInt(2, contact.getIdade());
            pstm.setDate(3, new Date(contact.getDataCadastro().getTime()));
            // select the id to update "WHERE id"
            pstm.setInt(4, contact.getId());
            // execute the query
            pstm.execute();
            System.out.println("Contact Updated Successfully! ");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void deleteById(int id){

        String sql = "DELETE FROM contatos WHERE id = ?";
        // preparing connections
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            // id from method's parameter
            pstm.setInt(1, id);
            pstm.execute();
            System.out.println("Deleted Successfully!");
            // https://github.com/itstoony
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (conn != null){
                    conn.close();
                }
                if (pstm != null){
                    pstm.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public List<Contact> getContacts(){

        String sql = "SELECT * FROM contatos";
        // instance a array object with contact type
        List<Contact> contacts = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ptsm = null;
        // Class that will recover data from database *** SELECT ***
        ResultSet rset = null;

        try {
            // uses the ConnectionFactory's method to connect with the database
            conn = ConnectionFactory.createConnectionToMySQL();

            ptsm = conn.prepareStatement(sql);

            rset = ptsm.executeQuery();

            while(rset.next()){
                // while there's new data in the ResultSet
                Contact contact = new Contact();

                // get id from database
                contact.setId(rset.getInt("id"));
                // get name
                contact.setNome(rset.getString("nome"));
                // get age
                contact.setIdade(rset.getInt("idade"));
                // get registration date
                contact.setDataCadastro(rset.getDate("dataCadastro"));

                contacts.add(contact);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally{
            try {
                // close connections if they're not null
                if(rset != null){
                    rset.close();
                }
                if(ptsm != null){
                    ptsm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return contacts;
    }


}
