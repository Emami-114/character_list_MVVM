package com.example.profile_list_mvvm

class Repository(private val apiService: ApiService) {
    fun getCharacter(page: Int) = apiService.fetchCaracter(page)
}