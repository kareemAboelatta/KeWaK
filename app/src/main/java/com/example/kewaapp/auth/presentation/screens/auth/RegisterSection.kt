package com.example.kewaapp.auth.presentation.screens.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
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
fun RegisterSectionPreview() {
    KewaAppTheme {
        RegisterSection()
    }
}

@Composable
fun RegisterSection() {

    val state = rememberRegisterDataState()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.name,
            hint = "Name",
            imageVector = Icons.Outlined.Person,
            onValueChange = {
                state.updateName(it)
            })

        AppTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.bio,
            hint = "Bio",
            imageVector = Icons.Default.Star,
            onValueChange = {
                state.updateBio(it)
            })

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


        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                //viewModel.Register()
            }
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}


@Composable
fun rememberRegisterDataState(): RegisterDataState =
    rememberSaveable(saver = RegisterDataState.Saver) {
        RegisterDataState()
    }

class RegisterDataState(
    val initialName: String = "",
    val initialBio: String = "",
    val initialEmail: String = "",
    val initialPassword: String = "",
) {
    var name by mutableStateOf(initialName)
        private set
    var bio by mutableStateOf(initialBio)
        private set

    var email by mutableStateOf(initialEmail)
        private set

    var password by mutableStateOf(initialPassword)
        private set

    fun updateName(newName: String) {
        name = newName
    }

    fun updateBio(newBio: String) {
        bio = newBio
    }


    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }


    companion object {
        val Saver: Saver<RegisterDataState, *> = listSaver(
            save = {
                listOf(it.name, it.bio, it.email, it.password)
            },
            restore = {
                RegisterDataState(
                    initialName = it[0],
                    initialBio = it[1],
                    initialEmail = it[2],
                    initialPassword = it[3],
                )
            }
        )

    }


}










