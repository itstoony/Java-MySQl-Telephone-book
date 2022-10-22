package br.com.agenda.application;

import br.com.agenda.dao.ContactDAO;
import br.com.agenda.model.Contact;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        /*
        * Class used only for tests
        */


        // create an instance of ContactDAO in order to use its methods
        ContactDAO contadoDAO = new ContactDAO();

        // instance of contact
        Contact contato2 = new Contact();
        contato2.setNome("Paulo Sérgio");
        contato2.setIdade(17);
        contato2.setDataCadastro(new Date());
        // ContactDAO expects a contact as parameter
        contadoDAO.save(contato2);

        // Update contact
        Contact c1 = new Contact();
        c1.setNome("Luiz Otário Mula da Silva");
        c1.setIdade(71);
        c1.setDataCadastro(new Date());
        c1.setId(2); // auto-increment id from database


        contadoDAO.update(c1);
        // check all data from database
        for(Contact c : contadoDAO.getContacts()){
            System.out.println("Contact: " + c.getNome());
        }



    }
}
