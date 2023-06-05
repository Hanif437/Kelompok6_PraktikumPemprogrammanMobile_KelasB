package id.ac.unpas.showroom.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.showroom.model.Promo

@Dao
interface PromoDao {
    @Query("SELECT * FROM Promo")
    fun loadAll(): LiveData<List<Promo>>

    @Query("SELECT * FROM Promo")
    suspend fun getList(): List<Promo>

    @Query("SELECT * FROM Promo WHERE id = :id")
    suspend fun find(id: String): Promo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Promo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Promo>)

    @Delete
    fun delete(item: Promo)

    @Query("DELETE FROM Promo WHERE id = :id")
    fun delete(id: String)
}
