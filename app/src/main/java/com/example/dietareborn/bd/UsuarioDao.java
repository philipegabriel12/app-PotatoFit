package com.example.dietareborn.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT first_name FROM usuario")
    List<String> getFirstNameList();

    @Query ("SELECT * FROM usuario WHERE email = :email AND senha = :senha")
    Usuario getUser(String email, String senha);

    @Query ("SELECT metaCal FROM usuario WHERE id = :id")
    int metaCalById(int id);

    @Query ("SELECT email FROM usuario WHERE id = :id")
    String emailById(int id);

    @Query ("SELECT senha FROM usuario WHERE id = :id")
    String senhaById(int id);

    @Query ("SELECT * FROM usuario WHERE id = :id")
    Usuario userById(int id);

    @Query("SELECT * FROM usuario WHERE email = :email")
    int emailExists(String email);

    @Insert
    void insertUsuario(Usuario usuario);

    @Update
    void updateUsuario(Usuario usuario);

    @Delete
    void deleteDieta(Usuario usuario);
}
