package com.example.dietareborn.bd;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comida {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nome")
    public String Nome;

    @ColumnInfo(name = "quantidade")
    public int Qtd;

    @ColumnInfo(name = "calorias")
    public int Cal;

    public Comida () {

    }

    public Comida (String Nome, int Qtd, int Cal) {
        this.Cal = Cal;
        this.Qtd = Qtd;
        this.Nome = Nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public int getQtd() {
        return Qtd;
    }

    public void setQtd(int qtd) {
        this.Qtd = qtd;
    }

    public int getCal() {
        return Cal;
    }

    public void setCal(int cal) {
        this.Cal = cal;
    }
}
