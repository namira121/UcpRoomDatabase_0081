package com.example.ucpduaa.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nama: String,
    val deskripsi: String,
    val harga: String,
    val stok: String,
    val namaspl: String,
)