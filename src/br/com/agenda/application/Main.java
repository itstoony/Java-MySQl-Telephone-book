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
        contato2.setNome("Luiz Carlos");
        contato2.setIdade(35);
        contato2.setDataCadastro(new Date());
        // ContactDAO expects a contact as parameter
        contadoDAO.save(contato2);



    }
}
