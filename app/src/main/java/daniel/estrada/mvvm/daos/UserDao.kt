package daniel.estrada.mvvm.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import daniel.estrada.mvvm.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): User

    @Query("SELECT * FROM users WHERE name LIKE '%' || :args || '%'")
    fun find(args: String): LiveData<List<User>>

}