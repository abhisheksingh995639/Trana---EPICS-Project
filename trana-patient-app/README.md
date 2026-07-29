# 📱 Trana Patient SOS Mobile Application (Kotlin — Native Android)

> **Role:** Patient-facing emergency SOS application with one-tap dispatch, rapid triage, and real-time ambulance tracking.  
> **Tech Stack:** Kotlin, Jetpack Compose, Firebase Auth (Phone OTP), Firebase Realtime Database, Google Maps Android SDK, Firebase Cloud Messaging (FCM).

---

## 📂 Module Structure

```
trana-patient-app/
└── app/src/main/java/com/trana/patient/
    ├── TranaApplication.kt              # App class — Firebase init & app-wide setup
    ├── data/
    │   ├── model/
    │   │   └── Models.kt                # Kotlin data classes mapping to Firebase JSON
    │   └── firebase/
    │       └── FirebaseRepository.kt    # Single data-access layer for all Firebase operations
    ├── service/
    │   └── TranaFirebaseMessagingService.kt  # FCM push notification handler
    └── ui/
        ├── MainActivity.kt              # Single Activity — hosts the Compose NavHost
        ├── navigation/
        │   └── NavRoutes.kt             # All navigation route definitions
        ├── splash/
        │   └── SplashScreen.kt          # Auth check & redirect
        ├── onboarding/
        │   └── OnboardingScreen.kt      # 3-slide intro pager (first install only)
        ├── auth/
        │   ├── AuthViewModel.kt         # Firebase Phone Auth state & OTP logic
        │   ├── PhoneLoginScreen.kt      # Phone number entry UI
        │   ├── OtpVerificationScreen.kt # 6-digit OTP entry with resend timer
        │   └── ProfileSetupScreen.kt    # New user profile collection (name, blood group, emergency contact)
        ├── home/
        │   ├── HomeScreen.kt            # Main SOS screen — 3-second press-and-hold button
        │   └── HomeViewModel.kt         # Address reverse-geocoding & dispatch creation
        ├── triage/
        │   ├── TriageScreen.kt          # 3 yes/no emergency questions (one per step)
        │   └── TriageViewModel.kt       # Triage answer state & Firebase dispatch update
        ├── tracking/
        │   ├── TrackingScreen.kt        # Live ambulance map with ETA and driver info
        │   └── TrackingViewModel.kt     # Firebase Realtime listeners for GPS & dispatch status
        └── history/
            └── HistoryScreen.kt         # Past dispatch log
```

---

## 🧭 Navigation Flow

```
SplashScreen
    ├── [Logged in]     → HomeScreen
    └── [Not logged in] → OnboardingScreen
                              └── PhoneLoginScreen
                                    └── OtpVerificationScreen
                                          ├── [New user]      → ProfileSetupScreen → HomeScreen
                                          └── [Existing user] → HomeScreen

HomeScreen (SOS confirmed, 3-second hold) → TriageScreen
TriageScreen (3 questions answered)        → TrackingScreen
TrackingScreen (dispatch COMPLETED)        → HistoryScreen
```

---

## 🛠️ Setup Instructions for Android Studio

### 1. Open the Project

Open Android Studio → **Open an Existing Project** → select `trana-patient-app/`.

### 2. Firebase Configuration

1. In the [Firebase Console](https://console.firebase.google.com/), create an Android App:
   - Package name: `com.trana.patient`
   - App nickname: `Trana Patient`
2. Download `google-services.json` and place it in `trana-patient-app/app/`
3. Enable in Firebase Console:
   - **Authentication → Phone** sign-in method
   - **Realtime Database** (import `firebase/schema.json` as seed data)
   - **Cloud Messaging** (FCM)

### 3. Google Maps API Key

1. In [Google Cloud Console](https://console.cloud.google.com/), enable:
   - Maps SDK for Android
   - Geocoding API
2. Create an API key restricted to your app's SHA-1 fingerprint
3. Add it to `local.properties` (this file is gitignored):

```properties
MAPS_API_KEY=YOUR_API_KEY_HERE
```

The key is injected into `AndroidManifest.xml` automatically via `build.gradle.kts`.

### 4. Build & Run

- Sync Gradle (Android Studio will prompt you)
- Click **Run ▶** — target **Android 8.0 (API 26) or higher**
- A physical device is strongly recommended for testing Firebase Phone Auth

---

## 🏗️ Architecture Pattern — MVVM

Each screen follows the **MVVM (Model-View-ViewModel)** pattern:

```
Screen (Composable UI)
    ↕ observes StateFlow
ViewModel (business logic, state holder)
    ↕ calls suspend functions / Flows
FirebaseRepository (data access)
    ↕ reads/writes
Firebase Realtime Database
```

- **Screens** are stateless — they only render from `StateFlow` and forward events to the ViewModel
- **ViewModels** survive configuration changes and hold all UI state as `StateFlow`
- **FirebaseRepository** is the single source of truth for all Firebase reads and writes
- All async operations use Kotlin **Coroutines** and **Flow**

---

## 📦 Key Dependencies

| Dependency | Purpose |
|-----------|---------|
| `androidx.navigation.compose` | Compose multi-screen navigation |
| `firebase-auth` | Phone number OTP authentication |
| `firebase-database` | Realtime Database read/write + live listeners |
| `firebase-messaging` | FCM push notifications |
| `play-services-maps` | Google Maps Android SDK |
| `maps-compose` | Compose wrapper for Google Maps |
| `play-services-location` | GPS / Fused Location Provider |

See `app/build.gradle.kts` for exact version references (managed via BOM).

---

## 🔑 Permissions Required

| Permission | Reason |
|-----------|--------|
| `INTERNET` | Firebase & Maps API calls |
| `ACCESS_FINE_LOCATION` | Precise GPS for SOS dispatch |
| `ACCESS_COARSE_LOCATION` | Fallback location |
| `CALL_PHONE` | "Call Driver" button in TrackingScreen |
| `VIBRATE` | Emergency haptic feedback on SOS confirmation |
| `POST_NOTIFICATIONS` | Show FCM dispatch status notifications (Android 13+) |

---

## 🔗 Related Docs

- [Firebase Setup & Schema](../firebase/README.md)
- [Technical Architecture](../implementation.md)
- [Full Build Steps](../STEPS.md#phase-5--patient-app-development)
- [Contributing Guide](../CONTRIBUTING.md)
