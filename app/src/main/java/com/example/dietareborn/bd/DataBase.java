package com.example.dietareborn.bd;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Usuario.class, Comida.class, Dieta.class},version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {


    private static volatile DataBase INSTANCE;

    public static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DataBase.class, "database").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }
    public abstract ComidaDao ComidaDao();
    public abstract UsuarioDao UsuarioDao();
    public abstract DietaDao DietaDao();

}