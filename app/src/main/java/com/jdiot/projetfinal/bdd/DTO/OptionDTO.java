package com.jdiot.projetfinal.bdd.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "options")
public class OptionDTO {

    @PrimaryKey
    public int optionId;
    public String nom;
    public int prix;
}
