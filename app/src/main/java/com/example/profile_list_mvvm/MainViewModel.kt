package com.example.profile_list_mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private val repository: Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private val _characterList = MutableLiveData<ScreenState<List<CharacterList.Result>?>>()
    val characterList: LiveData<ScreenState<List<CharacterList.Result>?>>
        get() = _characterList

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {
        val client = repository.getCharacter("1")
        _characterList.postValue(ScreenState.Loading(null))
        client.enqueue(object : Callback<CharacterList> {
            override fun onResponse(call: Call<CharacterList>, response: Response<CharacterList>) {
                response.body()?.results?.get(0)?.name?.let { Log.d("MYTAG", it) }
                if (response.isSuccessful){
                _characterList.postValue(ScreenState.Success(response.body()?.results))
                }else{
                    _characterList.postValue(ScreenState.Error(response.code().toString(),null))
                }
            }

            override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                Log.d("MYTAG", t.message.toString())
                _characterList.postValue(ScreenState.Error(t.message.toString(),null))

            }


        })
    }
}