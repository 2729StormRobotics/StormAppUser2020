package org.stormroboticsnj.stormuserradar2020;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.stormroboticsnj.stormuserradar2020.dao.StormDao;
import org.stormroboticsnj.stormuserradar2020.models.Whoosh;

/**
 * Abstract class that is used to get to the StormDao interface
 */
@Database(entities = {Whoosh.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StormDao stormDao();
}
