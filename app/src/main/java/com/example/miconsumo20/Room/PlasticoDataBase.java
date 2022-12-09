package com.example.miconsumo20.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Plasticos.class},version = 1)
public abstract class PlasticoDataBase extends RoomDatabase {

    public abstract PlasticoDao getDao();

    public static PlasticoDataBase INSTANCE;

    public static PlasticoDataBase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, PlasticoDataBase.class, "PlasticoDataBase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
