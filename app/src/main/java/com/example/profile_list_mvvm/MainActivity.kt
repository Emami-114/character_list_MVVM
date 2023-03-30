package com.example.profile_list_mvvm

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        viewModel.characterList.observe(this, Observer { character ->
            processCharacterResponse(character)

        })

    }

    private fun processCharacterResponse(state: ScreenState<List<CharacterList.Result>?>) {
        val progress = findViewById<ProgressBar>(R.id.progress_bar)
        when (state) {
            is ScreenState.Loading -> {
                progress.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                progress.visibility = View.GONE
                if (state.data != null) {
                    val adapter = MainAdapter(state.data)
                    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                    recyclerView.adapter = adapter
                }
            }
            is ScreenState.Error ->{
                progress.visibility = View.GONE
                val view = progress.rootView
                Snackbar.make(view,state.message!!,Snackbar.LENGTH_SHORT).show()

            }
        }
    }
}