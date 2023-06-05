package id.ac.unpas.showroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mobil(
    @PrimaryKey val id: String,
    val merk: String,
    val model: String,
    val bahan_bakar: String,
    val dijual: String,
    val deskripsi: String,
)