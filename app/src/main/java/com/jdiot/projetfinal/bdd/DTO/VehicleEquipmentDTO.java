package com.jdiot.projetfinal.bdd.DTO;

import androidx.room.Entity;

@Entity(primaryKeys = {"vehicleId","equipmentId"},tableName = "vehiclesEquipments")
public class VehicleEquipmentDTO {
    public int vehicleId;
    public int equipmentId;
}
