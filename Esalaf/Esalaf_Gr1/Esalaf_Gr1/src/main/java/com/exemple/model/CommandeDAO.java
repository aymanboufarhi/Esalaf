package com.exemple.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande> {
    public CommandeDAO() throws SQLException {
        super();
    }

    // mapping objet --> relation
    @Override
    public void save(Commande object) throws SQLException {

        String req = "insert into commande (cin , produit, quantite, etat) values (? , ? , ? , ?) ;";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1 , object.getCin());
        this.preparedStatement.setString(2 , object.getProduit());
        this.preparedStatement.setString(3 , object.getQuantite());
        this.preparedStatement.setString(4 , object.getEtat());
        this.preparedStatement.execute();

    }

    @Override
    public void update(Commande object) throws SQLException {
        String req = "UPDATE commande SET cin = ? , produit = ? , quantite = ? , etat = ? WHERE id = ?;";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1 , object.getCin());
        this.preparedStatement.setString(2 , object.getProduit());
        this.preparedStatement.setString(3 , object.getQuantite());
        this.preparedStatement.setString(4 , object.getEtat());
        this.preparedStatement.setString(5, String.valueOf(object.getId()));
        this.preparedStatement.execute();
    }

    @Override
    public void delete(Commande object) throws SQLException {
        String req = "DELETE FROM commande WHERE id = ? OR (cin = ? AND produit = ? AND quantite = ? AND etat =?);";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1, String.valueOf(object.getId()));
        this.preparedStatement.setString(2, object.getCin());
        this.preparedStatement.setString(3, object.getProduit());
        this.preparedStatement.setString(4, object.getQuantite());
        this.preparedStatement.setString(5, object.getEtat());
        this.preparedStatement.execute();
    }

    @Override
    public Commande getOne(Long id) throws SQLException {
        return null;
    }


    // mapping relation --> objet
    @Override
    public List<Commande> getAll() throws SQLException{

        List<Commande> mylist = new ArrayList<Commande>();
        String req = " select * from commande" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add( new Commande(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getString(3) , this.resultSet.getString(4) , this.resultSet.getString(5)));
        }

        return mylist;
    }
}
