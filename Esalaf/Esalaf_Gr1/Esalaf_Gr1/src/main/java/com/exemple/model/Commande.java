package com.exemple.model;

public class Commande {
    public Long id ;

    private String cin;

    private String produit ;

    private String quantite ;

    private String etat ;

    public Commande() {
    }

    public Commande(Long id, String cin, String produit, String quantite, String etat) {
        this.id = id;
        this.cin = cin;
        this.produit = produit;
        this.quantite = quantite;
        this.etat = etat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getProduit() {
        return produit;
    }

    public void setproduit(String produit) {
        this.produit = produit;
    }

    public String getQuantite() {return quantite;}

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getEtat() {
        return etat;
    }

    public void setetat(String etat) {
        this.etat = etat;
    }


    @Override
    public String toString() {
        return "Commande{" +
                "Id_commande=" + id +
                ", Cin='" + cin + '\'' +
                ", Produit='" + produit + '\'' +   ", Quantite='" + quantite + '\'' +   ", État='" + etat + '\'' +
                '}';
    }

}
