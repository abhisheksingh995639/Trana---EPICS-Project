package com.trana.patient.ui.auth

// TODO: Implement AuthViewModel.
// Responsibilities:
//   - Hold Firebase Auth state (currentUser, loading, error)
//   - sendOtp(phoneNumber): trigger Firebase Phone Auth OTP
//   - verifyOtp(verificationId, otp): verify credential & sign in
//   - Expose auth state as StateFlow to PhoneLoginScreen and OtpVerificationScreen
