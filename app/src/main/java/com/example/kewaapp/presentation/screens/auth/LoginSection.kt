package com.example.kewaapp.presentation.screens.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kewaapp.presentation.theme.KewaAppTheme


@Preview(
    showBackground = true
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun LoginSectionPreview() {
    KewaAppTheme {
        LoginSection()
    }
}




@Composable
fun LoginSection() {

    val state = rememberLoginDataState()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTextField(
            text = state.email,
            hint = "Email",
            imageVector = Icons.Outlined.Email,
            onValueChange = {
                state.updateEmail(it)
            })
        AppTextField(
            text = state.password,
            hint = "Password",
            imageVector = Icons.Filled.Lock,
            onValueChange = {
                state.updatePassword(it)
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(horizontal = 10.dp)
            ,
            onClick = {
                //viewModel.login()
            }
        ) {
            Text(text = "Login")
        }
    }
}


@Composable
fun AppTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium,
    singleLine: Boolean = true,
    imageVector: ImageVector = Icons.Filled.Info
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        textStyle = textStyle,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = hint)
        },
        singleLine = singleLine,
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = "")
        },


    )
}


@Composable
fun rememberLoginDataState(): LoginDataState =
    rememberSaveable(saver = LoginDataState.Saver) {
        LoginDataState()
    }

class LoginDataState(
    val initialEmail: String = "",
    val initialPassword: String = "",
) {
    var email by mutableStateOf(initialEmail)
        private set
    var password by mutableStateOf(initialPassword)
        private set

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }


    companion object {
        val Saver: Saver<LoginDataState, *> = listSaver(
            save = {
                listOf(it.email, it.password)
            },
            restore = {
                LoginDataState(
                    initialEmail = it[0],
                    initialPassword = it[1],
                )
            }
        )

    }


}










