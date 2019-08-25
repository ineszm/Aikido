package com.example.aikido;

public class Salle extends Activite {
    private String id_s;
    private String nom_s;
  private  String adres_s;
  private String num_coach;
  private String num_act;
  private String tel_s;

    public Salle(String adres_s,String longitude,String latitude) {
        this.adres_s = adres_s;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Salle(String nom_s, String adress_s, String num_coach, String num_act, String tel_s, String email_s, String longitude, String latitude) {
        this.nom_s = nom_s;
        this.adres_s = adres_s;
        this.num_coach = num_coach;
        this.num_act = num_act;
        this.tel_s = tel_s;
        this.email_s = email_s;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private String email_s;
  private String longitude;
  private String latitude;

    public Salle() {
        super();
    }

    public Salle(String nom_s, String adres_s, String num_coach, String num_act, String tel_s, String email_s) {
        this.nom_s = nom_s;
        this.adres_s = adres_s;
        this.num_coach = num_coach;
        this.num_act = num_act;
        this.tel_s = tel_s;
        this.email_s = email_s;
    }

    public Salle(String id_u, String nom, String prenom, String email, String adresse, String tel, String password, String type, String id_a, String nom_a, String date_a, String duree, String num_part, String num_salle, String id_s, String nom_s, String adres_s, String num_coach, String num_act, String tel_s, String email_s, String longitude, String latitude) {
        super(id_u, nom, prenom, email, adresse, tel, password, type, id_a, nom_a, date_a, num_salle);
        this.id_s = id_s;
        this.nom_s = nom_s;
        this.adres_s = adres_s;
        this.num_coach = num_coach;
        this.num_act = num_act;
        this.tel_s = tel_s;
        this.email_s = email_s;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Salle(String nom, String prenom, String email, String adresse, String tel, String password, String type, String nom_a, String date_a, String duree, String num_part, String num_salle, String nom_s, String adres_s, String num_coach, String num_act, String tel_s, String email_s, String longitude, String latitude) {
        super(nom, prenom, email, adresse, tel, password, type, nom_a, date_a, duree, num_part, num_salle);
        this.nom_s = nom_s;
        this.adres_s = adres_s;
        this.num_coach = num_coach;
        this.num_act = num_act;
        this.tel_s = tel_s;
        this.email_s = email_s;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public String getId_s() {
        return id_s;
    }

    public void setId_s(String id_s) {
        this.id_s = id_s;
    }

    public String getNom_s() {
        return nom_s;
    }

    public void setNom_s(String nom_s) {
        this.nom_s = nom_s;
    }

    public String getAdress_s() {
        return adres_s;
    }

    public void setAdress_s(String adress_s) {
        this.adres_s = adress_s;
    }

    public String getNum_coach() {
        return num_coach;
    }

    public void setNum_coach(String num_coach) {
        this.num_coach = num_coach;
    }

    public String getNum_act() {
        return num_act;
    }

    public void setNum_act(String num_act) {
        this.num_act = num_act;
    }

    public String getTel_s() {
        return tel_s;
    }

    public void setTel_s(String tel_s) {
        this.tel_s = tel_s;
    }

    public String getEmail_s() {
        return email_s;
    }

    public void setEmail_s(String email_s) {
        this.email_s = email_s;
    }
}
