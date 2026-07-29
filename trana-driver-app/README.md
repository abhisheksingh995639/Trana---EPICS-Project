# 🚑 Trana Driver & Paramedic Application (Kotlin — Native Android)

> **Role:** Ambulance driver & paramedic app for mandatory daily equipment checklist verification and emergency dispatch navigation.  
> **Tech Stack:** Kotlin, Jetpack Compose / XML, Foreground GPS Service, Google Maps Directions SDK, Firebase Realtime Database.

---

## 🛠️ Setup Instructions for Android Studio

1. **Open Project:** Open Android Studio and choose **Open an Existing Project**, pointing to `trana-driver-app/`.
2. **Firebase Configuration:**
   - In the Firebase Console, create an Android App with package name `com.trana.driver`.
   - Download `google-services.json` and place it inside `trana-driver-app/app/`.
3. **Permissions Required:**
   - Foreground Service and Background GPS Location access (`ACCESS_FINE_LOCATION`, `ACCESS_BACKGROUND_LOCATION`).
4. **Build & Run:**
   - Sync Gradle and click **Run** on a physical device or emulator.

---

## 🏗️ Architecture & Modules
- **`ui/checklist/`** — Mandatory daily BLS/ALS equipment verification screen required before going online.
- **`ui/alert/`** — 20-second incoming dispatch alert with high-contrast UI and one-tap *Accept/Decline*.
- **`service/location/`** — Foreground telemetry service pushing GPS coordinates every 3 seconds to Firebase `/ambulances/{id}/currentLocation`.
