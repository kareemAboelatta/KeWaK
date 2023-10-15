package com.example.kewaapp.auth.presentation.screens.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import androidx.navigation.NavHostController
import com.example.common.common.Dimensions.IconSize
import com.example.common.common.PaddingDimensions
import com.example.common.theme.KewaAppTheme
import com.example.kewaapp.R

import com.example.kewaapp.common.ui.components.animations.InfinitelyScaling

enum class AuthScreens {
    Login, Register
}

/*
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFFFFFFFF)
@Composable
fun AuthScreenPreview() {
    KewaAppTheme {
        AuthScreen()
    }
}
*/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
    // viewModel: MainViewModel = viewModel(),
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(scaffoldState = scaffoldState,
        sheetShadowElevation = 10.dp,
        sheetTonalElevation = 10.dp,
        sheetPeekHeight = 130.dp,
        sheetContent = {
            BottomSheetBody(
                navController=navController,
                scaffoldState= scaffoldState
            )
        }) {
        Box {
            AuthBackground()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun BottomSheetBody(
    navController: NavHostController,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()
) {
    Column(
        Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(1000),

                )
            .padding(vertical = 20.dp), horizontalAlignment = CenterHorizontally
    ) {

        val isExpanded = scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded

        var selectedTap by remember {
            mutableStateOf(AuthScreens.Login)
        }

        BottomSheetHeader(isExpanded = isExpanded, tabSelected = selectedTap, onTabSelected = {
            selectedTap = it
        })
        Spacer(modifier = Modifier.height(60.dp))

        AnimatedContent(
            selectedTap,
            label = "Animated Content",
        ) { targetState ->
            when (selectedTap) {
                AuthScreens.Login -> LoginSection(navController = navController)
                AuthScreens.Register -> RegisterSection()
            }
        }
        SocialRow(onGoogleClick = {}, onFacebookClick = {})

    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomSheetHeader(
    isExpanded: Boolean,
    onTabSelected: (AuthScreens) -> Unit = {},
    tabSelected: AuthScreens = AuthScreens.Login
) {
    Column {

        AnimatedContent(
            isExpanded,
            label = "Animated Content",
        ) { isExpanded ->
            if (!isExpanded) {
                Text(
                    "Swipe up to Login", style = MaterialTheme.typography.titleLarge
                )
            } else {
                AuthTabBar(
                    onTabSelected = onTabSelected, tabSelected = tabSelected
                )
            }

        }
    }

}

@Composable
fun SocialRow(
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(IconSize)
                .clickable {
                    onGoogleClick()
                },
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Google",

            )
        Spacer(modifier = Modifier.width(PaddingDimensions.large))
        Image(
            modifier = Modifier
                .size(IconSize)
                .clickable {
                    onFacebookClick()
                },
            painter = painterResource(id = R.drawable.facebook),
            contentDescription = "Facebook"
        )
    }
}


@Composable
fun AuthBackground() {
    KewaAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            InfinitelyScaling {
                Column {
                    Text(
                        text = "KeWaK", style = MaterialTheme.typography.headlineLarge.copy(
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
    }

}


@Preview
@Composable
fun AuthTabBar(
    modifier: Modifier = Modifier,
    onTabSelected: (AuthScreens) -> Unit = {},
    tabSelected: AuthScreens = AuthScreens.Login,
) {
    AuthTabs(modifier = modifier.padding(5.dp),
        titles = AuthScreens.values().map { it.name },
        tabSelected = tabSelected,
        onTabSelected = { newTab -> onTabSelected(AuthScreens.values()[newTab.ordinal]) })
}

@Composable
fun AuthTabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: AuthScreens,
    onTabSelected: (AuthScreens) -> Unit
) {
    TabRow(selectedTabIndex = tabSelected.ordinal,
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { },
        divider = { }) {
        titles.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal

            var textModifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = PaddingDimensions.small, horizontal = PaddingDimensions.xLarge
                )

            if (selected) {
                textModifier = Modifier
                    .border(
                        BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        RoundedCornerShape(PaddingDimensions.xLarge)
                    )
                    .then(textModifier)
            }

            Tab(
                modifier = modifier.padding(2.dp),
                selected = selected,
                onClick = { onTabSelected(AuthScreens.values()[index]) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.secondary
            ) {
                Text(
                    modifier = textModifier.align(CenterHorizontally),
                    text = title.uppercase(
                        ConfigurationCompat.getLocales(LocalConfiguration.current)[0]!!
                    ),
                    style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
                )
            }
        }
    }
}





