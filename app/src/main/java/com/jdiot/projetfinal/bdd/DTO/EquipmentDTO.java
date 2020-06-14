package com.jdiot.projetfinal.bdd.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "equipments")
public class EquipmentDTO {

    @PrimaryKey
    public int equipmentId;
    public String nom;
}
