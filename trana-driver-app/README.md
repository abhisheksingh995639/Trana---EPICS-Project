# 🚑 Trana Driver & Paramedic Application (Kotlin — Native Android)

> **Role:** Ambulance driver & paramedic app for mandatory daily equipment checklist verification, real-time emergency dispatch alerts, and live navigation.  
> **Tech Stack:** Kotlin, Jetpack Compose, Foreground GPS Service, Google Maps Directions SDK, Firebase Realtime Database, Firebase Cloud Messaging (FCM).

---

## 📂 Planned Module Structure

```
trana-driver-app/
└── app/src/main/java/com/trana/driver/
    ├── TranaDriverApplication.kt        # App class — Firebase init & app-wide setup
    ├── data/
    │   ├── model/
    │   │   └── Models.kt                # Kotlin data classes (Driver, Checklist, Dispatch, Location)
    │   └── firebase/
    │       └── FirebaseRepository.kt    # Firebase data-access layer (checklist, dispatch, GPS writes)
    ├── service/
    │   ├── LocationForegroundService.kt # Foreground service — GPS telemetry every 3 seconds
    │   └── TranaFirebaseMessagingService.kt  # FCM push — wakes driver on new dispatch alert
    └── ui/
        ├── MainActivity.kt              # Single Activity — Compose NavHost
        ├── navigation/
        │   └── NavRoutes.kt             # All navigation routes
        ├── splash/
        │   └── SplashScreen.kt          # Auth check & pending verification check
        ├── auth/
        │   ├── AuthViewModel.kt         # Firebase Phone Auth state
        │   ├── PhoneLoginScreen.kt      # Phone number entry
        │   ├── OtpVerificationScreen.kt # OTP entry & verification
        │   └── PendingApprovalScreen.kt # Shown until admin verifies driver account
        ├── checklist/
        │   ├── ChecklistScreen.kt       # Daily BLS/ALS equipment checklist (required before going online)
        │   └── ChecklistViewModel.kt    # Checklist state, Firebase write, online-lock logic
        ├── home/
        │   ├── HomeScreen.kt            # Online/Offline toggle, daily checklist status
        │   └── HomeViewModel.kt         # Driver status management
        ├── alert/
        │   ├── AlertScreen.kt           # 20-second incoming dispatch alert with Accept/Decline
        │   └── AlertViewModel.kt        # Countdown timer, accept/decline dispatch handling
        ├── navigation_to_patient/
        │   ├── NavigateToPatientScreen.kt  # Full-screen map routing to patient location
        │   └── NavigateToPatientViewModel.kt
        ├── intake/
        │   ├── PatientIntakeScreen.kt   # Quick patient condition form (forwarded live to hospital)
        │   └── PatientIntakeViewModel.kt
        └── navigation_to_hospital/
            ├── NavigateToHospitalScreen.kt  # Full-screen map routing to selected hospital
            └── NavigateToHospitalViewModel.kt
```

---

## 🧭 Navigation Flow

```
SplashScreen
    ├── [Logged in + Verified]    → HomeScreen
    ├── [Logged in + Pending]     → PendingApprovalScreen
    └── [Not logged in]           → PhoneLoginScreen
                                        └── OtpVerificationScreen
                                              ├── [New driver]      → PendingApprovalScreen
                                              └── [Verified driver] → HomeScreen

HomeScreen (must complete checklist first to go Online)
    └── [Receives FCM alert]      → AlertScreen (20s countdown)
            ├── [Accept]          → NavigateToPatientScreen
            │       └── [Arrived] → PatientIntakeScreen
            │               └── [Submitted] → NavigateToHospitalScreen
            │                       └── [Arrived at Hospital] → HomeScreen
            └── [Decline / Timeout] → HomeScreen (next driver is alerted automatically)
```

---

## 🛠️ Setup Instructions for Android Studio

### 1. Open the Project

Open Android Studio → **Open an Existing Project** → select `trana-driver-app/`.

### 2. Firebase Configuration

1. In the [Firebase Console](https://console.firebase.google.com/), add an Android App:
   - Package name: `com.trana.driver`
   - App nickname: `Trana Driver`
2. Download `google-services.json` and place it in `trana-driver-app/app/`
3. Enable in Firebase Console:
   - **Authentication → Phone** sign-in method
   - **Realtime Database**
   - **Cloud Messaging** (FCM — critical for dispatch alert wakeup)

### 3. Google Maps API Key

1. Enable in [Google Cloud Console](https://console.cloud.google.com/):
   - Maps SDK for Android
   - Directions API
2. Create an API key restricted to the driver app's SHA-1 fingerprint
3. Add to `local.properties`:

```properties
MAPS_API_KEY=YOUR_API_KEY_HERE
```

### 4. Build & Run

- Sync Gradle and click **Run ▶**
- Target **Android 8.0 (API 26) or higher**
- **Use a physical device** — GPS foreground service and FCM wakeup cannot be fully tested on emulators

---

## 🏗️ Architecture Pattern — MVVM + Foreground Service

The driver app extends the standard MVVM pattern with a **Foreground GPS Service**:

```
UI Layer (Compose Screens)
    ↕ StateFlow
ViewModel Layer
    ↕ coroutines / Flow
FirebaseRepository (data access)
    ↕ Firebase SDK

[Parallel — always running when driver is ONLINE]
LocationForegroundService
    → GPS fix every 3 seconds
    → Writes to Firebase /ambulances/{ambulanceId}/currentLocation
    → Keeps a persistent notification visible (Android foreground service requirement)
```

The `LocationForegroundService` binds to the app but also **survives app backgrounding** — critical for continuous GPS updates while the driver is navigating.

---

## 🔑 Key Permissions Required

| Permission | Reason |
|-----------|--------|
| `INTERNET` | Firebase & Maps API calls |
| `ACCESS_FINE_LOCATION` | Precise GPS for live telemetry |
| `ACCESS_COARSE_LOCATION` | Fallback location |
| `ACCESS_BACKGROUND_LOCATION` | Continue GPS when app is backgrounded (required in Android 10+) |
| `FOREGROUND_SERVICE` | Location telemetry foreground service |
| `FOREGROUND_SERVICE_LOCATION` | Android 14+ foreground service type declaration |
| `CALL_PHONE` | Call patient/hospital from navigation screens |
| `POST_NOTIFICATIONS` | FCM dispatch alert + foreground service notification (Android 13+) |

---

## ⚡ Key Features

### Daily Equipment Checklist (Core Proposal Feature)
Every driver **must** complete the daily equipment checklist before they can go Online. The checklist writes to Firebase `/checklists/` and sets `ambulances/{id}/equipmentVerifiedToday = true`. If any item fails, a warning is shown but the driver is still allowed to proceed (with the failure logged for admin review).

### 20-Second Dispatch Alert
When a new SOS is dispatched to this driver:
1. FCM notification wakes the app (even from background/killed state)
2. The full-screen `AlertScreen` appears with the patient's location, distance, and triage summary
3. A 20-second countdown starts — if not accepted in time, the dispatch auto-advances to the next nearest driver

### Live GPS Telemetry
While online and during an active dispatch, `LocationForegroundService` pushes GPS coordinates every **3 seconds** to:
```
/ambulances/{ambulanceId}/currentLocation/{ lat, lng, heading, updatedAt }
```
This is what powers the live ambulance marker on the patient's tracking map.

---

## 🔗 Related Docs

- [Firebase Setup & Schema](../firebase/README.md)
- [Technical Architecture](../implementation.md)
- [Full Build Steps](../STEPS.md#phase-6--driver--paramedic-app-development)
- [Contributing Guide](../CONTRIBUTING.md)
