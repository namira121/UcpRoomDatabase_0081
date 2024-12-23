package com.example.ucpduaa.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.data.entity.Suplier

@Composable
fun CardSuplier(
    spl: Suplier,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
){
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(modifier= Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = spl.namaspl,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(modifier= Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = spl.kontakspl,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(modifier= Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = spl.alamatspl,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun ListSuplier(
    listSpl: List<Suplier>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = { }
){
    LazyColumn(
        modifier=modifier
    ) {
        items(
            items = listSpl,
            itemContent = { spl ->
                CardSuplier(
                    spl= spl,
                    onClick = {onClick(spl.idspl)}
                )
            }
        )
    }
}
