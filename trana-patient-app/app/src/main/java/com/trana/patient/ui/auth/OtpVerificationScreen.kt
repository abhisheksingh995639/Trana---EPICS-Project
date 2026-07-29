package com.trana.patient.ui.auth

/**
 * OtpVerificationScreen — the 6-digit OTP entry screen for Firebase Phone Auth.
 *
 * This is the second step of the two-step OTP login flow. It collects the OTP
 * sent via SMS and verifies it with Firebase to complete sign-in.
 *
 * ## UI Elements
 * - 6 individual digit input boxes (auto-advance on each digit entry; backspace moves backwards)
 * - "Verify" primary action button — disabled until all 6 digits are filled
 * - 60-second countdown resend timer
 * - "Resend OTP" link — enabled only after the 60-second cooldown expires
 * - Loading indicator shown while Firebase verifies the credential
 * - Error Snackbar for invalid OTP or network failures
 *
 * ## Behaviour
 * 1. Screen displays the phone number the OTP was sent to (passed from [PhoneLoginScreen])
 * 2. User fills in the 6-digit OTP — focus auto-advances across boxes
 * 3. Taps "Verify" → [AuthViewModel.verifyOtp] is called
 * 4. On [AuthUiState.Loading] → spinner + button disabled
 * 5. On [AuthUiState.SignedIn]:
 *    - If `isNewUser == true` → navigate to [ProfileSetupScreen]
 *    - If `isNewUser == false` → navigate to [HomeScreen] (clear back stack)
 * 6. On [AuthUiState.Error] → Snackbar + clear OTP boxes
 * 7. After 60 seconds → "Resend OTP" link becomes tappable, calls [AuthViewModel.sendOtp] again
 *
 * ## Navigation
 * - **Incoming:** From [PhoneLoginScreen] after OTP is sent
 * - **Outgoing (new user):** To [ProfileSetupScreen]
 * - **Outgoing (existing user):** To [HomeScreen] (with `popUpTo(PhoneLogin) { inclusive = true }`)
 */

// TODO: Implement OtpVerificationScreen composable:
//
// @Composable
// fun OtpVerificationScreen(
//     navController: NavController,
//     viewModel: AuthViewModel = viewModel()
// ) {
//     val authState by viewModel.authState.collectAsState()
//
//     LaunchedEffect(authState) {
//         when (val state = authState) {
//             is AuthViewModel.AuthUiState.SignedIn -> {
//                 if (state.isNewUser) {
//                     navController.navigate(Screen.ProfileSetup.route)
//                 } else {
//                     navController.navigate(Screen.Home.route) {
//                         popUpTo(Screen.PhoneLogin.route) { inclusive = true }
//                     }
//                 }
//             }
//             else -> {}
//         }
//     }
//
//     // Build UI: 6-box OTP input + countdown timer + Verify button
// }
