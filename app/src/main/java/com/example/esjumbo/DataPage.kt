package com.example.esjumbo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight


@OptIn(ExperimentalMaterial3Api::class)
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



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        
        Text(text = "Coustomer Data" , fontWeight = FontWeight.Bold)

        OutlinedTextField(value = nametxt,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { nametxt = it },
            label = { Text(text = stringResource(id = R.string.name)) })


        OutlinedTextField(value = addressTxt,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { addressTxt = it },
            label = { Text(text = stringResource(id = R.string.adedres)) })


        OutlinedTextField(value = tlpnTxt,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { tlpnTxt = it },
            label = { Text(text = stringResource(id = R.string.phonenumber)) })
        
        
    }



}