package com.example.spot.presentation.navigation.bottom_nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpotBottomNavigation (
    items: List<BottomNavigationItem>,
    onItemSelected: Int,
    onItemClicked: (Int) -> Unit
) {
    NavigationBar (
        modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem (
                selected = index == onItemSelected,
                onClick = { onItemClicked(index) },
                icon = {
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon (
                            painter = painterResource(item.icon),
                            contentDescription = "Navigation Icon",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text (
                            text = item.title,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors (
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SpotBottomNavigationPreview() {
    
}