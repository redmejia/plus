package com.binarystack01.plus.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.binarystack01.plus.components.ProductCard

@Composable
fun Menu(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        data class DummyData(val name: String, val lastName: String)
        val users: List<DummyData> = listOf(
            DummyData(name = "Re", lastName = "me"),
            DummyData(name = "ee", lastName = "e"),
            DummyData(name = "ee", lastName = "e"),
            DummyData(name = "Re", lastName = "me"),
            DummyData(name = "ee", lastName = "e"),
            DummyData(name = "ee", lastName = "e"),
            DummyData(name = "Re", lastName = "me"),
            DummyData(name = "ee", lastName = "e"),
            DummyData(name = "ee", lastName = "e"),
            DummyData(name = "Re", lastName = "me"),
            DummyData(name = "ee", lastName = "e"),
            DummyData(name = "ee", lastName = "e")
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(2.dp),
            columns = GridCells.Fixed(2),
        ) {
            items(users) { item ->
                Log.d("ITEM", "OrderCardPreview: $item")
                ProductCard()
            }
        }
    }
}