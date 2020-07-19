package daniel.estrada.mvvm.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import daniel.estrada.mvvm.R
import daniel.estrada.mvvm.viewmodels.UserViewModel
import daniel.estrada.mvvm.views.adapters.MainActivityAdapter
import daniel.estrada.mvvm.views.dialogs.AddUserDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainActivityAdapter
    private lateinit var vm: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MainActivityAdapter().also { this@MainActivity.adapter = it }
        }

        vm = ViewModelProviders.of(this).get(UserViewModel::class.java)
        vm.users.observe(this, Observer {
            (recyclerView.adapter as MainActivityAdapter).submitList(it)
        })

        fab.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        AddUserDialog().showNow(supportFragmentManager, "add user")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)

        val searchItem = menu?.findItem(R.id.app_bar_search) as MenuItem
        val searchView = searchItem.actionView as SearchView

        searchView.apply {
            imeOptions = EditorInfo.IME_ACTION_DONE
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    vm.search(newText ?: "")
                    return false
                }
            })
        }

        return true
    }

}