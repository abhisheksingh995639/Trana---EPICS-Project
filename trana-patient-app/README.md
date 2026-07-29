# 📱 Trana Patient SOS Mobile Application (Kotlin — Native Android)

> **Role:** Patient-facing emergency SOS application with one-tap dispatch and real-time ambulance tracking.  
> **Tech Stack:** Kotlin, Jetpack Compose / XML, Firebase Auth, Google Maps Android SDK, Firebase Realtime Database.

---

## 🛠️ Setup Instructions for Android Studio

1. **Open Project:** Open Android Studio and choose **Open an Existing Project**, pointing to `trana-patient-app/`.
2. **Firebase Configuration:**
   - In the Firebase Console, create a new Android App with package name `com.trana.patient`.
   - Download `google-services.json` and place it inside `trana-patient-app/app/`.
3. **Google Maps API Key:**
   - Add your Google Maps Platform Android SDK API key to `local.properties` or your app's Manifest/build config.
4. **Build & Run:**
   - Sync Gradle and click **Run** on an Android 12+ physical device or emulator.

---

## 🏗️ Architecture & Modules
- **`ui/sos/`** — One-tap 3-second SOS confirmation button and rapid 3-question triage screen.
- **`ui/tracking/`** — Live ambulance location map with real-time ETA synced from Firebase `/dispatches/{id}`.
- **`data/firebase/`** — Firebase Realtime Database and Phone OTP authentication repository.
