package com.jdiot.projetfinal.bdd.DTO;

import androidx.room.Entity;

@Entity(primaryKeys = {"vehicleId","optionId"},tableName = "vehiclesOptions")
public class VehicleOptionDTO {
    public int vehicleId;
    public int optionId;
}
