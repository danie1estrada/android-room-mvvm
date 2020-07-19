package daniel.estrada.mvvm.views.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import daniel.estrada.mvvm.databinding.DialogAddUserBinding
import daniel.estrada.mvvm.viewmodels.UserViewModel

class AddUserDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = activity?.let {
        val builder = AlertDialog.Builder(it)
        val binding = DialogAddUserBinding.inflate(requireActivity().layoutInflater)

        val vm = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.apply {
            lifecycleOwner = this@AddUserDialog
            uservm = vm
        }

        builder.apply {
            setTitle("Add user")
            setView(binding.root)
            .setPositiveButton("Ok", DialogInterface.OnClickListener { _, _ ->
                vm.save()
            })
        }

        builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")

}