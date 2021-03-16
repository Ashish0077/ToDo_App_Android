package com.example.todoapp.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.data.viewmodel.ToDoViewModel
import com.example.todoapp.databinding.FragmentUpdateBinding
import com.example.todoapp.fragments.SharedViewModel

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        setHasOptionsMenu(true)
        binding.currentDescriptionEt.setText(args.currentItem.description)
        binding.currentTitleEt.setText(args.currentItem.title)
        binding.currentPrioritiesSpinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save) {
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = binding.currentTitleEt.text.toString()
        val desc = binding.currentDescriptionEt.text.toString()
        val priority = mSharedViewModel.parsePriorityString(binding.currentPrioritiesSpinner.selectedItem.toString())
        if(mSharedViewModel.verifyDataFromUser(title, desc)) {
            val updatedItem = ToDoData(
                id = args.currentItem.id,
                title = title,
                priority = priority,
                description = desc
            )
            mToDoViewModel.updateData(updatedItem)
            Toast.makeText(
                    requireContext(),
                    "Successfully Updated!",
                    Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(
                    requireContext(),
                    "Please fill out all the fields.",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}