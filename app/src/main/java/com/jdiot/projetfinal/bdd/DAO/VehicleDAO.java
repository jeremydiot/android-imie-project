package com.jdiot.projetfinal.bdd.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jdiot.projetfinal.bdd.DTO.VehicleDTO;

import java.util.List;

@Dao
public abstract class VehicleDAO {

    @Query("SELECT * FROM vehicles")
    public abstract List<VehicleDTO> getListVehicles();

    @Query("SELECT * FROM vehicles WHERE vehicleId = :id")
    public abstract List<VehicleDTO> getVehicleById(int id);

    @Query("DELETE FROM vehicles")
    public abstract void deleteAll();

    @Insert
    public abstract void insert(VehicleDTO... vehicles);

    @Update
    public abstract void update(VehicleDTO... vehicles);

    @Delete
    public abstract void delete(VehicleDTO... vehicles);


}
