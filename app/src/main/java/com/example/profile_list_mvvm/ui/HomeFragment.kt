package com.example.profile_list_mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.profile_list_mvvm.*
import com.example.profile_list_mvvm.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.characterList.observe(viewLifecycleOwner, Observer { character ->

            processCharacterResponse(character)

        })
//        viewModel.filterList.observe(viewLifecycleOwner) { itState ->
////            binding.btnStart.setOnClickListener {
////                viewModel.filter("Morty Smith")
//            val adapter = itState?.let { MainAdapter(it) }
//            val recyclerView = binding.recyclerView
//            recyclerView.adapter = adapter
////
//            }
    }


    private fun processCharacterResponse(state: ScreenState<List<CharacterList.Result>?>) {
        val progress = binding.progressBar
        when (state) {
            is ScreenState.Loading -> {
                progress.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                progress.visibility = View.GONE
                if (state.data != null) {

                    val adapter = MainAdapter(state.data)
                    val recyclerView = binding.recyclerView
                    recyclerView.adapter = adapter
                }
            }
            is ScreenState.Error ->{
                progress.visibility = View.GONE
                val view = progress.rootView
                Snackbar.make(view,state.message!!, Snackbar.LENGTH_SHORT).show()

            }
        }
    }
}