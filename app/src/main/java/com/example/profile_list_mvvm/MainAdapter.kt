package com.example.profile_list_mvvm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.profile_list_mvvm.databinding.ItemListBinding
import com.example.profile_list_mvvm.Charakter

class MainAdapter(val characterList: List<CharacterList.Result>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(charakter: CharacterList.Result){
            Log.d("MYTAG",charakter.name)
            binding.apply {
                textView.text = charakter.name
                imageShape.load(charakter.image){
                    placeholder(R.drawable.ic_launcher_background)
                    crossfade(true)
                    scale(Scale.FILL)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(characterList[position])
//        holder.binding.textView.text = characterList[position].name
    }
}