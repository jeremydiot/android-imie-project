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
    public String categorieco2;
    public List<Equipement> equipements = new ArrayList<>();
    public List<Option> options = new ArrayList<>();

    public Vehicle(int id, String nom, String image, int disponible, int prixjournalierbase, int promotion, int agemin, String categorieco2, List<Equipement> equipements, List<Option> options) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.disponible = disponible;
        this.prixjournalierbase = prixjournalierbase;
        this.promotion = promotion;
        this.agemin = agemin;
        this.categorieco2 = categorieco2;
        this.equipements = equipements;
        this.options = options;
    }
    public void addEquipement( Equipement equipement){
        equipements.add(equipement);
    }
    public void addOption( Option option){
        options.add(option);
    }

    public String equipmentsToString(){
        String equipmentStr = "";
        for (int i = 0; i < equipements.size(); i++){
            equipmentStr += equipements.get(i).toString();
        }

        return  equipmentStr;
    }

    public String optionsToString(String moneySymbol){
        String optionStr = "";
        for (int i = 0; i < options.size(); i++){
            optionStr += options.get(i).toString(moneySymbol);
        }

        return  optionStr;
    }
}
