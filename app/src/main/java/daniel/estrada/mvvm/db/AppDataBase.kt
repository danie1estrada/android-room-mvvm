package daniel.estrada.mvvm.db

import android.app.Application
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import daniel.estrada.mvvm.models.User
import daniel.estrada.mvvm.daos.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: AppDataBase? = null

        fun getInstance(application: Application): AppDataBase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(application, AppDataBase::class.java, "app-db")
                .build().also { instance = it }
        }
    }
}