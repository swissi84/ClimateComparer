package de.syntax_institut.jetpack.ClimateComparer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


//open class ImageViewModel: ViewModel() {
//
////    private val api = NasaImageAPI.retrofitService
////
////    private val _nasaDataState = MutableStateFlow<List<NasaData>>(emptyList())
////    val nasaDataState = _nasaDataState.asStateFlow()
////
////    private val _nasaLinksState = MutableStateFlow<List<NasaLink>>(emptyList())
////    val nasaLinksState = _nasaLinksState.asStateFlow()
//
////    init { loadNasaImage("shuttle") }
////
////    fun loadNasaImage(searchQuery2: String) {
////        viewModelScope.launch {
////            try {
////                val response = api.searchImage(searchQuery2.lowercase())
////                val nasaDataList = response.collection.items.flatMap { it.data }
////                val nasaLinksList = response.collection.items.flatMap { it.links ?: emptyList() }
////
////                _nasaDataState.value = nasaDataList
////                _nasaLinksState.value = nasaLinksList
////
////            } catch (e: Exception) {
////                Log.e("load NasaImage", "Error: $e")
////                _nasaLinksState.value = emptyList()
////                _nasaDataState.value = emptyList()
////            }
////        }
////    }
//}
