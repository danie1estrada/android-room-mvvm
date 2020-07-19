package daniel.estrada.mvvm.viewmodels

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import daniel.estrada.mvvm.db.AppDataBase
import daniel.estrada.mvvm.models.User
import daniel.estrada.mvvm.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val query = MutableLiveData<String>().apply { value = "" }
    private val repository: UserRepository
    val users: LiveData<List<User>>
    val user:  MutableLiveData<User>

    init {
        repository = UserRepository(AppDataBase.getInstance(application).userDao())
        users = Transformations.switchMap(query) { query ->
            return@switchMap if (query.isEmpty()) {
                repository.getAll()
            } else
                repository.find(query)
        }
        user = MutableLiveData<User>().apply { value = User() }
    }

    fun search(searchQuery: String) {
        query.value = searchQuery
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) { repository.insert(user) }

    fun save() = viewModelScope.launch { user.value?.let { repository.insert(it) } }

    fun click(context: Context) {
        Toast.makeText(context, "${user.value?.name}", Toast.LENGTH_SHORT).show()
        //Snackbar.make(v, "${user.value?.name}", Snackbar.LENGTH_SHORT).show()
    }
}