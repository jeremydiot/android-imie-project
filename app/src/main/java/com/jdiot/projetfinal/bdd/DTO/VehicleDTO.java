package com.jdiot.projetfinal.bdd.DTO;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicles")
public class VehicleDTO {

    @PrimaryKey
    public int vehicleId;

    public String nom;
    public String image;
    public int disponible;
    public int prixjournalierbase;
    public int promotion;
    public int agemin;
    public String categorieco2;

//    public List<Equipement> equipements = new ArrayList<>();
//    public List<Option> options = new ArrayList<>();

}
