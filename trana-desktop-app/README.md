# 🖥️ Trana Desktop App — Hospital Command Dashboard

> **Role:** Hospital Command Dashboard & Admin Panel for real-time emergency queue management and bed availability tracking.  
> **Tech Stack:** Python 3.10+, `pywebview` (native window), `firebase-admin` SDK, HTML5 / Vanilla CSS3 / JavaScript.

---

## 🏗️ Architecture

The desktop application uses a **Python + HTML/CSS/JS** hybrid architecture:

```
trana-desktop-app/
├── main.py              # Python entry point — initializes pywebview window & Firebase Admin SDK
├── requirements.txt     # Python dependencies
└── ui/
    ├── index.html       # Main HTML shell for the dashboard UI
    ├── css/
    │   └── style.css    # Design system — dark mode, emergency red theme, WCAG AA
    └── js/
        └── app.js       # Firebase JS SDK connection, live queue rendering, bed counter logic
```

**How it works:**
- `main.py` launches a native desktop window using `pywebview` that renders the `ui/index.html` page
- The HTML/JS frontend connects **directly** to Firebase Realtime Database via the Firebase JS SDK for live data sync
- Python bridges to the JS frontend via `window.pywebview.api` for any OS-level operations (e.g., file export, notifications)
- Firebase Admin SDK (Python) is used for privileged operations that require server-side auth

---

## 🖥️ Planned Dashboard Sections

| Section | Description |
|---------|-------------|
| **Inbound Emergency Queue** | Live list of incoming ambulances sorted by ETA, color-coded by urgency (Red < 5 min, Yellow 5–15 min, Green > 15 min) |
| **Bed Availability Panel** | Real-time ER / ICU / BLS bed counters with `+` / `−` buttons syncing directly to Firebase `/hospitals/{id}/beds` |
| **Patient Detail Modal** | Full triage answers, paramedic intake form, and live ambulance mini-map for a selected inbound case |
| **Completed Cases Log** | Historical log of all completed dispatches with CSV export |
| **Equipment Audit Summary** | Daily fleet compliance status — which ambulances have / haven't submitted today's checklist |

---

## 🛠️ Setup Instructions

### Prerequisites

- Python 3.10 or later
- `pip` (comes with Python)
- A Firebase project with the Realtime Database enabled (see [`firebase/README.md`](../firebase/README.md))

### 1. Install Python Dependencies

```bash
cd trana-desktop-app
pip install -r requirements.txt
```

### 2. Add Firebase Credentials

1. In the Firebase Console, go to **Project Settings → Service Accounts**
2. Click **Generate new private key** — download the JSON file
3. Rename it to `firebase_credentials.json` and place it in the `trana-desktop-app/` folder
4. **Confirm** `firebase_credentials.json` is listed in `.gitignore` before committing

### 3. Configure Firebase JS SDK (Frontend)

The `ui/js/app.js` file uses the **Firebase JavaScript SDK** for real-time live updates in the browser.

Add your Firebase project's web config object (from **Firebase Console → Project Settings → Your apps → Web app**):

```javascript
// ui/js/app.js
const firebaseConfig = {
  apiKey: "...",
  authDomain: "...",
  databaseURL: "...",
  projectId: "...",
  storageBucket: "...",
  messagingSenderId: "...",
  appId: "..."
};
```

> ⚠️ Never commit real API keys. Use environment variable injection or a `.env` approach for production builds.

### 4. Run the Application

```bash
cd trana-desktop-app
python main.py
```

This will open the Hospital Command Dashboard in a native desktop window.

---

## 🎨 Design System

The dashboard follows a **dark mode, high-contrast emergency theme**:

| Token | Value | Usage |
|-------|-------|-------|
| Emergency Red | `#D32F2F` | Critical alerts, SOS indicators, status badges |
| Background | `#121212` | Primary dark background |
| Surface | `#1E1E1E` | Cards, panels |
| Border | `#2C2C2C` | Dividers, input outlines |
| Text Primary | `#FFFFFF` | Headings, key values |
| Text Secondary | `#B0B0B0` | Labels, metadata |
| Success Green | `#2E7D32` | ONLINE status, passed checklists |
| Warning Yellow | `#F57F17` | Medium ETA, equipment warnings |
| Typography | Inter / Poppins (Google Fonts) | All text |

Accessibility target: **WCAG AA** compliance (contrast ratio ≥ 4.5:1 for normal text).

---

## 📦 Dependencies

| Package | Purpose |
|---------|---------|
| `firebase-admin` | Firebase Realtime Database & Auth (Admin SDK — server-side privileges) |
| `pywebview` | Renders the HTML/CSS/JS UI inside a native OS window |
| `flask` *(optional)* | Local dev HTTP server if needed for the HTML UI (alternative to pywebview) |

> See `requirements.txt` for version-pinned dependencies once implementation begins.

---

## 🔗 Related Docs

- [Firebase Setup & Schema](../firebase/README.md)
- [Technical Architecture](../implementation.md)
- [Full Build Steps](../STEPS.md#phase-7--hospital-command-dashboard)
