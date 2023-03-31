package com.example.profile_list_mvvm

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.profile_list_mvvm.ui.HomeFragment
import com.example.profile_list_mvvm.ui.MessageFragment
import com.example.profile_list_mvvm.ui.MovieFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity(),OnTabSelectedListener {
    var fragment: Fragment? = null

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = HomeFragment()

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment!!)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()

        tabLayout.addOnTabSelectedListener(this)




//        viewModel.characterList.observe(this, Observer { character ->
//            processCharacterResponse(character)
//
//        })


    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> {
                fragment = HomeFragment()
                viewModel.filter("Alle")

            }
            1 -> {
                fragment = MovieFragment()
                viewModel.filter("Morty Smith")


            }
            2 -> {
                fragment = MessageFragment()
                viewModel.filter("Summer Smith")
            }
        }
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment!!)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> {
                fragment = HomeFragment()
                viewModel.filter("Alle")

            }
            1 -> {
                fragment = MovieFragment()
                viewModel.filter("Morty Smith")


            }
            2 -> {
                fragment = MessageFragment()
                viewModel.filter("Summer Smith")

            }
        }
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment!!)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

}