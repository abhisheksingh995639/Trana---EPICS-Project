package com.trana.patient.ui.auth

/**
 * AuthViewModel — manages authentication state for the phone OTP login flow.
 *
 * This ViewModel is shared between [PhoneLoginScreen] and [OtpVerificationScreen]
 * to maintain OTP state across the two-step login process.
 *
 * ## State Flow
 * ```
 * PhoneLoginScreen
 *     user enters phone → calls sendOtp()
 *     AuthViewModel holds verificationId
 *     navigates to OtpVerificationScreen
 *
 * OtpVerificationScreen
 *     user enters 6-digit OTP → calls verifyOtp(verificationId, otp)
 *     Firebase validates credential
 *     if new user → navigate to ProfileSetup
 *     if existing user → navigate to Home
 * ```
 *
 * ## Firebase Phone Auth Flow
 * Firebase Phone Auth works in two steps:
 * 1. `PhoneAuthProvider.verifyPhoneNumber(phoneNumber, ...)` — Firebase sends the SMS OTP.
 *    The `onVerificationCompleted` callback fires automatically on some devices (e.g., if
 *    Firebase auto-detects the SMS). The `onCodeSent` callback fires with a `verificationId`.
 * 2. `PhoneAuthProvider.getCredential(verificationId, otp)` — builds a credential from the
 *    verificationId returned in step 1 and the OTP entered by the user.
 *    Then call `FirebaseAuth.signInWithCredential(credential)` to complete sign-in.
 *
 * ## Exposed State
 * | StateFlow         | Type              | Description                              |
 * |-------------------|-------------------|------------------------------------------|
 * | `authState`       | AuthUiState       | Loading / Success / Error / Idle         |
 * | `verificationId`  | String?           | Stored after OTP is sent, used to verify |
 * | `isNewUser`       | Boolean           | True if this is the first login          |
 */

// TODO: Implement AuthViewModel:
//
// import androidx.lifecycle.ViewModel
// import com.google.firebase.auth.FirebaseAuth
// import com.google.firebase.auth.PhoneAuthProvider
// import kotlinx.coroutines.flow.MutableStateFlow
// import kotlinx.coroutines.flow.StateFlow
//
// class AuthViewModel : ViewModel() {
//
//     private val auth = FirebaseAuth.getInstance()
//     private var _verificationId: String? = null
//
//     sealed class AuthUiState {
//         object Idle : AuthUiState()
//         object Loading : AuthUiState()
//         data class Error(val message: String) : AuthUiState()
//         data class OtpSent(val verificationId: String) : AuthUiState()
//         data class SignedIn(val isNewUser: Boolean) : AuthUiState()
//     }
//
//     private val _authState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
//     val authState: StateFlow<AuthUiState> = _authState
//
//     /**
//      * Sends a 6-digit OTP to the given phone number using Firebase Phone Auth.
//      *
//      * On success, [authState] transitions to [AuthUiState.OtpSent] with the verificationId.
//      * On failure, [authState] transitions to [AuthUiState.Error].
//      *
//      * @param phoneNumber The full phone number with country code (e.g., "+919876543210").
//      * @param activity    The calling Activity, required by the Firebase Phone Auth SDK.
//      */
//     fun sendOtp(phoneNumber: String, activity: Activity) { ... }
//
//     /**
//      * Verifies the OTP entered by the user and signs in with Firebase.
//      *
//      * On success, [authState] transitions to [AuthUiState.SignedIn].
//      * The [AuthUiState.SignedIn.isNewUser] flag determines whether to navigate to
//      * ProfileSetup (true) or directly to Home (false).
//      *
//      * @param verificationId The ID received in the [AuthUiState.OtpSent] state.
//      * @param otp            The 6-digit code entered by the user.
//      */
//     fun verifyOtp(verificationId: String, otp: String) { ... }
// }
