package com.example.aikido;

public class Respo extends User {
    private String id_r;
    public Respo()
    {
        super();
    }

    public Respo(String id_u, String nom, String prenom, String email, String adresse, String tel, String password, String type, String id_r) {
        super(id_u, nom, prenom, email, adresse, tel, password, type);
        this.id_r = id_r;
    }

    public Respo(String nom, String prenom, String email, String adresse, String tel, String password, String type) {
        super(nom, prenom, email, adresse, tel, password, type);
    }

    public String getId_r() {
        return id_r;
    }

    public void setId_r(String id_r) {
        this.id_r = id_r;
    }
}
