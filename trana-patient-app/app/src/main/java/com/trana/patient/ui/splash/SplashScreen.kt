package com.trana.patient.ui.splash

/**
 * SplashScreen — the app's initial loading screen shown on cold start.
 *
 * Displayed for a brief moment while the app checks whether the user is
 * already authenticated with Firebase. The user is then redirected without
 * any further interaction required.
 *
 * ## UI Elements
 * - Trana logo (centered)
 * - App name "Trana" (large typography)
 * - Optional tagline (e.g., "Help is on the way")
 * - No buttons or input — this screen is purely automatic
 *
 * ## Auth Check Logic
 * ```
 * FirebaseAuth.currentUser != null
 *     → true  : navigate to HomeScreen (clear back stack)
 *     → false : wait 2 seconds, navigate to OnboardingScreen
 * ```
 *
 * The 2-second delay on the "not logged in" path serves as a branding moment;
 * it is not shown when the user is already logged in (that redirect is instant).
 *
 * ## Navigation
 * - **Incoming:** App cold start (registered as `startDestination` in NavHost)
 * - **Outgoing (logged in):** To [HomeScreen] immediately
 * - **Outgoing (not logged in):** To [OnboardingScreen] after 2 seconds
 *
 * Note: `SplashScreen` should never appear in the back stack — always use
 * `popUpTo(Screen.Splash.route) { inclusive = true }` when navigating away.
 */

// TODO: Implement SplashScreen composable:
//
// @Composable
// fun SplashScreen(navController: NavController) {
//     val currentUser = FirebaseAuth.getInstance().currentUser
//
//     LaunchedEffect(Unit) {
//         if (currentUser != null) {
//             navController.navigate(Screen.Home.route) {
//                 popUpTo(Screen.Splash.route) { inclusive = true }
//             }
//         } else {
//             delay(2000L)
//             navController.navigate(Screen.Onboarding.route) {
//                 popUpTo(Screen.Splash.route) { inclusive = true }
//             }
//         }
//     }
//
//     // Display logo and app name centered on screen
// }
