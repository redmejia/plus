package com.binarystack01.plus

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.binarystack01.plus.screens.CheckOut
import com.binarystack01.plus.screens.Menu
import com.binarystack01.plus.screens.Wallet
import com.binarystack01.plus.ui.theme.PlusTheme
import com.binarystack01.plus.ui.theme.blue400
import com.binarystack01.plus.ui.theme.blue500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlusTheme {
                PlusApp()
            }
        }
    }
}
//
//I am trying to build the bottom bar navigation this my code the Top bar seems to be working but bottom bar is not
//here is the resourse I am folllowing for bottom bar but does not work
//https://developer.android.com/develop/ui/compose/navigation
//HERE CODE

// NOTE: The name defined in this enum MUST match the in the composable navHost example composable(route = PlusScreen.Menu.name){}
// also in th sealed class route property. The title property can be any name
enum class PlusScreen(val title: String) {
    Menu(title = "Menu"),
    CheckOut(title = "Check Out"),
    Wallet(title = "Wallet"),
}


sealed class BottomNavItem(
    val route: String,
    val title: String,
    val selected: Int,
    val unselected: Int,
) {
    object Menu : BottomNavItem(
        route = "Menu",
        title = "Menu",
        selected = R.drawable.storefront_filled,
        unselected = R.drawable.store_outline
    )

    object CheckOut : BottomNavItem(
        route = "CheckOut",
        title = "Pay",
        selected = R.drawable.payment_filled,
        unselected = R.drawable.payment_outline
    )

    object Wallet : BottomNavItem(
        route = "Wallet",
        title = "Wallet",
        selected = R.drawable.balance_wallet_filled,
        unselected = R.drawable.balance_wallet_outline
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlusTopBar(
    currentScreen: PlusScreen,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        colors = topAppBarColors(
            containerColor = blue500,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                color = Color.White,
                text = currentScreen.title
            )
        }
    )
}

@Composable
fun PlusBottomBar(
    navController: NavHostController,
) {
    val items = listOf(
        BottomNavItem.Menu,
        BottomNavItem.CheckOut,
        BottomNavItem.Wallet
    )

    BottomNavigation(navController = navController, items = items)
}

@Composable
fun BottomNavigation(
    navController: NavHostController,
    items: List<BottomNavItem>,
) {
    NavigationBar(
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        painter = painterResource(id = if (currentRoute == screen.route) screen.selected else screen.unselected),
                        contentDescription = screen.title,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                label = { Text(text = screen.title) }

            )
        }
    }
}


@Composable
fun PlusApp(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = PlusScreen.valueOf(
        backStackEntry?.destination?.route ?: PlusScreen.Menu.name
    )
    Scaffold(
        topBar = {
            PlusTopBar(currentScreen = currentScreen)
        },
        bottomBar = {
            PlusBottomBar(navController = navController)
        },

        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = PlusScreen.Menu.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = PlusScreen.Menu.name) {
                Menu()
            }

            composable(route = PlusScreen.CheckOut.name) {
                CheckOut()
            }

            composable(route = PlusScreen.Wallet.name) {
                Wallet()
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "plus"
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(text = "By BinaryStack")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlusTheme {
        Greeting()
    }
}