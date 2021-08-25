package com.faryz.testapp.second

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.faryz.testapp.R
import com.faryz.testapp.databinding.FragmentSecondBinding
import com.faryz.testapp.first.UserListViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var userListViewModel: UserListViewModel
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        if (!arguments?.getString("id").toString().isNullOrEmpty()) {
            val firstName = arguments?.getString("firstName").toString()
            val lastName = arguments?.getString("lastName").toString()
            val email = arguments?.getString("email").toString()
            val phone = arguments?.getString("phone").toString()

            binding.secondFirstName.setText(firstName)
            binding.secondLastName.setText(lastName)
            binding.secondEmail.setText(email)
            binding.secondPhone.setText(phone)
        } else{
            binding.secondFirstName.setText("")
            binding.secondLastName.setText("")
            binding.secondEmail.setText("")
            binding.secondPhone.setText("")
        }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel = ViewModelProvider(requireActivity()).get(UserListViewModel::class.java)

        binding.buttonSave.setOnClickListener {
            if (valid()) {

                val id = arguments?.getString("id").toString()
                val position = arguments?.getString("position").toString()
                val firstName = binding.secondFirstName.text.toString()
                val lastName = binding.secondLastName.text.toString()
                val email = binding.secondEmail.text.toString()
                val phone = binding.secondPhone.text.toString()
                if (!arguments?.getString("position").toString().isNullOrEmpty()) {
                    userListViewModel.editUserList(position,id,firstName, lastName,email, phone)
                } else {
                    //new data
                    userListViewModel.addUserList(id, firstName, lastName, email, phone)
                }

                findNavController().popBackStack()
            }
        }
        binding.buttonCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun valid(): Boolean {
        var valid = true

        if (binding.secondFirstName.text.isNullOrEmpty()) {
            binding.secondFirstName.error = "required"
            valid = false
        } else {
            binding.secondFirstName.error = null
        }

        if (binding.secondLastName.text.isNullOrEmpty()) {
            binding.secondLastName.error = "required"
            valid = false
        } else {
            binding.secondLastName.error = null
        }


        return valid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}