package com.jdiot.projetfinal.bdd.DTO;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class VehicleEquipment {
    @Embedded public VehicleDTO vehicleDTO;
    @Relation(
            parentColumn = "vehicleId",
            entityColumn = "equipmentId",
            associateBy = @Junction(VehicleEquipmentDTO.class)
    )
    public List<EquipmentDTO> equipmentDTOList;
}
