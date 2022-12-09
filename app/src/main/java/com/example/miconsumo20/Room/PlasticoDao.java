package com.example.miconsumo20.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlasticoDao {

    @Insert
    void insert(Plasticos plasticos);

    @Update
    void update(Plasticos plasticos);

    @Query("delete from Plasticos where id=:id")
    void delete(int id);

    @Query("Select * from Plasticos")
    List<Plasticos> getAllPlasticos();

}
