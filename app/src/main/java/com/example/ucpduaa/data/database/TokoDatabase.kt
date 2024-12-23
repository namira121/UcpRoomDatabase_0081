package com.example.ucpduaa.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucpduaa.data.dao.BarangDao
import com.example.ucpduaa.data.dao.SuplierDao
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.data.entity.Suplier

@Database(entities = [Barang::class], [Suplier::class], version = 1, exportSchema = false)
abstract class TokoDatabase : RoomDatabase() {
    abstract fun barangDao(): BarangDao
    abstract fun suplierDao(): SuplierDao
}