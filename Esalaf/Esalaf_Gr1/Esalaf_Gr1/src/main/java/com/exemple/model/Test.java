package com.exemple.model;

import java.sql.SQLException;
import java.util.List;
public class Test {

    public static void main(String[] args) {


        try {
                // entity
                Client cli  = new Client(0l,"Ayman","0683907376","K573926");

                //Transacatio
                ClientDAO clidao = new ClientDAO();

                // save trasanction
                clidao.save(cli);


             List<Client> mylist =  clidao.getAll();

            for (Client temp :mylist) {

                System.out.println(temp.toString());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        try {
            // entity
            Produit pri  = new Produit(0l,"Fromage",31.46f,"20", "LAITIERS");

            //Transacatio
            ProduitDAO pridao = new ProduitDAO();

            // save trasanction
            pridao.save(pri);


            List<Produit> mylist =  pridao.getAll();

            for (Produit temp :mylist) {

                System.out.println(temp.toString());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // entity
            Commande com  = new Commande(0l,"K982727","Fromage","1", "Paye");

            //Transacatio
            CommandeDAO comDAO = new CommandeDAO();

            // save trasanction
            comDAO.save(com);


            List<Commande> mylist =  comDAO.getAll();

            for (Commande temp :mylist) {

                System.out.println(temp.toString());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }
