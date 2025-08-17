package com.aubynsamuel.cardit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
        /** Switch for toggling whether to allow sharing a contact detail **/
fun SwitchableTextField(
    label: String? = "",
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        label?.let { Text(it) }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}