package com.example.dumdumteaapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esjumbo.R
import com.example.esjumbo.data.OrderUIState
import com.example.esjumbo.ui.theme.komponen.FormatlabelHarga

@Composable
fun SecondPage(
    orderUIState: OrderUIState,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        Pair(stringResource(R.string.quantity), orderUIState.jumlah),
        Pair(stringResource(R.string.flavor), orderUIState.rasa),
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.padding_small)
                )
            ) {
                Text(text = stringResource(id = R.string.name))
                Text(text = orderUIState.name)
                Divider()
                Spacer(modifier = Modifier.padding(16.dp))


                Text(text = stringResource(id = R.string.adedres))
                Text(text = orderUIState.adress)
                Divider()
                Spacer(modifier = Modifier.padding(16.dp))

                Text(text = stringResource(id = R.string.phonenumber))
                Text(text = orderUIState.tlp)
                Divider()

                Spacer(modifier = Modifier.padding(16.dp))

                items.forEach { item ->
                    Column {
                        Text(item.first.uppercase())
                        Text(text = item.second.toString(), fontWeight = FontWeight.Bold)
                    }
                    Divider(thickness = dimensionResource(R.dimen.thickness_divider))
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                FormatlabelHarga(
                    subtotal = orderUIState.harga,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f, false)
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)))
                {
                    Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.send))
                    }
                    OutlinedButton(
                        onClick = onCancelButtonClicked,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.cansel))
                    }
                }
            }
        }
    }
