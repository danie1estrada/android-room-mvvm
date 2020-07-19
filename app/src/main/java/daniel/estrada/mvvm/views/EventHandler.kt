package daniel.estrada.mvvm.views

import android.content.Context
import android.widget.Toast
import daniel.estrada.mvvm.models.User

class EventHandler(private val context: Context) {

    fun click(user: User) {
        Toast.makeText(context, "${user.id} ${user.name}", Toast.LENGTH_SHORT).show()
    }
}