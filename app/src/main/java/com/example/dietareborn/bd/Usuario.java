package com.example.dietareborn.bd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "senha")
    public String senha;

    @ColumnInfo(name = "metaCal")
    public int metaCal;

    @ColumnInfo(name = "metaAgua")
    public int metaAgua;

    @ColumnInfo(name = "calDieta")
    public int calDieta;

    @ColumnInfo(name = "aguaDieta")
    public int aguaDieta;

    @ColumnInfo(name = "aguaSelect")
    public int aguaSelect;

    public Usuario () {

    }

    public Usuario (int id, String firstName, String lastName, String email, String senha, int metaCal, int metaAgua, int calDieta, int aguaDieta, int aguaSelect){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.senha = senha;
        this.metaCal = metaCal;
        this.metaAgua = metaAgua;
        this.calDieta = calDieta;
        this.aguaDieta = aguaDieta;
        this.aguaSelect = aguaSelect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getMetaCal() { return metaCal; }

    public void setMetaCal(int metaCal) { this.metaCal = metaCal; }

    public int getMetaAgua() { return metaAgua; }

    public void setMetaAgua(int metaAgua) { this.metaAgua = metaAgua; }

    public int getCalDieta() { return calDieta; }

    public void setCalDieta(int calDieta) { this.calDieta = calDieta; }

    public int getAguaDieta() { return aguaDieta; }

    public void setAguaDieta(int aguaDieta) { this.aguaDieta = aguaDieta; }

    public int getAguaSelect() { return aguaSelect; }

    public void setAguaSelect(int aguaSelect) { this.aguaSelect = aguaSelect; }
}

