# 🔥 Firebase — Database Schema & Security Rules

This folder contains the **Firebase Realtime Database** configuration shared by all three Trana interfaces:
the Patient App (Kotlin), the Driver/Paramedic App (Kotlin), and the Hospital Command Dashboard (Python).

---

## 📂 Files

| File | Purpose |
|------|---------|
| [`schema.json`](./schema.json) | Reference JSON showing the complete Realtime Database tree structure with sample data |
| [`database.rules.json`](./database.rules.json) | Firebase Realtime Database security rules (read/write access control + indexing) |

---

## 🗄️ Database Tree Structure

```
/
├── users/
│   └── {uid}/
│       ├── name            (String)  — Full name
│       ├── phone           (String)  — Full number with country code, e.g. +919876543210
│       ├── role            (String)  — "patient" | "driver" | "hospital_staff" | "admin"
│       ├── bloodGroup      (String)  — e.g. "O+" (patients only)
│       ├── licenseNumber   (String)  — Driving license (drivers only)
│       └── createdAt       (String)  — ISO 8601 timestamp
│
├── ambulances/
│   └── {ambulanceId}/
│       ├── driverId              (String)  — Ref to /users/{uid}
│       ├── vehicleNumber         (String)  — e.g. "MH-12-EM-4521"
│       ├── type                  (String)  — "BLS" | "ALS"
│       ├── status                (String)  — "ONLINE" | "OFFLINE" | "BUSY" | "SUSPENDED"
│       ├── equipmentVerifiedToday (Boolean) — True if today's checklist was submitted & passed
│       └── currentLocation/
│           ├── lat        (Double)  — Latitude
│           ├── lng        (Double)  — Longitude
│           ├── heading    (Int)     — Bearing in degrees (0-360)
│           └── updatedAt  (String)  — ISO 8601 timestamp
│
├── dispatches/
│   └── {dispatchId}/
│       ├── patientId      (String)  — Ref to /users/{uid}
│       ├── ambulanceId    (String)  — Ref to /ambulances/{id}
│       ├── status         (String)  — "PENDING" | "EN_ROUTE" | "ARRIVED" | "COMPLETED" | "CANCELLED"
│       ├── etaMinutes     (Int)     — Live ETA estimate
│       ├── createdAt      (String)  — ISO 8601 timestamp
│       ├── origin/
│       │   ├── lat        (Double)
│       │   └── lng        (Double)
│       └── triage/
│           ├── isConscious     (Boolean)
│           ├── isBreathing     (Boolean)
│           ├── severeBleeding  (Boolean)
│           └── notes           (String)  — Optional free-text
│
├── hospitals/
│   └── {hospitalId}/
│       ├── name         (String)
│       ├── city         (String)
│       ├── lastUpdated  (String)  — ISO 8601 timestamp
│       ├── location/
│       │   ├── lat      (Double)
│       │   └── lng      (Double)
│       └── beds/
│           ├── er       (Int)  — Available ER beds
│           ├── icu      (Int)  — Available ICU beds
│           └── bls      (Int)  — Available BLS beds
│
└── checklists/
    └── {checklistId}/
        ├── ambulanceId  (String)  — Ref to /ambulances/{id}
        ├── driverId     (String)  — Ref to /users/{uid}
        ├── status       (String)  — "PASSED" | "FAILED"
        ├── verifiedAt   (String)  — ISO 8601 timestamp
        └── items/
            ├── oxygenCylinderFull        (Boolean)
            ├── defibrillatorOperational  (Boolean)
            ├── firstAidKitStocked        (Boolean)
            └── stretcherSecured          (Boolean)
```

---

## 🔒 Security Rules

The rules in `database.rules.json` enforce:

| Collection | Read | Write |
|------------|------|-------|
| `users/{uid}` | Any authenticated user | Only the user themselves **or** an admin |
| `ambulances` | Any authenticated user | Any authenticated user (driver writes their own GPS) |
| `dispatches` | Any authenticated user | Any authenticated user |
| `hospitals` | Any authenticated user | Any authenticated user (hospital staff writes bed counts) |
| `checklists` | Any authenticated user | Any authenticated user |

> ⚠️ **Note:** The current rules allow any authenticated user to write to `ambulances`, `dispatches`, `hospitals`, and `checklists`. These should be tightened in Phase 4 with role-based checks (e.g., only a driver can write their ambulance's GPS, only hospital staff can update bed counts).

### Indexes

The following fields are indexed for efficient query performance:

| Collection | Indexed Fields |
|------------|----------------|
| `ambulances` | `status`, `type` |
| `dispatches` | `status`, `patientId`, `ambulanceId` |
| `hospitals` | `city` |
| `checklists` | `ambulanceId`, `status` |

---

## 🚀 Getting Started with Firebase

### 1. Create a Firebase Project

1. Go to the [Firebase Console](https://console.firebase.google.com/)
2. Click **Add Project** → name it `Trana-Pilot`
3. Enable **Google Analytics** (optional)

### 2. Enable Firebase Services

In your Firebase project:
- **Authentication** → Enable **Phone** sign-in method
- **Realtime Database** → Create a database in **test mode** initially (switch to production rules before pilot)

### 3. Import Security Rules

In the Firebase Console:
1. Go to **Realtime Database → Rules**
2. Paste the contents of `database.rules.json`
3. Click **Publish**

### 4. Load Sample Data

To populate the database with sample data for development:
1. Go to **Realtime Database → Data**
2. Click the three-dot menu → **Import JSON**
3. Import `schema.json`

### 5. Add Apps to Firebase

For each Kotlin Android app:
1. In the Firebase Console, go to **Project Settings → Add App → Android**
2. Use the correct package name:
   - Patient App: `com.trana.patient`
   - Driver App: `com.trana.driver`
3. Download `google-services.json` and place it in the respective `app/` folder

For the Python Desktop App:
1. Go to **Project Settings → Service Accounts**
2. Click **Generate new private key**
3. Save as `firebase_credentials.json` inside `trana-desktop-app/` (**add to `.gitignore`!**)

---

## 📝 Schema Update Process

When the database structure needs to change:

1. Update `schema.json` with the new structure and sample data
2. Update `database.rules.json` if new indexes or access rules are needed
3. Update this `README.md` with the new tree diagram
4. Update `implementation.md` with the revised schema
5. Notify all team members — schema changes affect all three apps simultaneously
