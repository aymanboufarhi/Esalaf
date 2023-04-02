package com.exemple.model;


// java beans (Entity)
public class Client {

        private Long id_client ;

        private String nom ;

        private String telepehone ;

        private String cin ;


    public Client() {
    }

    public Client(Long id_client, String nom, String telepehone, String cin) {
        this.id_client = id_client;
        this.nom = nom;
        this.telepehone = telepehone;
        this.cin = cin;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelepehone() {
        return telepehone;
    }

    public void setTelepehone(String telepehone) {
        this.telepehone = telepehone;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", telepehone='" + telepehone + '\'' +   ", Cin='" + cin + '\'' +
                '}';
    }
}
