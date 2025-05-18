package com.aubynsamuel.cardit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
        /** Helper for arranging detailItem and corresponding switch in a row **/
fun ItemRow(detailInputField: @Composable (() -> Unit), switch: @Composable (() -> Unit)) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.8f)) {
            detailInputField()
        }
        Row {
            switch()
        }
    }
}