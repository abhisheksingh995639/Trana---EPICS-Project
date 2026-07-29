package com.trana.patient.ui.auth

/**
 * PhoneLoginScreen — the phone number entry screen for Firebase Phone Auth.
 *
 * This is the first step of the two-step OTP login flow. It collects the user's
 * mobile phone number and triggers Firebase to send a 6-digit OTP via SMS.
 *
 * ## UI Elements
 * - Country code prefix field (pre-filled with "+91" for India, selectable)
 * - Phone number input field (numeric keyboard, 10-digit validation)
 * - "Send OTP" primary action button — disabled until a valid 10-digit number is entered
 * - Loading indicator shown while Firebase sends the OTP
 * - Error Snackbar for invalid numbers or network failures
 *
 * ## Behaviour
 * 1. User enters their 10-digit mobile number
 * 2. Taps "Send OTP" → [AuthViewModel.sendOtp] is called with "+91{number}"
 * 3. While [AuthViewModel.authState] is [AuthUiState.Loading], a spinner replaces the button
 * 4. On [AuthUiState.OtpSent] → navigates to [OtpVerificationScreen]
 * 5. On [AuthUiState.Error] → shows Snackbar with error message
 *
 * ## Accessibility
 * - Input field has a content description for screen readers
 * - Button tap target is minimum 48dp height
 * - Error messages are announced via accessibility events
 *
 * ## Navigation
 * - **Incoming:** From [com.trana.patient.ui.onboarding.OnboardingScreen] (Get Started tap)
 * - **Outgoing:** To [OtpVerificationScreen] on OTP sent successfully
 */

// TODO: Implement PhoneLoginScreen composable:
//
// @Composable
// fun PhoneLoginScreen(
//     navController: NavController,
//     viewModel: AuthViewModel = viewModel()
// ) {
//     val authState by viewModel.authState.collectAsState()
//
//     // Navigate to OTP screen when OTP is sent
//     LaunchedEffect(authState) {
//         if (authState is AuthViewModel.AuthUiState.OtpSent) {
//             navController.navigate(Screen.OtpVerification.route)
//         }
//     }
//
//     // Build UI: country code + phone number input + Send OTP button
// }
