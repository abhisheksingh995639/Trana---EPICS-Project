package com.trana.patient.ui.auth

/**
 * ProfileSetupScreen — collects new patient profile information after first login.
 *
 * Displayed only for **new users** (users whose `/users/{uid}` node does not yet exist
 * in Firebase). After submitting, the profile is written to Firebase and the user is
 * navigated to the main [HomeScreen].
 *
 * ## UI Elements
 * - Full name text input field
 * - Blood group dropdown (options: A+, A−, B+, B−, AB+, AB−, O+, O−, Unknown)
 * - Emergency contact name text input
 * - Emergency contact phone number input
 * - "Save & Continue" primary action button — disabled until name and blood group are filled
 * - Loading indicator during Firebase write
 * - Error Snackbar if the write fails
 *
 * ## Data Written to Firebase
 * Path: `/users/{uid}`
 * ```json
 * {
 *   "name": "Ananya Sharma",
 *   "phone": "+919876543210",
 *   "role": "patient",
 *   "bloodGroup": "O+",
 *   "createdAt": "2026-07-29T12:00:00Z"
 * }
 * ```
 * The phone number is retrieved from `FirebaseAuth.currentUser.phoneNumber`
 * (already verified in the OTP step) — never entered again by the user.
 *
 * ## Navigation
 * - **Incoming:** From [OtpVerificationScreen] when `isNewUser == true`
 * - **Outgoing:** To [HomeScreen] after successful Firebase write
 *   (back stack is cleared so the user cannot navigate back to login)
 */

// TODO: Implement ProfileSetupScreen composable:
//
// @Composable
// fun ProfileSetupScreen(
//     navController: NavController,
//     viewModel: ProfileSetupViewModel = viewModel()  // or use a shared ViewModel
// ) {
//     // Collect: name, bloodGroup, emergencyContactName, emergencyContactPhone
//     // On "Save & Continue": call repository.saveUserProfile(uid, user)
//     // On success: navController.navigate(Screen.Home.route) {
//     //     popUpTo(Screen.Splash.route) { inclusive = true }
//     // }
// }
