package org.sopt.and.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.PersistentList
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.Blue
import org.sopt.and.ui.theme.LightGray

@Composable
fun MainBottomBar(
    isVisible: Boolean,
    tabs: PersistentList<MainBottomTab>,
    currentTab: MainBottomTab?,
    onTabSelected: (MainBottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Black)
                .defaultMinSize(minHeight = 50.dp)
                .padding(vertical = 10.dp)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            tabs.forEach { tab ->
                MainNavigatorTab(
                    tab = tab,
                    isSelected = (tab == currentTab),
                    onClick = { onTabSelected(tab) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun MainNavigatorTab(
    tab: MainBottomTab,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = tab.icon,
            contentDescription = null,
            tint = if (isSelected) Blue else LightGray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = tab.title,
            style = TextStyle(
                fontSize = 18.sp,
                color = if (isSelected) Blue else LightGray
            )
        )
    }
}
