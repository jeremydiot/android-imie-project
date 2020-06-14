package com.jdiot.projetfinal.bdd.DTO;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class VehicleOption {
    @Embedded public VehicleDTO vehicleDTO;
    @Relation(
            parentColumn = "vehicleId",
            entityColumn = "optionId",
            associateBy = @Junction(VehicleOptionDTO.class)
    )
    public List<OptionDTO> optionDTOList;

}
