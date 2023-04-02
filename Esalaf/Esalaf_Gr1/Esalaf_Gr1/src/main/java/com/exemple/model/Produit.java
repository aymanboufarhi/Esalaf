package com.exemple.model;

public class Produit {
    public Long id_produit ;

    private String nom ;

    private Float prix ;

    private String quantite ;

    private String categories ;

    public Produit() {
    }

    public Produit(Long id_produit, String nom, Float prix, String quantite, String categories) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.categories = categories;
    }

    public Long getIdProduit() {
        return id_produit;
    }

    public void setId_Produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }


    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom='" + nom + '\'' +
                ", Prix='" + prix + '\'' +   ", Quantite='" + quantite + '\'' +   ", Categories='" + categories + '\'' +
                '}';
    }

}
