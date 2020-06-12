package com.jdiot.projetfinal.pojo;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    public int id;
    public String nom;
    public String image;
    public int disponible;
    public int prixjournalierbase;
    public int promotion;
    public int agemin;
    public char categorieco2;
    List<Equipement> equipements = new ArrayList<>();

    public Vehicle(int id, String nom, String image, int disponible, int prixjournalierbase, int promotion, int agemin, char categorieco2) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.disponible = disponible;
        this.prixjournalierbase = prixjournalierbase;
        this.promotion = promotion;
        this.agemin = agemin;
        this.categorieco2 = categorieco2;
    }

    public void addEquipement( Equipement equipement){
        equipements.add(equipement);
    }
}
