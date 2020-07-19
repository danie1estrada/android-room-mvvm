package daniel.estrada.mvvm.views.adapters

import android.view.ViewGroup
import android.view.LayoutInflater
import daniel.estrada.mvvm.models.User
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import daniel.estrada.mvvm.databinding.ItemUserBinding

class MainActivityAdapter : ListAdapter<User, MainActivityAdapter.ViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemUser.user = getItem(position)
    }

    inner class ViewHolder(val itemUser: ItemUserBinding) : RecyclerView.ViewHolder(itemUser.root)
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}
