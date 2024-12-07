package com.example.cryptoinfo.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoinfo.model.Crypto
import com.example.cryptoinfo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {
    private val _cryptoList = MutableStateFlow<List<Crypto>>(emptyList())
    val cryptoList: StateFlow<List<Crypto>> get() = _cryptoList

    fun fetchCryptos() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCryptos()
                _cryptoList.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
