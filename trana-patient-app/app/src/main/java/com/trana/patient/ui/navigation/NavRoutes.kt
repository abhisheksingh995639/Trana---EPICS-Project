package com.trana.patient.ui.navigation

/**
 * NavRoutes — defines all navigation route strings for the Trana Patient App.
 *
 * Using a sealed class (or object with string constants) ensures route strings
 * are defined in one place and never duplicated as magic strings across the codebase.
 *
 * ## Usage
 * ```kotlin
 * // Navigating to a screen:
 * navController.navigate(Screen.Triage.route)
 *
 * // Navigating and clearing the back stack (e.g., after login):
 * navController.navigate(Screen.Home.route) {
 *     popUpTo(Screen.PhoneLogin.route) { inclusive = true }
 * }
 * ```
 *
 * ## Route Map
 * | Screen             | Route String        | Entry Point                          |
 * |--------------------|---------------------|--------------------------------------|
 * | SplashScreen       | "splash"            | App cold start                       |
 * | OnboardingScreen   | "onboarding"        | First install (not logged in)        |
 * | PhoneLoginScreen   | "phone_login"       | After onboarding                     |
 * | OtpVerificationScreen | "otp_verification" | After phone number submitted        |
 * | ProfileSetupScreen | "profile_setup"     | After OTP verified (new user only)   |
 * | HomeScreen         | "home"              | After login / after profile setup    |
 * | TriageScreen       | "triage"            | After SOS confirmed on HomeScreen    |
 * | TrackingScreen     | "tracking"          | After triage completed               |
 * | HistoryScreen      | "history"           | From HomeScreen bottom sheet         |
 */

// TODO: Implement Screen sealed class:
//
// sealed class Screen(val route: String) {
//     object Splash          : Screen("splash")
//     object Onboarding      : Screen("onboarding")
//     object PhoneLogin      : Screen("phone_login")
//     object OtpVerification : Screen("otp_verification")
//     object ProfileSetup    : Screen("profile_setup")
//     object Home            : Screen("home")
//     object Triage          : Screen("triage")
//     object Tracking        : Screen("tracking")
//     object History         : Screen("history")
// }
