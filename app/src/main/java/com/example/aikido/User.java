package com.example.aikido;

public class User {
    private String id_u;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String tel;
    private String password;
    private String type;

    public User(String nom, String prenom, String email, String adresse, String tel, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public  User ()
{

}
    public User(String id_u, String nom, String prenom, String email, String adresse, String tel, String password, String type) {
        this.id_u = id_u;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.password = password;
        this.type = type;
    }

    public User(String nom, String prenom, String email, String adresse, String tel, String password, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.password = password;
        this.type = type;
    }

    public String getId_u() {
        return id_u;
    }

    public void setId_u(String id_u) {
        this.id_u = id_u;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
