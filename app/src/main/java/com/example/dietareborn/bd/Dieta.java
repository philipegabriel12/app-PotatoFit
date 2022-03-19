package com.example.dietareborn.bd;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(foreignKeys = {@ForeignKey(entity = Usuario.class,
        parentColumns = "id",childColumns = "id_usuario"),
        @ForeignKey(entity = Comida.class,
                parentColumns = "id",childColumns = "id_comida")})

public class Dieta {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "id_usuario")
    public int id_usuario;

    @ColumnInfo(name = "id_comida")
    public int id_comida;

    @ColumnInfo(name = "calorias")
    public int calorias;

    @ColumnInfo(name = "quantidade")
    public int quantidade;

    public Dieta () {

    }

    public Dieta (int id_usuario, int id_comida, int calorias, int quantidade) {
        this.id_comida = id_comida;
        this.id_usuario = id_usuario;
        this.calorias = calorias;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_comida() {
        return id_comida;
    }

    public void setId_comida(int id_comida) {
        this.id_comida = id_comida;
    }

    public float getCal() {
        return calorias;
    }

    public void setCal(int calorias) { this.calorias = calorias; }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
