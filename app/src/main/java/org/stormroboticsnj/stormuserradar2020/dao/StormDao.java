package org.stormroboticsnj.stormuserradar2020.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.stormroboticsnj.stormuserradar2020.models.Whoosh;

import java.util.List;

/**
 * This interface creates methods that conduct SQL queries. There are only four basic ones in this
 * version, but more can easily be added for more advanced data tools. See Room guide at
 * https://developer.android.com/training/data-storage/room/ for more information
 */
@Dao
public interface StormDao {
    @Insert
    public void insertWhooshes(Whoosh whoosh);

    @Update
    public void updateWhooshes(Whoosh whoosh);

    @Delete
    public void deleteWhooshes(Whoosh whoosh);

    @Query("SELECT * FROM whooshes")
    public List<Whoosh> getAllWhooshes();
}