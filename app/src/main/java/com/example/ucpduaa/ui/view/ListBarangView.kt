package com.example.ucpduaa.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun CardBarang(
    brg: Barang,
    modifier: Modifier=Modifier,
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
            Row(modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.deskripsi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.stok,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

