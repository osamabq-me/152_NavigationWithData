package com.example.esjumbo

import android.icu.text.NumberFormat
import androidx.lifecycle.ViewModel
import com.example.esjumbo.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


private  const val Harga_per_cup = 3000

class OrderViewModel : ViewModel() {

    private val _stateUI = MutableStateFlow(OrderUIState())
    val stateUi: StateFlow<OrderUIState> = _stateUI.asStateFlow()


    fun setContact(list: MutableList<String>){
        _stateUI.update {
                statenow-> statenow.copy(
            name = list[0],
            adress = list[1],
            tlp = list[2]
        )
        }
    }

    fun setJumlah (jmlEsjumbo: Int){
        _stateUI.update { stateSaatIni -> stateSaatIni.copy(
            jumlah = jmlEsjumbo,
            harga = hitungHarga(jumlah = jmlEsjumbo)
        ) }
    }

    fun setRasa(rasaPilihan: String){
        _stateUI.update { stateSaatIni -> stateSaatIni.copy(rasa = rasaPilihan) }
    }
    fun resetOrder(){
        _stateUI.value = OrderUIState()
    }

    private fun hitungHarga( jumlah:Int = _stateUI.value.jumlah,
                             ): String{
        val kalkulasiHarga = jumlah * Harga_per_cup
        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }


}