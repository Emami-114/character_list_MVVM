package com.example.profile_list_mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.profile_list_mvvm.MainAdapter
import com.example.profile_list_mvvm.MainViewModel
import com.example.profile_list_mvvm.R
import com.example.profile_list_mvvm.ScreenState
import com.example.profile_list_mvvm.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {

   private lateinit var binding : FragmentMovieBinding
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.filterList.observe(viewLifecycleOwner) { itState ->
            binding.btnStart.setOnClickListener {
                viewModel.filter("Summer Smith")

            }


            binding.recyclerView.adapter = itState?.let { MainAdapter(it) }



        }
    }
}