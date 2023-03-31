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

    private val _filterList = MutableLiveData<List<CharacterList.Result>?>()
    val filterList: LiveData<List<CharacterList.Result>?>
        get() = _filterList

    init {
        fetchCharacter()
    }
    init {
        filter("")

    }

    fun filter(name: String) {
        if (name == "Alle") {
            _filterList.postValue(_characterList.value?.data)
        } else {

            _filterList.value = characterList.value?.data?.filter { it.name == name }
        }
    }


    private fun fetchCharacter() {
        val client = repository.getCharacter(1)
        _characterList.postValue(ScreenState.Loading(null))
        client.enqueue(object : Callback<CharacterList> {
            override fun onResponse(call: Call<CharacterList>, response: Response<CharacterList>) {
                if (response.isSuccessful) {
                    _characterList.postValue(ScreenState.Success(response.body()?.results))
                } else {
                    _characterList.postValue(ScreenState.Error(response.code().toString(), null))
                }
            }

            override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                Log.d("MYTAG", t.message.toString())
                _characterList.postValue(ScreenState.Error(t.message.toString(), null))

            }


        })
    }
}