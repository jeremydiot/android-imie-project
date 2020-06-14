package com.jdiot.projetfinal.bdd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jdiot.projetfinal.bdd.DAO.VehicleDAO;
import com.jdiot.projetfinal.bdd.DTO.VehicleDTO;

@Database(entities = {VehicleDTO.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract VehicleDAO vehicleDAO();
}
