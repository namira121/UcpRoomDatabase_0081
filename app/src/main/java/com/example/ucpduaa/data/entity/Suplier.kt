package com.example.ucpduaa.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suplier")
data class Suplier (
    @PrimaryKey (autoGenerate = true)val idspl:Int,
    val namaspl: String,
    val kontakspl: String,
    val alamatspl: String
)