package com.trana.patient.ui

/**
 * MainActivity — the single Activity entry point for the Trana Patient App.
 *
 * This app uses a **single-Activity architecture** with Jetpack Compose navigation.
 * All screens are Composable functions managed by a `NavHost` defined here.
 *
 * ## Navigation Graph
 * ```
 * NavHost(startDestination = Screen.Splash.route) {
 *     composable(Screen.Splash.route)            { SplashScreen(navController) }
 *     composable(Screen.Onboarding.route)        { OnboardingScreen(navController) }
 *     composable(Screen.PhoneLogin.route)        { PhoneLoginScreen(navController) }
 *     composable(Screen.OtpVerification.route)   { OtpVerificationScreen(navController) }
 *     composable(Screen.ProfileSetup.route)      { ProfileSetupScreen(navController) }
 *     composable(Screen.Home.route)              { HomeScreen(navController) }
 *     composable(Screen.Triage.route)            { TriageScreen(navController) }
 *     composable(Screen.Tracking.route)          { TrackingScreen(navController) }
 *     composable(Screen.History.route)           { HistoryScreen(navController) }
 * }
 * ```
 *
 * ## Screen Routes
 * All route strings are defined in [com.trana.patient.ui.navigation.NavRoutes] to avoid
 * magic strings scattered across the codebase.
 *
 * ## Theme
 * The activity sets the Compose content inside a `TranaPatientTheme { }` wrapper
 * (defined in the `ui/theme/` package) to apply the app's color scheme, typography,
 * and shape tokens consistently across all screens.
 */

// TODO: Implement MainActivity:
//
// import androidx.activity.ComponentActivity
// import androidx.activity.compose.setContent
// import androidx.navigation.compose.NavHost
// import androidx.navigation.compose.composable
// import androidx.navigation.compose.rememberNavController
//
// class MainActivity : ComponentActivity() {
//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)
//         setContent {
//             TranaPatientTheme {
//                 val navController = rememberNavController()
//                 NavHost(navController = navController, startDestination = Screen.Splash.route) {
//                     // Register all screen composables here
//                 }
//             }
//         }
//     }
// }
