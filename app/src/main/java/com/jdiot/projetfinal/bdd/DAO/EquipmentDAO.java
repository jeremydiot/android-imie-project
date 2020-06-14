package com.jdiot.projetfinal.bdd.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jdiot.projetfinal.bdd.DTO.EquipmentDTO;

import java.util.List;

@Dao
public abstract class EquipmentDAO {

    @Query("SELECT * FROM equipments")
    public abstract List<EquipmentDTO> getListCourses();

    @Query("SELECT COUNT(*) FROM equipments WHERE equipmentId = :id")
    public abstract long getEquipmentById(int id);

    @Insert
    public abstract void insert(EquipmentDTO... courses);

    @Update
    public abstract void update(EquipmentDTO... courses);

    @Delete
    public abstract void delete(EquipmentDTO... courses);
}
