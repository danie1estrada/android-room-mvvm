package daniel.estrada.mvvm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo var username: String = "",
    @ColumnInfo var name: String = "",
    @ColumnInfo var role: String = ""
)