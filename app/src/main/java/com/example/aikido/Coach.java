package com.example.aikido;

public class Coach extends User {
    private String id_co;
    private String poids;
    private String taille;
    private String date_ajout;
    public Coach()
    {
        super();
    }

    public Coach(String id_u, String nom, String prenom, String email, String adresse, String tel, String password, String type, String id_co, String poids, String taille, String date_ajout) {
        super(id_u, nom, prenom, email, adresse, tel, password, type);
        this.id_co = id_co;
        this.poids = poids;
        this.taille = taille;
        this.date_ajout = date_ajout;
    }

    public Coach(String poids, String taille, String date_ajout) {
        this.poids = poids;
        this.taille = taille;
        this.date_ajout = date_ajout;
    }

    public Coach(String nom, String prenom, String email, String adresse, String tel, String password, String type, String poids, String taille, String date_ajout) {
        super(nom, prenom, email, adresse, tel, password, type);
        this.poids = poids;
        this.taille = taille;
        this.date_ajout = date_ajout;
    }

    public String getId_co() {
        return id_co;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public void setId_co(String id_co) {
        this.id_co = id_co;
    }
}
