package id.ac.unpas.showroom.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.showroom.model.SepedaMotor

@Dao
interface SepedaMotorDao {
    @Query("SELECT * FROM SepedaMotor")
    fun loadAll(): LiveData<List<SepedaMotor>>

    @Query("SELECT * FROM SepedaMotor")
    suspend fun getList(): List<SepedaMotor>

    @Query("SELECT * FROM SepedaMotor WHERE id = :id")
    suspend fun find(id: String): SepedaMotor?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: SepedaMotor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SepedaMotor>)

    @Delete
    fun delete(item: SepedaMotor)

    @Query("DELETE FROM SepedaMotor WHERE id = :id")
    fun delete(id: String)
}
