package id.ac.unpas.showroom.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.showroom.model.Mobil

@Dao
interface MobilDao {
    @Query("SELECT * FROM Mobil")
    fun loadAll(): LiveData<List<Mobil>>

    @Query("SELECT * FROM Mobil")
    suspend fun getList(): List<Mobil>

    @Query("SELECT * FROM Mobil WHERE id = :id")
    suspend fun find(id: String): Mobil?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Mobil)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Mobil>)

    @Delete
    fun delete(item: Mobil)

    @Query("DELETE FROM Mobil WHERE id = :id")
    fun delete(id: String)
}
