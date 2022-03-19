package com.example.dietareborn.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComidaDao {
    @Query("SELECT * FROM comida")
    List<Comida> getComidaList();

    @Query("SELECT * FROM comida WHERE nome = :Nome")
    int comidaExists(String Nome);

    @Query ("SELECT * FROM comida WHERE id = :id")
    Comida comidaById(int id);

    @Query ("SELECT nome FROM comida WHERE id = :id")
    String nomeById(int id);

    @Insert
    void insertComida(Comida comida);

    @Update
    void updateComida(Comida comida);

    @Delete
    void deleteComida(Comida comida);
}
