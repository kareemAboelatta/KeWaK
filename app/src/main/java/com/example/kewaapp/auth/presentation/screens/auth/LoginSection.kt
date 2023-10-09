package com.example.kewaapp.auth.presentation.screens.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kewaapp.common.ui.components.AppTextField
import com.example.kewaapp.common.ui.components.PasswordTextField
import com.example.kewaapp.common.ui.theme.KewaAppTheme


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
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.email,
            hint = "Email",
            imageVector = Icons.Outlined.Email,
            onValueChange = {
                state.updateEmail(it)
            })
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.password,
            hint = "Password",
            imageVector = Icons.Filled.Lock,
            onValueChange = {
                state.updatePassword(it)
            }
        )

        Text(
            modifier = Modifier
                .align(End)
                .padding(5.dp),
            text = "Forget Password",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                //viewModel.login()
            }
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
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










