package com.example.aikido;

public class Client extends  User {
  private String id_c;
    private String date_ajout;
    private String frais;
    private String mode_paiement;
 public Client()
 {
   super();
 }

    public Client(String id_u, String nom, String prenom, String email, String adresse, String tel, String password, String type, String id_c, String date_ajout, String frais, String mode_paiement) {
        super(id_u, nom, prenom, email, adresse, tel, password, type);
        this.id_c = id_c;
        this.date_ajout = date_ajout;
        this.frais = frais;
        this.mode_paiement = mode_paiement;
    }

    public Client(String nom, String prenom, String email, String adresse, String tel, String password, String type, String date_ajout, String frais, String mode_paiement) {
        super(nom, prenom, email, adresse, tel, password, type);
        this.date_ajout = date_ajout;
        this.frais = frais;
        this.mode_paiement = mode_paiement;
    }

    public Client(String date_ajout, String frais, String mode_paiement) {
        this.date_ajout = date_ajout;
        this.frais = frais;
        this.mode_paiement = mode_paiement;
    }

    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getFrais() {
        return frais;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }
}
