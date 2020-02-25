package com.example.seiska;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyTableDao{

    @Query("SELECT * FROM myentity ORDER BY id DESC")
    List<MyEntity> Descending();
    @Insert
    void InsertEntity(MyEntity myEntity);
}