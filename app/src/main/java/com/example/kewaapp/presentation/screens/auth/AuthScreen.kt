package com.example.kewaapp.presentation.screens.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import com.example.kewaapp.R
import com.example.kewaapp.presentation.theme.KewaAppTheme
import kotlinx.coroutines.launch

enum class AuthScreens {
    Login, Register
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFFFFFFFF)

@Composable
fun AuthScreenPreview() {
    KewaAppTheme {
        AuthScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    // viewModel: MainViewModel = viewModel(),
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.error),
        scaffoldState = scaffoldState,
        sheetShadowElevation = 20.dp,
        sheetTonalElevation = 10.dp,
        sheetPeekHeight = 130.dp,

        sheetContent = {
            Text(
                if (scaffoldState.bottomSheetState.hasPartiallyExpandedState)  "Swipe up to Login" else "Login"
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginSection()
            }
        }) {
        Box {
            AuthBackground()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun AuthBackground() {
    KewaAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxSize()
                .padding(60.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "KeWaK",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontStyle = FontStyle.Italic,
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.background
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_crane_drawer),
                modifier = Modifier.padding(5.dp),
                contentDescription = "Kewak logo"
            )
        }
    }

}


@Preview
@Composable
fun AuthTabBar(
    onTabSelected: (AuthScreens) -> Unit = {},
    tabSelected: AuthScreens = AuthScreens.Login,
    modifier: Modifier = Modifier,
) {
    AuthTabs(
        modifier = modifier
            .padding(5.dp)
            .clip(CircleShape),
        titles = AuthScreens.values().map { it.name },
        tabSelected = tabSelected,
        onTabSelected = { newTab -> onTabSelected(AuthScreens.values()[newTab.ordinal]) }
    )
}

@Composable
fun AuthTabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: AuthScreens,
    onTabSelected: (AuthScreens) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected.ordinal,
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { },
        divider = { }
    ) {
        titles.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal

            var textModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)

            if (selected) {
                textModifier =
                    Modifier
                        .border(
                            BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                            RoundedCornerShape(16.dp)
                        )
                        .then(textModifier)
            }

            Tab(
                modifier = modifier.padding(2.dp),
                selected = selected,
                onClick = { onTabSelected(AuthScreens.values()[index]) },
                selectedContentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    modifier = textModifier,
                    text = title.uppercase(
                        ConfigurationCompat.getLocales(LocalConfiguration.current)[0]!!
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}





