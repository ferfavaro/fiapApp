package com.fiap.fiapapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapCarViewModel : ViewModel() {
    private val _isFilterVisible = MutableLiveData<Boolean>(false)
    val isFilterVisible: LiveData<Boolean> get() = _isFilterVisible

    private val _distance = MutableLiveData<String>()
    val distance: LiveData<String> get() = _distance

    fun toggleFilterVisibility() {
        _isFilterVisible.value = !_isFilterVisible.value!!
    }

    fun setDistanceFilter(distance: String) {
        _distance.value = distance
    }
}
