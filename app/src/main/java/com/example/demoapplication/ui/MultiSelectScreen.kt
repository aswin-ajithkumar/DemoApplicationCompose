package com.example.demoapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MultiSelectScreen() {
    var items by remember {
        mutableStateOf(
            (1..20).map {
                ListItem(
                    title = "Item $it",
                    isSelected = false
                )
            }
        )
    }
    items.filter { it.isSelected }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items.size) { index ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        items = items.mapIndexed { j, item ->
                            if (index == j) {
                                item.copy(isSelected = !item.isSelected)
                            } else item
                        }
                    }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = items[index].title)
                if (items[index].isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.Green,
                        modifier = Modifier.size(20.dp)
                    )
                }

            }
        }
    }
}