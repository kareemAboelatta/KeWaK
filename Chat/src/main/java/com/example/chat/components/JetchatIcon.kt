
package com.example.chat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.example.chat.R
import io.eyram.iconsax.IconSax

@Composable
fun JetchatIcon(
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }
    Box(modifier = modifier.then(semantics)) {
        Icon(
            painter = painterResource(id = IconSax.Bold.AaveAave),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primaryContainer
        )
        Icon(
            painter = painterResource(id =  IconSax.Bold.AddCircle),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
