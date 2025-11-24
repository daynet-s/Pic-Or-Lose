package com.sefmat.picorlose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sefmat.picorlose.data.model.PictureModel
import com.sefmat.picorlose.repository.PictureRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PictureVM : ViewModel() {
    private val repo = PictureRepository()
    private val _pics = MutableStateFlow<List<PictureModel>>(emptyList())

    val pics: StateFlow<List<PictureModel>> = _pics

    init {
        fetchPics()
    }

    private fun fetchPics() {
        viewModelScope.launch {
            _pics.value = repo.getPictures()
        }
    }
}