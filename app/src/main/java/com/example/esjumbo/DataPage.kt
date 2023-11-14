package com.example.esjumbo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun Datapage (

    onSubmitButtonClicked: (MutableList<String>) -> Unit


) {
    var nametxt by remember {
        mutableStateOf("")
    }

    var addressTxt by remember {
        mutableStateOf("")
    }

    var tlpnTxt by remember {
        mutableStateOf("")
    }
    var listData: MutableList<String> = mutableListOf(nametxt, addressTxt, tlpnTxt)


}