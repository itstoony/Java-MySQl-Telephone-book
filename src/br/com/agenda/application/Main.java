package br.com.agenda.application;

import br.com.agenda.dao.ContactDAO;
import br.com.agenda.model.Contact;

import java.util.Date;

public class Main {
    public static void main(String[] args) {


        /**
         * create an instance of ContactDAO in order to use its methods
         */

        ContactDAO contadoDAO = new ContactDAO();

        /**
         * instance of contact
         */

        Contact contato = new Contact();
        contato.setNome("Chloe Decker");
        contato.setIdade(35);
        contato.setDataCadastro(new Date());
        contadoDAO.save(contato);

        /**
         * Update contact
         */

        Contact c1 = new Contact();
        c1.setNome("Luiz Otário Mula da Silva");
        c1.setIdade(71);
        c1.setDataCadastro(new Date());
        c1.setId(2); // auto-increment id from database
        contadoDAO.update(c1);

        /**
         * check all data from database
         */

        for (Contact c : contadoDAO.getContacts()) {
            System.out.println("Contact: " + c.getNome());
        }

        /**
         * deleting contact from database by id
         */

        contadoDAO.deleteById(5);
        for (Contact c : contadoDAO.getContacts()) {
            System.out.println("Contact: " + c.getNome());
        }
    }
}
