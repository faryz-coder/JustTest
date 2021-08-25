package com.faryz.testapp.first

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faryz.testapp.R
import com.faryz.testapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var userListViewModel: UserListViewModel
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userListViewModel = ViewModelProvider(requireActivity()).get(UserListViewModel::class.java)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        val recyclerView = binding.userRecyclerView
        fun displayUser() {
            userListViewModel.users.observe(viewLifecycleOwner, Observer {
                d("bomoh", "user list: $it")
                val userListAdapter = UserList(it)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@FirstFragment.context)
                    adapter = userListAdapter
                }
                userListAdapter.notifyDataSetChanged()
            })
        }
        displayUser()


        binding.swipeToRefresh.setOnRefreshListener {
            displayUser()
            binding.swipeToRefresh.isRefreshing = false
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}