package com.example.aikido;

public class Activite extends User {

    private String id_a;
    private String nom_a;
    private String date_a;

    public Activite(String nom, String prenom, String nom_a, String date_a, String num_salle) {
        super(nom, prenom);
        this.nom_a = nom_a;
        this.date_a = date_a;
        this.num_salle = num_salle;
    }

    private String num_salle;
    public Activite()
    {
        super();
    }

    public Activite(String nom_a, String date_a, String num_salle) {
        this.nom_a = nom_a;
        this.date_a = date_a;
        this.num_salle = num_salle;
    }

    public Activite(String id_u, String nom, String prenom, String email, String adresse, String tel, String password, String type, String id_a, String nom_a, String date_a, String num_salle) {
        super(id_u, nom, prenom, email, adresse, tel, password, type);
        this.id_a = id_a;
        this.nom_a = nom_a;
        this.date_a = date_a;


        this.num_salle = num_salle;
    }

    public Activite(String nom, String prenom, String email, String adresse, String tel, String password, String type, String nom_a, String date_a,  String num_salle) {
        super(nom, prenom, email, adresse, tel, password, type);
        this.nom_a = nom_a;
        this.date_a = date_a;


        this.num_salle = num_salle;
    }

    public String getId_a() {
        return id_a;
    }

    public void setId_a(String id_a) {
        this.id_a = id_a;
    }

    public String getNom_a() {
        return nom_a;
    }

    public void setNom_a(String nom_a) {
        this.nom_a = nom_a;
    }

    public String getDate_a() {
        return date_a;
    }

    public void setDate_a(String date_a) {
        this.date_a = date_a;
    }



  
    public String getNum_salle() {
        return num_salle;
    }

    public void setNum_salle(String num_salle) {
        this.num_salle = num_salle;
    }
}
