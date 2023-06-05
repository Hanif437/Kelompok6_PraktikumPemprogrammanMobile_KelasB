package id.ac.unpas.showroom.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.showroom.model.Mobil
import id.ac.unpas.showroom.model.SepedaMotor
import id.ac.unpas.showroom.model.Promo

@Database(entities = [Mobil::class, SepedaMotor::class, Promo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mobilDao(): MobilDao
    abstract fun sepedaMotorDao(): SepedaMotorDao
    abstract fun promoDao(): PromoDao
}