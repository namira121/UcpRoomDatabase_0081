package com.example.ucpduaa.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucpduaa.R

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onAddBarang: () -> Unit,
    onAddSuplier: () -> Unit,
    onListBarang: () -> Unit,
    onListSuplier: () -> Unit,
 ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.purple_200)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.toko),
                contentDescription = "",
                modifier = Modifier.size(45.dp).clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.padding(start = 16.dp))
            Column {
                Text(
                    text = "Toko Murah Hati",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Murah Banget :3",
                    color = Color.Blue,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp)
                )
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = onAddBarang
                ) {
                    Text(text = "Tambah Barang")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = onListBarang
                ) {
                    Text(text = "Daftar Barang")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = onAddSuplier
                ) {
                    Text(text = "Tambah Suplier")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = onListSuplier
                ) {
                    Text(text = "Daftar Suplier")
                }
            }
        }
    }
}