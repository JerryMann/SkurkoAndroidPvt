package by.itacademy.pvt.skurkoandroidpvt.dz15

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.POI_TABLE_NAME
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi

@Dao
interface Dao {

    @Query("SELECT * FROM $POI_TABLE_NAME")
    fun get(): List<Poi>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Poi>)
}