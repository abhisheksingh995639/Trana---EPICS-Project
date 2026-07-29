# 🚑 Trana — Technical Implementation Blueprint & Architecture Guide

> **Project:** Trana — Emergency Medical Response & Dispatch Platform  
> **Status:** Phase 0 Complete — Project structure, documentation, and code stubs initialized. Phase 1 (Design & Prototyping) in progress.  
> **Repository Root:** `Trana---EPICS-Project - Copy`

---

## 1. System Architecture Overview

Trana is built as a **real-time, multi-interface emergency dispatch platform** designed to solve three primary failure points in emergency medical response:
1. **Dispatch Delay:** Solved via automated GPS-based nearest-ambulance dispatching with a 20-second acceptance SLA.
2. **Equipment Deficit:** Solved via mandatory daily pre-shift BLS/ALS equipment checklists before going online.
3. **Hospital-Side Bottlenecks:** Solved via live ER/ICU bed tracking and real-time paramedic intake form streaming to receiving hospitals.

```
+-----------------------------------------------------------------------------------+
|                                 CLIENT INTERFACES                                 |
|                                                                                   |
|   +-----------------------+   +------------------------+   +------------------+   |
|   |   Patient Mobile App  |   | Driver / Paramedic App |   | Hospital Command |   |
|   |   (Kotlin - Android)  |   |   (Kotlin - Android)   |   |  (Python + HTML) |   |
|   +-----------+-----------+   +-----------+------------+   +--------+---------+   |
+---------------|---------------------------|-------------------------|-------------+
                |                           |                         |
                | (Firebase Realtime Sync)  | (Firebase Realtime Sync)| (Firebase SDK)
                v                           v                         v
+-----------------------------------------------------------------------------------+
|                            FIREBASE CLOUD INFRASTRUCTURE                          |
|                                                                                   |
|  +--------------------+  +---------------------+  +----------------------------+  |
|  |   Firebase Auth    |  |  Firebase Realtime  |  |  Firebase Cloud Messaging  |  |
|  |   (Phone / OTP)    |  |   Database / NoSQL  |  |   (FCM Push Notifications) |  |
|  +--------------------+  +---------------------+  +----------------------------+  |
|                                                                                   |
|    - /users/{id}              - /hospitals/{id}/beds                              |
|    - /ambulances/{id}         - /checklists/{id}                                  |
|    - /dispatches/{id}         - /live_locations/{id}                              |
+-----------------------------------------------------------------------------------+
```

---

## 2. Directory Structure

```text
Trana---EPICS-Project - Copy/
├── .gitignore                     # Ignore rules for Python, Kotlin/Android, Firebase, and IDEs
├── README.md                      # Project overview and instructions
├── STEPS.md                       # Phase-by-phase execution roadmap
├── implementation.md              # Master technical architecture & implementation blueprint
├── firebase/                      # Firebase Database Configuration & Rules
│   ├── database.rules.json        # Firebase Realtime Database security & read/write rules
│   └── schema.json                # JSON data structure reference for Firebase collections
├── trana-desktop-app/             # Hospital Command Dashboard & Admin (Python + HTML UI)
│   ├── main.py                    # Python desktop app entry point (PyQtWebEngine / Webview + Firebase)
│   ├── requirements.txt           # Python dependencies (firebase-admin, pywebview, etc.)
│   ├── firebase_config.py         # [PLANNED] Firebase Admin SDK initialization & helper queries
│   └── ui/                        # Rich HTML/CSS/JS frontend rendered by Python
│       ├── index.html             # Hospital Command Dashboard UI
│       ├── css/style.css          # Emergency Red theme, dark mode, high contrast WCAG AA
│       └── js/app.js              # Live bed availability & emergency incoming queue logic
├── trana-patient-app/             # Patient SOS Mobile App (Kotlin - Native Android)
│   ├── README.md                  # Android Studio setup & architecture guide
│   └── app/                       # Android app module (Activities, Fragments, ViewModel)
└── trana-driver-app/              # Driver & Paramedic App (Kotlin - Native Android)
    ├── README.md                  # Android Studio setup & architecture guide
    └── app/                       # Android app module (Daily Checklist, GPS Service, Dispatch)
```

---

## 3. Firebase Database JSON Schema Specification

The Firebase Realtime Database tree is structured for ultra-low-latency read/write access and live synchronization across Kotlin mobile apps and the Python desktop dashboard:

```json
{
  "users": {
    "usr_patient_01": {
      "name": "Ananya Sharma",
      "phone": "+919876543210",
      "role": "patient",
      "bloodGroup": "O+",
      "createdAt": "2026-07-29T12:00:00Z"
    }
  },
  "ambulances": {
    "amb_01": {
      "driverId": "usr_driver_101",
      "vehicleNumber": "MH-12-EM-4521",
      "type": "ALS",
      "status": "ONLINE",
      "currentLocation": {
        "lat": 18.5204,
        "lng": 73.8567,
        "heading": 120,
        "updatedAt": "2026-07-29T12:05:00Z"
      },
      "equipmentVerifiedToday": true
    }
  },
  "dispatches": {
    "dsp_001": {
      "patientId": "usr_patient_01",
      "ambulanceId": "amb_01",
      "status": "EN_ROUTE",
      "triage": {
        "isBreathing": true,
        "isConscious": true,
        "severeBleeding": false,
        "notes": "Severe chest pain"
      },
      "origin": { "lat": 18.5250, "lng": 73.8600 },
      "etaMinutes": 5,
      "createdAt": "2026-07-29T12:10:00Z"
    }
  },
  "hospitals": {
    "hosp_01": {
      "name": "Apollo Speciality Hospital",
      "city": "Pune",
      "location": { "lat": 18.5300, "lng": 73.8700 },
      "beds": {
        "er": 6,
        "icu": 3,
        "bls": 12
      },
      "lastUpdated": "2026-07-29T12:15:00Z"
    }
  },
  "checklists": {
    "chk_today_amb_01": {
      "ambulanceId": "amb_01",
      "driverId": "usr_driver_101",
      "status": "PASSED",
      "items": {
        "oxygenCylinderFull": true,
        "defibrillatorOperational": true,
        "firstAidKitStocked": true,
        "stretcherSecured": true
      },
      "verifiedAt": "2026-07-29T06:00:00Z"
    }
  }
}
```

---

## 4. Component-by-Component Specifications

### A. Desktop Application (`trana-desktop-app` — Python + HTML UI)
- **Role:** Hospital Command Dashboard & System Admin.
- **UI Engine:** Built using HTML5, Vanilla CSS3 (high-contrast emergency red `#D32F2F`, sleek dark mode, modern typography), and JS.
- **Python Bridge:** Python host (`main.py`) using `pywebview` or lightweight local web engine to serve the UI and communicate with Firebase Database using `firebase-admin` Python SDK.
- **Key Capabilities:**
  - Real-time inbound ambulance queue with color-coded triage severity badges.
  - One-click ER/ICU/BLS bed availability counters (`+` and `-`) syncing directly to Firebase `/hospitals/{id}/beds`.
  - Live patient intake form display with incoming paramedic vitals.

### B. Patient Mobile Application (`trana-patient-app` — Kotlin / Android)
- **Role:** Patient SOS emergency dispatch & live tracking.
- **Tech Stack:** Native Android (Kotlin), Jetpack Compose / XML layouts, Firebase Auth (Phone OTP), Google Maps Android SDK.
- **Key Capabilities:**
  - One-tap 3-second SOS confirmation button.
  - 3-question rapid triage form (Consciousness, Breathing, Bleeding).
  - Live ambulance map tracking with real-time ETA synced from `/dispatches/{id}`.

### C. Driver / Paramedic Mobile Application (`trana-driver-app` — Kotlin / Android)
- **Role:** Ambulance driver onboarding, daily equipment checklist, and emergency navigation.
- **Tech Stack:** Native Android (Kotlin), Foreground GPS Location Service, Google Maps Directions SDK.
- **Key Capabilities:**
  - Mandatory daily BLS/ALS equipment verification screen before going online (`/checklists`).
  - 20-second incoming emergency alert with high-contrast UI and one-tap *Accept/Decline*.
  - Live GPS telemetry stream pushing coordinates every 3 seconds to Firebase `/ambulances/{id}/currentLocation`.

---

## 5. Next Execution Steps
1. **Firebase Project Setup:** Create a Firebase project in the Firebase Console, download `google-services.json` (for Kotlin Android apps) and `firebase_credentials.json` (for Python desktop app).
2. **Desktop UI Launch:** Run `python main.py` inside `trana-desktop-app/` to preview the Hospital Command Dashboard.
3. **Android Studio Setup:** Open `trana-patient-app/` and `trana-driver-app/` in Android Studio to begin UI building.
