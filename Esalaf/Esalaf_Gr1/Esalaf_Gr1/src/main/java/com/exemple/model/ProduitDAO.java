package com.exemple.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit> {
    public ProduitDAO() throws SQLException {

        super();
    }

    // mapping objet --> relation
    @Override
    public void save(Produit object) throws SQLException {

        String req = "insert into produit (nom , prix, quantite, categories) values (? , ? , ? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setFloat(2 , object.getPrix());
        this.preparedStatement.setString(3 , object.getQuantite());
        this.preparedStatement.setString(4 , object.getCategories());


        this.preparedStatement.execute();

    }

    @Override
    public void update(Produit object) throws SQLException {
        String req = "UPDATE produit SET nom = ? , prix = ? , quantite = ? , categories = ? WHERE id_produit = ?;";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setFloat(2 , object.getPrix());
        this.preparedStatement.setString(3 , object.getQuantite());
        this.preparedStatement.setString(4 , object.getCategories());
        this.preparedStatement.setString(5, String.valueOf(object.getIdProduit()));

        this.preparedStatement.execute();
    }

    @Override
    public void delete(Produit object) throws SQLException {
        String req = "DELETE FROM produit WHERE id_produit = ? OR (nom = ? AND prix = ? AND quantite = ? AND categories =?);";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1, String.valueOf(object.getIdProduit()));
        this.preparedStatement.setString(2, object.getNom());
        this.preparedStatement.setFloat(3, object.getPrix());
        this.preparedStatement.setString(4, object.getQuantite());
        this.preparedStatement.setString(5, object.getCategories());
        this.preparedStatement.execute();

    }

    @Override
    public Produit getOne(Long id) throws SQLException {
        return null;
    }


    // mapping relation --> objet
    @Override
    public List<Produit> getAll() throws SQLException{

        List<Produit> mylist = new ArrayList<Produit>();
        String req = " select * from produit" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add( new Produit(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getFloat(3) , this.resultSet.getString(4) , this.resultSet.getString(5)));
        }

        return mylist;
    }
}
