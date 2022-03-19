package com.example.dietareborn.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DietaDao {
    @Query("SELECT * FROM dieta")
    List<Dieta> getDietaList();

    @Query("SELECT * FROM dieta WHERE id_usuario = :id_usuario")
    Dieta dietaById(int id_usuario);

    @Query("SELECT * FROM dieta WHERE id = :id")
    Dieta dietaEspecificaById(int id);

    @Query("SELECT * FROM dieta WHERE id_usuario = :id_usuario")
    List<Dieta> listDietaByUser(int id_usuario);

    @Query ("SELECT calorias FROM dieta WHERE id = :id")
    int caloriaById(int id);

    @Insert
    void insertDieta(Dieta dieta);

    @Update
    void updateDieta(Dieta dieta);

    @Delete
    void deleteDieta(Dieta dieta);

    @Query("DELETE FROM dieta WHERE id_usuario = :id_usuario ")
    public void deleteAllDietaFromUser(int id_usuario);
}
