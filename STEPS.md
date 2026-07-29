# 🚑 Trana — Complete Build Steps for the Team

> A step-by-step execution guide for building the Trana emergency response platform.
> Follow phases in order. Each step must be complete before the next begins.
> Mark items with `[x]` as you finish them so teammates stay in sync.
> **Work flexibly — pick up tasks step-by-step as needed during each phase.**

---

## 🟢 Current Status: Phase 0 COMPLETE → Now starting Phase 1

> **What Trana is:** An Uber-like emergency dispatch platform connecting patients, certified ambulance drivers, and hospitals through a unified real-time system. It targets 3 quantifiable failure points in India's emergency response: **dispatch delay**, **equipment deficit**, and **hospital-side bottlenecks**.

---

## 📋 Quick Navigation

- [Phase 0 — Team Setup & Planning](#phase-0--team-setup--planning) ✅
- [Phase 1 — Design & Prototyping](#phase-1--design--prototyping) ← **START HERE**
- [Phase 2 — Backend Foundation](#phase-2--backend-foundation)
- [Phase 3 — Database Design](#phase-3--database-design)
- [Phase 4 — Authentication & User Management](#phase-4--authentication--user-management)
- [Phase 5 — Patient App](#phase-5--patient-app-development)
- [Phase 6 — Driver / Paramedic App](#phase-6--driver--paramedic-app-development)
- [Phase 7 — Hospital Dashboard](#phase-7--hospital-command-dashboard)
- [Phase 8 — Maps & Geolocation Engine](#phase-8--maps--geolocation-engine)
- [Phase 9 — Real-Time Dispatch Engine](#phase-9--real-time-dispatch-engine)
- [Phase 10 — Notifications System](#phase-10--notifications-system)
- [Phase 11 — Equipment Audit System](#phase-11--equipment-audit--verification-system)
- [Phase 12 — Testing](#phase-12--testing)
- [Phase 13 — Deployment & DevOps](#phase-13--deployment--devops)
- [Phase 14 — Pilot Launch Prep](#phase-14--pilot-launch-preparation)
- [Phase 15 — Post-Launch](#phase-15--post-launch--iteration)

---

## 🗓️ Project Timeline (From Proposal)

| Phase | Activities | Timeline |
|-------|-----------|----------|
| **Phase 1** | Research & Prototyping — Finalize UI/UX for all 3 interfaces, establish pilot-city ambulance partnerships | Months 1-2 |
| **Phase 2** | Development & Alpha Testing — Core dispatch, routing algorithms, geolocation, closed beta with dummy data | Months 3-5 |
| **Phase 3** | Pilot Launch — Deploy in 1 high-density urban area, onboard 50-100 verified ambulances, 5-10 partner hospitals | Month 6 |
| **Phase 4** | Optimization & Scaling — Analyze response-time data, city-by-city expansion | Months 7+ |

---

## Phase 0 — Team Setup & Planning ✅

> This phase is COMPLETE.

### 0.1 Team Working Model ✅
- [x] Agreed on flexible, task-based Agile collaboration (no rigid role silos)
- [x] Team members pick up tasks dynamically from this checklist as each phase begins
- [x] Pair programming encouraged on complex features

### 0.2 Set Up Project Management
- [x] Create a GitHub repository for the project (`Trana---EPICS-Project`)
- [x] Add all 5 team members as collaborators (Settings → Collaborators)
- [x] Enable branch protection ruleset on default branch (`Source-Code`) — requires 1 PR approval to merge
- [x] Agree on Git branching strategy: `Source-Code` → feature branches → Pull Requests
- [x] Define code review rules (at least 1 reviewer per PR)
- [ ] Set up a project board (GitHub Projects / Notion / Trello) to track tasks
- [ ] Create a team communication channel (Discord / Slack / WhatsApp group)
- [ ] Schedule weekly sync meetings

### 0.3 Define Tech Stack ✅ (Confirmed from Proposal)

> These decisions are finalized based on the Trana Project Proposal.

- [x] **Mobile framework:** Kotlin (Native Android for Patient App & Driver/Paramedic App)
- [x] **Desktop application:** Python + HTML/CSS/JS UI (Hospital Command Dashboard & Admin)
- [x] **Database & Real-time Backend:** Firebase Database (Realtime Database / Cloud Firestore + Firebase Auth)
- [x] **Maps API:** Google Maps Platform (Android Maps SDK in Kotlin + JS Maps API in Python HTML UI)
- [x] **Notifications:** Firebase Cloud Messaging (FCM) for push + SMS fallback
- [x] **Cloud Hosting:** Firebase Cloud Services (Serverless realtime database, rules, and authentication)
- [x] Document final decisions in `implementation.md` and `README.md`

### 0.4 Set Up Development Environment
- [x] All team members install: Git, VS Code / PyCharm, Android Studio, Python 3.10+, Kotlin SDK
- [x] Set up shared `firebase/` schema and config rules
- [x] Create a shared API key management document (Notion / private Google Doc — **never commit keys to GitHub**)
- [x] Add a `.gitignore` file covering all platforms (Python, Kotlin/Android, Firebase, environment files)

---

## Phase 1 — Design & Prototyping
## ← YOU ARE HERE (Months 1-2)

> Design first, build second. This prevents expensive rework.
> **Goal:** Finalize all UI/UX screens across 3 interfaces before any code is written.

### 1.1 Research & Inspiration
- [ ] Study existing apps: **Uber**, **Ola**, and existing ambulance apps in India (Ziqitza, StanPlus, BVG)
- [ ] Identify UX pain points in emergency situations: panic, poor lighting, stress, gloves on hands
- [ ] Define core design principles:
  - **Simple** — one action per screen, no clutter
  - **Fast** — critical actions reachable in < 2 taps
  - **High-Contrast** — red/white/dark palette, WCAG AA accessible
  - **One-handed usable** — large tap targets, important buttons at thumb reach

### 1.2 Create User Flows
- [ ] Map the **Patient flow:** Open app → Tap SOS (3-second confirm) → Triage questions (3 Qs) → See ambulance on map → Track live ETA → Ambulance arrives
- [ ] Map the **Driver/Paramedic flow:** Open app → Daily checklist → Go Online → Receive alert (20s to accept) → Navigate to patient → Patient intake form → Navigate to hospital → Mark complete
- [ ] Map the **Hospital Staff flow:** Login → Live inbound queue → Click patient card → View intake form + ambulance ETA → Update bed count → Mark patient received
- [ ] Document all edge cases:
  - No ambulances nearby within 10 km
  - Driver declines (auto-advance to next driver)
  - Hospital beds = 0 (skip to next hospital)
  - App crash / network loss mid-dispatch

### 1.3 Wireframes (Lo-Fi) — Sketches or Excalidraw
- [ ] **Patient App screens:**
  - Splash, Onboarding (3 slides), Phone Login, OTP Entry, Profile Setup
  - Home/SOS Screen (large red SOS button, address, history)
  - Triage Form (3 large-button questions)
  - Live Tracking Map (ambulance + patient + ETA + call button)
  - Dispatch History
- [ ] **Driver App screens:**
  - Login, Pending Approval screen
  - Daily Equipment Checklist (required before going online)
  - Home / Online-Offline Toggle
  - Incoming Alert (mini-map + Accept/Decline + 20s countdown)
  - Navigation to Patient
  - Patient Intake Form
  - Navigation to Hospital
- [ ] **Hospital Dashboard screens:**
  - Login
  - Emergency Queue (live list, color-coded by ETA)
  - Patient Detail Modal (intake form + live ambulance map)
  - Bed Availability Panel (ICU + ER with +/- buttons)
  - Completed Cases Log (CSV export)

### 1.4 High-Fidelity Designs (Figma)
- [ ] Set up a shared **Figma** project (free tier is sufficient)
- [ ] Define the **Design System:**
  - [ ] Color palette: Emergency red (#D32F2F), dark bg, white text — WCAG AA compliant
  - [ ] Typography: **Inter** or **Poppins** — bold, clear, readable at glance
  - [ ] Component library: buttons, cards, status badges (Trana BLS / Trana ALS), map overlays, inputs
  - [ ] Icon set: Material Icons (consistent set)
- [ ] Design all **Patient App** screens in Hi-Fi
- [ ] Design all **Driver App** screens in Hi-Fi (dark mode, oversized 56dp tap targets)
- [ ] Design all **Hospital Dashboard** screens in Hi-Fi (desktop + tablet responsive)
- [ ] Team review and sign-off before any coding begins

### 1.5 Prototype & Validate
- [ ] Create a clickable prototype in Figma linking all screens
- [ ] Conduct informal usability tests with 3-5 people outside the team
- [ ] Note feedback and revise designs
- [ ] **Final design sign-off from all team members** → Phase 2 can begin

---

## Phase 2 — Database & Real-time Foundation (Firebase)

> Configure the Firebase Realtime Database and Cloud Firestore that all three apps will communicate with.

### 2.1 Initialize Firebase Project Structure
- [x] Create folder: `firebase/`
- [x] Configure security & indexing rules in `firebase/database.rules.json`
- [x] Define real-time data tree schema in `firebase/schema.json` (`/users`, `/ambulances`, `/dispatches`, `/hospitals`, `/checklists`)
- [ ] Create a project in the Firebase Console and import `schema.json` as starter test data
- [ ] Download `google-services.json` and place it in Kotlin Android app directories


### 2.2 Define All API Routes (On Paper First!)

> Plan these before coding. Change routes on paper, not in code.

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register user (patient, driver, hospital staff) |
| POST | `/api/auth/login` | Login, returns JWT + refresh token |
| POST | `/api/auth/refresh` | Refresh access token |
| POST | `/api/auth/send-otp` | Send 6-digit OTP via SMS |
| POST | `/api/auth/verify-otp` | Validate OTP, mark phone verified |
| POST | `/api/sos` | Patient submits SOS + triage data |
| GET | `/api/ambulances/nearby` | Find nearest online ambulances by lat/lng/radius |
| PATCH | `/api/ambulances/:id/location` | Driver updates GPS location |
| GET | `/api/hospitals/beds` | Get all hospitals with bed counts |
| PATCH | `/api/hospitals/:id/beds` | Hospital updates bed availability |
| GET | `/api/dispatch/:id` | Get full dispatch status |
| PUT | `/api/dispatch/:id/accept` | Driver accepts a dispatch |
| PUT | `/api/dispatch/:id/complete` | Driver marks dispatch complete |
| POST | `/api/checklist` | Driver submits daily equipment checklist |
| GET | `/api/checklist/today` | Check if today's checklist submitted |
| GET | `/api/checklists/history` | Full audit history for an ambulance |
| PATCH | `/api/admin/drivers/:id/verify` | Admin approves/rejects driver |
| PUT | `/api/admin/ambulances/:id/suspend` | Admin suspends an ambulance |

- [ ] Implement centralized error handling middleware
- [ ] Implement request validation middleware (use `zod` or `joi`)
- [ ] Add request logging middleware (morgan)

### 2.3 Configure Environment
- [ ] Set up `.env` with: `PORT`, `DATABASE_URL`, `REDIS_URL`, `JWT_SECRET`, `JWT_REFRESH_SECRET`, `MAPS_API_KEY`, `FCM_KEY`, `TWILIO_SID`, `TWILIO_AUTH_TOKEN`, `TWILIO_FROM`
- [ ] Create `.env.example` (same keys, empty values) and commit it
- [ ] Add `.env` to `.gitignore`

---

## Phase 3 — Database Design

> Design your schema carefully — changes later are very painful.

### 3.1 Design the Schema

| Table | Key Fields |
|-------|-----------|
| **Users** | id, name, phone, email, role (patient/driver/hospital_staff/admin), phone_verified, created_at |
| **Ambulances** | id, driver_id, vehicle_number, type (BLS/ALS), status (available/busy/offline/suspended), last_lat, last_lng, last_checklist_at |
| **Hospitals** | id, name, address, lat, lng, total_icu_beds, available_icu_beds, total_er_beds, available_er_beds, contact_phone |
| **Dispatches** | id, patient_id, ambulance_id, hospital_id, status (pending/dispatched/arrived/completed/cancelled), emergency_type, requested_at, arrived_at, completed_at |
| **Checklists** | id, ambulance_id, driver_id, oxygen_ok, aed_ok, bp_monitor_ok, iv_kit_ok, stretcher_ok, gloves_ppe_ok, submitted_at, notes |
| **LocationHistory** | id, ambulance_id, lat, lng, timestamp |
| **TriageData** | id, dispatch_id, emergency_type (cardiac/trauma/respiratory/other), is_conscious, is_breathing, voice_note_url, notes |
| **PatientIntake** | id, dispatch_id, patient_name, approx_age, observed_symptoms, consciousness, breathing, allergies, submitted_at |

### 3.2 Implement the Database
- [ ] Set up PostgreSQL locally (Docker: `docker run -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres`)
- [ ] Choose ORM: **Prisma** (recommended) — `npm install prisma @prisma/client`
- [ ] Write `schema.prisma` for all tables above
- [ ] Run `npx prisma migrate dev --name init`
- [ ] Verify all tables created correctly in a DB viewer (TablePlus / pgAdmin)
- [ ] Seed with test data: 3 patients, 5 ambulances (mix of BLS and ALS), 2 hospitals
- [ ] Set up **Redis** instance for real-time location caching (`docker run -p 6379:6379 -d redis`)

---

## Phase 4 — Authentication & User Management

### 4.1 Implement JWT Auth
- [ ] `POST /api/auth/register` → hash password with bcrypt, return JWT
- [ ] `POST /api/auth/login` → validate credentials, return JWT + refresh token
- [ ] `POST /api/auth/refresh` → issue new access token using refresh token
- [ ] JWT middleware to protect all private routes
- [ ] Role-based access control (RBAC): patient / driver / hospital_staff / admin roles

### 4.2 Phone OTP Verification
- [ ] Integrate **Twilio** (or Firebase Phone Auth) for OTP-based verification
- [ ] `POST /api/auth/send-otp` → sends 6-digit OTP via SMS
- [ ] `POST /api/auth/verify-otp` → validates OTP, marks phone as verified
- [ ] Require phone verification before user can place an SOS

### 4.3 Driver Onboarding
- [ ] Driver registration requires: name, phone, license number, vehicle number, vehicle type (BLS/ALS)
- [ ] New drivers start as `status: pending_verification`
- [ ] Admin endpoint to approve/reject: `PATCH /api/admin/drivers/:id/verify`
- [ ] Only verified drivers can go online and receive dispatch alerts

### 4.4 Hospital Staff Onboarding
- [ ] Hospital staff registration tied to a specific hospital ID
- [ ] Admin approves hospital accounts before activation

### 4.5 Paramedic Certification Verification (From Proposal)
- [ ] Store medical license number and certification type on driver profile
- [ ] Admin manually validates certifications before approval
- [ ] Certification expiry date tracked — alert admin when near expiry

---

## Phase 5 — Patient App Development

### 5.1 Project Setup
- [ ] Initialize Flutter project: `flutter create trana_patient`
- [ ] Set up folder structure: `/screens`, `/widgets`, `/services`, `/models`, `/providers`, `/utils`
- [ ] Add dependencies: `http`, `riverpod`, `google_maps_flutter`, `geolocator`, `firebase_messaging`, `flutter_secure_storage`, `sentry_flutter`
- [ ] Set up app theme, colors, and fonts from the Figma design system

### 5.2 Splash, Onboarding & Login Screens
- [ ] **Splash Screen:** Logo → redirect based on login state
- [ ] **Onboarding Screens:** 3 slides explaining Trana (shown only on first install)
- [ ] **Phone Login Screen:** Enter phone number → request OTP
- [ ] **OTP Screen:** 6-digit input, auto-read SMS if possible, resend timer
- [ ] **Profile Setup Screen:** Enter name, optional email

### 5.3 Home / SOS Screen
- [ ] Large, prominent **SOS button** (red, center, minimum 100x100 dp — usable in panic)
- [ ] User's current address displayed at top
- [ ] Previous dispatch history (last 3)
- [ ] Emergency contact number display (108 / local)
- [ ] 3-second confirmation dialog on SOS tap (prevent accidental triggers)
- [ ] On confirm: capture GPS → POST SOS to backend immediately

### 5.4 Smart Triage Screen (3-Question System from Proposal)
- [ ] Question 1: Emergency type — **Cardiac / Trauma / Respiratory / Other** (large tap buttons)
- [ ] Question 2: Is the patient conscious? — **Yes / No / Unsure**
- [ ] Question 3: Is the patient breathing? — **Yes / No / Unsure**
- [ ] Optional voice note recording and upload
- [ ] POST triage data to `/api/sos`
- [ ] Show loading state: "Finding nearest ambulance..."
- [ ] Backend uses triage type to prefer ALS ambulance for Cardiac/Respiratory

### 5.5 Live Tracking Screen
- [ ] Google Maps integration
- [ ] Patient location (blue dot) and ambulance location (red icon — labeled Trana BLS or Trana ALS) on map
- [ ] Live route line drawn from ambulance to patient
- [ ] Driver name, vehicle number, ambulance type, live ETA countdown
- [ ] **Call Paramedic** button (direct tel: intent)
- [ ] WebSocket updates for location every 3-5 seconds; animate marker smoothly
- [ ] "Ambulance Arrived" state when status changes to `arrived`

### 5.6 Dispatch History Screen
- [ ] List of past dispatches: date, ambulance, hospital, status
- [ ] Tap to expand for full dispatch details

---

## Phase 6 — Driver / Paramedic App Development

> Must work well with gloves, outdoors, while stressed. Large targets, dark mode.

### 6.1 Project Setup
- [ ] Initialize Flutter project: `flutter create trana_driver`
- [ ] Same dependencies + `flutter_background_service` (for background GPS)
- [ ] **Dark mode UI**, large text, oversized tap targets (minimum 56 dp throughout)

### 6.2 Login, Verification & Status Toggle
- [ ] Driver login (phone + OTP)
- [ ] Show "Pending Approval" screen if driver not yet verified by admin
- [ ] **Go Online / Offline toggle** prominently on home screen (cannot go online without today's checklist)

### 6.3 Daily Equipment Checklist — Required Before Going Online (Proposal Core Feature)
- [ ] Large toggle buttons (Yes/No) for each item:
  - Oxygen Cylinder level OK
  - AED / Defibrillator charged
  - Blood Pressure Monitor present
  - IV Kit & Saline present
  - Stretcher & Spine Board present
  - Gloves, Masks, PPE present
- [ ] Free-text notes field
- [ ] POST to `/api/checklist` with timestamp
- [ ] Driver **cannot go online** until today's checklist is submitted
- [ ] Warning shown (not blocked) if any item is marked "No"
- [ ] This data is the core of the **Equipment Verification Protocol** from the proposal

### 6.4 Incoming Alert Screen
- [ ] Push notification wakes app on new dispatch
- [ ] Alert screen shows: patient location on mini-map, distance, triage summary
- [ ] Large **Accept** and **Decline** buttons
- [ ] **20-second countdown timer** (auto-declines if ignored — next driver gets the alert)
- [ ] On Accept: PUT `/api/dispatch/:id/accept` → status → `dispatched`

### 6.5 Navigation to Patient
- [ ] Full-screen Google Maps with turn-by-turn route to patient
- [ ] Collapsed patient info card at bottom (expandable)
- [ ] "Arrived at Patient" button → opens Patient Intake Form

### 6.6 Patient Intake Form (Forwarded to Hospital Dashboard)
- [ ] Quick fields: patient name (optional), approximate age, observed symptoms, consciousness, breathing, allergies
- [ ] POST data to backend → forwarded live to hospital dashboard
- [ ] After submit: switch navigation to nearest hospital route

### 6.7 Navigation to Hospital (Hospital Selection Algorithm)
- [ ] Backend selects nearest hospital with available_er_beds > 0 or available_icu_beds > 0
- [ ] Show patient summary at bottom of map
- [ ] Driver can see next 2 alternatives and override
- [ ] "Arrived at Hospital" button → dispatch marked `completed`

### 6.8 Background Location Tracking
- [ ] Background service sends GPS every 5 seconds while driver is online
- [ ] POST to `/api/ambulances/:id/location`
- [ ] Prompt user to disable battery optimization for Trana during onboarding

---

## Phase 7 — Hospital Command Dashboard

> Web dashboard for hospital ward staff — desktop and tablet. Built with Next.js.

### 7.1 Project Setup
- [ ] Initialize: `npx create-next-app@latest trana-hospital --typescript`
- [ ] Install: `axios`, `socket.io-client`, `react-query` (or TanStack Query), `react-leaflet` (or Google Maps JS SDK)
- [ ] Responsive layout: desktop primary, tablet (iPad) secondary

### 7.2 Login Screen
- [ ] Hospital staff email + password login
- [ ] On login: fetch this hospital's data, store in context

### 7.3 Emergency Queue Dashboard (Core Proposal Feature: Pre-Arrival Alerts)
- [ ] Live list of incoming ambulances sorted by ETA
- [ ] Each card shows: patient condition, emergency type, ambulance number, driver name, live ETA countdown
- [ ] Color coding by ETA: Red = < 5 min, Yellow = 5-15 min, Green = > 15 min
- [ ] Click card → Patient Detail Modal
- [ ] Live auto-refresh via WebSocket (no manual reload ever needed)

### 7.4 Patient Detail Modal
- [ ] Full patient intake form data (from driver)
- [ ] Ambulance live location on mini-map
- [ ] Projected arrival time
- [ ] Field for internal hospital prep notes

### 7.5 Bed Availability Management Panel (Core Proposal Feature)
- [ ] **ICU Beds:** available / total with + and - buttons
- [ ] **ER Beds:** available / total with + and - buttons
- [ ] One-tap update propagates to backend → all ambulances see updated availability in real-time
- [ ] Warning banner if ICU available = 0
- [ ] "Last updated: X minutes ago" timestamp

### 7.6 Completed Cases Log
- [ ] History tab: all completed dispatches for this hospital
- [ ] Columns: date/time, patient summary, ambulance, response time, outcome
- [ ] CSV export button

---

## Phase 8 — Maps & Geolocation Engine

### 8.1 Google Maps Platform Setup
- [ ] Create Google Cloud project
- [ ] Enable APIs: Maps SDK (Android + iOS), Maps JS API, Directions API, Distance Matrix API, Geocoding API
- [ ] Create API keys with app/domain restrictions
- [ ] Store all keys in `.env` — **never commit to Git**

### 8.2 Ambulance Location Service (Backend)
- [ ] `PATCH /api/ambulances/:id/location` → stores lat/lng in Redis with 30-second TTL
- [ ] `GET /api/ambulances/nearby?lat=&lng=&radius=` → queries active ambulances within radius
- [ ] Implement **Haversine formula** for straight-line distance filtering
- [ ] Use Distance Matrix API for actual driving time on final dispatch decision

### 8.3 Nearest Ambulance Dispatch Algorithm (Core Logic)
When SOS is received:
1. Fetch all `status: available` ambulances within 10 km radius (Haversine)
2. Call Google Distance Matrix API to get actual driving ETA for each
3. For Cardiac/Respiratory triage: **prefer ALS ambulances** (from proposal)
4. Sort by ETA (ascending)
5. Send dispatch alert to closest driver (#1)
6. If declined or no response within 20 seconds → move to next driver
7. Repeat until accepted or no ambulances available → notify patient with fallback 108 number

- [ ] Implement as `DispatchService` class on backend

### 8.4 Hospital Selection Algorithm
When driver submits patient intake form:
1. Get list of hospitals with available_icu_beds > 0 or available_er_beds > 0
2. Use Distance Matrix API from current ambulance location to each qualifying hospital
3. Recommend nearest available hospital
4. Provide next 2 alternatives for driver override

- [ ] Implement as `HospitalSelectionService` class on backend
- [ ] GPS/network fallback: cell-tower triangulation + periodic re-sync (from Risk Mitigation in proposal)

### 8.5 Route Drawing
- [ ] **Patient App:** Directions API to draw route on map; update as ambulance moves; smooth marker animation
- [ ] **Driver App:** Launch Google Maps native navigation for turn-by-turn (deep link)

---

## Phase 9 — Real-Time Dispatch Engine

### 9.1 Set Up WebSockets
- [ ] Install `socket.io` on backend
- [ ] Create namespaces: `/dispatch` and `/hospital`
- [ ] Authenticate socket connections using JWT

### 9.2 Define Socket Events

| Event | Direction | Description |
|-------|-----------|-------------|
| `ambulance:location_update` | Driver → Backend → Patient | Driver emits GPS every 5s |
| `dispatch:status_change` | Backend → All parties | Any status transition |
| `dispatch:new_alert` | Backend → Driver | New SOS assigned to driver |
| `hospital:queue_update` | Backend → Hospital | New incoming ambulance targeted at hospital |
| `hospital:bed_update` | Hospital → Backend → All | Hospital updates bed count |

### 9.3 Location Broadcast
- [ ] Driver opens socket on login → emits `ambulance:location_update { ambulance_id, lat, lng }` every 5s
- [ ] Backend receives → writes to Redis → emits to patient's dispatch room
- [ ] Patient app receives → updates and animates marker on map

### 9.4 Dispatch Rooms
- [ ] On SOS creation: create socket room `dispatch_[id]`
- [ ] Patient, assigned driver, and relevant hospital all join this room
- [ ] All events scoped to that room only (no cross-dispatch leakage)

---

## Phase 10 — Notifications System

### 10.1 Firebase Cloud Messaging (FCM) Setup
- [ ] Create Firebase project
- [ ] Add Android and iOS apps in Firebase console
- [ ] Download and add `google-services.json` (Android) and `GoogleService-Info.plist` (iOS)
- [ ] Integrate `firebase_messaging` Flutter package in both apps
- [ ] Request notification permissions on first launch
- [ ] Save each user's FCM token to backend on login

### 10.2 Backend Notification Service
- [ ] Build `NotificationService.sendPush(userId, title, body, data)`
- [ ] On new SOS: push to nearest driver → "New Emergency Nearby — Accept within 20 seconds"
- [ ] On dispatch accepted: push to patient → "Ambulance is on the way"
- [ ] On ambulance arrived: push to patient → "Ambulance has arrived at your location"
- [ ] On dispatch completed: push to patient → "Dispatch complete. Stay safe."
- [ ] On driver decline: push to next driver in queue

### 10.3 SMS Fallback — Twilio (From Proposal)
- [ ] Integrate Twilio SMS API
- [ ] On dispatch: SMS to patient → "Trana: Ambulance [vehicle] is on the way. ETA: ~X min. Driver: [name] [phone]"
- [ ] When ambulance is 5 minutes from hospital: SMS to hospital contact
- [ ] Fallback if push notification fails (app in background, no internet)

---

## Phase 11 — Equipment Audit & Verification System

> This is the core differentiator from the proposal — solving the 90% equipment deficit.

### 11.1 Checklist Backend
- [ ] `POST /api/checklist` → saves daily checklist with timestamp
- [ ] `GET /api/checklist/today?driver_id=` → today's checklist if submitted
- [ ] `GET /api/checklists/history?ambulance_id=` → full audit history
- [ ] Auto-flag ambulances missing checklist 3+ days in a row (status: flagged)

### 11.2 Admin Audit Panel
- [ ] Simple admin web page with:
  - [ ] All registered ambulances + last checklist date
  - [ ] Red flag on ambulances with missing checklists
  - [ ] Individual checklist history per ambulance
  - [ ] **Suspend / Reactivate** ambulance buttons
  - [ ] All registered drivers with verification status (pending / verified / rejected)
  - [ ] **Approve / Reject** pending driver accounts
  - [ ] Certification expiry tracking

### 11.3 BLS / ALS Classification in Dispatch (From Proposal)
- [ ] Every ambulance must be registered as `type: BLS` or `type: ALS`:
  - **Trana BLS** = Basic Life Support — standard equipment + oxygen
  - **Trana ALS** = Advanced Life Support — ventilators, ECG monitor, specialized paramedics
- [ ] For cardiac/respiratory/severe triage: dispatch algorithm **prefers ALS ambulances**
- [ ] Show ambulance type (Trana BLS / Trana ALS) clearly on patient's tracking screen

### 11.4 Fleet Spot-Audits (From Proposal Risk Mitigation)
- [ ] Admin can mark an ambulance for "Random Physical Spot-Audit Required"
- [ ] System logs audit results (pass/fail)
- [ ] Ambulances that fail spot-audits are automatically suspended until re-verified

---

## Phase 12 — Testing

### 12.1 Unit Testing (Backend)
- [ ] Test Haversine distance calculation
- [ ] Test nearest ambulance selection algorithm (including ALS preference logic)
- [ ] Test hospital selection algorithm (bed availability filter)
- [ ] Test JWT generation and validation
- [ ] Test checklist submission and 3-day auto-flag logic
- [ ] Aim for > 70% test coverage on all business logic

### 12.2 API Integration Testing
- [ ] Create Postman collection covering every endpoint in Phase 2 table
- [ ] Test happy path: full SOS → dispatch → arrive → patient intake → hospital arrive → complete
- [ ] Test error cases: no ambulances, hospital full, driver declines x3, timeout
- [ ] Automate with Newman (Postman CLI) on every PR

### 12.3 Mobile App Testing
- [ ] Test Patient App on low-end Android (2GB RAM, Android 8+), high-end Android, iOS
- [ ] Test Driver App with maximum brightness (outdoor / daylight simulation)
- [ ] Test all tap targets with gloves (minimum 48 dp)
- [ ] Test airplane mode → reconnect recovery for both apps
- [ ] Run full SOS → ambulance arrives → hospital flow end-to-end on 2 real devices

### 12.4 Load Testing (For Pilot Scale: 50-100 Ambulances)
- [ ] Use k6 or Locust to simulate:
  - 50 simultaneous SOS requests
  - 100 ambulances sending location updates every 5 seconds
- [ ] Ensure API response time < 500 ms under pilot load
- [ ] Verify Redis and PostgreSQL performance under load

### 12.5 Security Testing
- [ ] All private routes reject requests without valid JWT
- [ ] Patient cannot access driver or hospital routes (RBAC enforcement)
- [ ] Test for SQL injection on all user-input fields
- [ ] Verify no API keys exposed in app binaries
- [ ] Check no sensitive data logged in production builds

---

## Phase 13 — Deployment & DevOps

### 13.1 Backend Deployment
- [ ] Choose cloud: **Railway** (easiest) or Render
- [ ] Set all production environment variables in cloud dashboard
- [ ] Deploy backend API — verify health-check is live at production URL
- [ ] Deploy Redis (Railway built-in or Upstash free tier)
- [ ] Deploy PostgreSQL (Supabase free tier or Railway)

### 13.2 CI/CD Pipeline (GitHub Actions)
- [ ] On every PR to `Source-Code`: run linting + unit tests + API tests
- [ ] On merge to `Source-Code`: auto-deploy backend to production
- [ ] Add CI status badge to README

### 13.3 Hospital Dashboard Deployment
- [ ] Deploy Next.js dashboard to **Vercel** (free tier)
- [ ] Set production API URL as environment variable in Vercel
- [ ] Test deployed dashboard connects to deployed backend and WebSockets

### 13.4 Mobile App Build
- [ ] Configure both apps with production API URL (not localhost)
- [ ] `flutter build apk --release` for Android
- [ ] Test release APK on a real device
- [ ] Sign APK with a keystore file (store keystore securely — not in Git)
- [ ] Distribute APK via direct download link for pilot (no Play Store needed yet)

### 13.5 Monitoring & Logging
- [ ] Set up **Sentry** (free) for crash reporting in both Flutter apps
- [ ] Set up server logging (Winston for Node.js)
- [ ] Set up **UptimeRobot** (free) to alert team if backend goes down
- [ ] Track basic metrics: SOS per day, average response time

---

## Phase 14 — Pilot Launch Preparation (Month 6)

> Target: 1 high-density urban area, 50-100 verified ambulances, 5-10 partner hospitals.

### 14.1 Fleet Onboarding
- [ ] Identify local ambulance operators willing to pilot
- [ ] Walk each driver through app installation and onboarding
- [ ] Manually verify each driver's license and vehicle documents
- [ ] Classify each ambulance as **Trana BLS** or **Trana ALS** in the system
- [ ] Conduct practice dispatch runs with each driver before going live
- [ ] Confirm commission incentive structure for drivers and operators

### 14.2 Hospital Onboarding
- [ ] Identify 5-10 partner hospitals
- [ ] Visit each hospital, train ward staff on the dashboard
- [ ] Set up hospital accounts in the system
- [ ] Designate one staff member as the "Trana Bed Update Owner" per hospital
- [ ] Run a mock incoming dispatch drill with each hospital
- [ ] Confirm escalation contact at each hospital if dashboard fails

### 14.3 Pilot Readiness Checklist
- [ ] All driver accounts created and verified
- [ ] All hospital accounts created and configured
- [ ] Backend stable — no crashes for 48 hours in staging
- [ ] Both apps tested on actual driver devices
- [ ] Dashboard tested on actual hospital devices (tablets/PCs)
- [ ] **Emergency fallback plan** documented: if app crashes → direct 108 call instructions
- [ ] Team has a dedicated support channel active during pilot hours

### 14.4 Soft Launch (Controlled Simulation)
- [ ] Run: 1 fake SOS → dispatch → navigate → hospital with real devices
- [ ] Repeat with 3 drivers and 2 hospitals simultaneously
- [ ] Fix any last-minute issues before announcing
- [ ] Announce pilot to a small, controlled group of test users

---

## Phase 15 — Post-Launch & Iteration (Months 7+)

### 15.1 Key Metrics to Track (From Proposal Objectives)
- [ ] Average time: SOS submitted → ambulance dispatched (target: **< 2 minutes**)
- [ ] Average time: dispatch → ambulance arrival (track vs. pre-Trana baseline)
- [ ] % of dispatched ambulances with submitted equipment checklist that day (target: **100%**)
- [ ] % of hospital bed data updated within last 30 minutes
- [ ] Number of dispatches per day / per week
- [ ] Number of driver declines per dispatch (flag if > 2 per SOS)

### 15.2 Feedback Collection
- [ ] In-app rating screen after each dispatch (1-5 stars, optional comment)
- [ ] Driver interviews after first 2 weeks
- [ ] Hospital staff interviews — is the dashboard useful?

### 15.3 Iteration Cadence
- [ ] Weekly review meeting: metrics + feedback
- [ ] Prioritize top 3 improvements each week
- [ ] Ship a new app update every 2 weeks during pilot

### 15.4 Scaling Preparation (Months 7+)
- [ ] Write a scaling report: response time improvements, equipment compliance rates, lessons learned
- [ ] Use report to approach additional cities, NGOs, or government partners
- [ ] Plan multi-language support (Hindi + regional languages)
- [ ] Explore **108 / 112 government helpline integration** (requires formal partnership — noted as out-of-scope for Phase 1 in proposal)

---

## 📎 Useful Resources

| Resource | Link | Purpose |
|----------|------|---------|
| Flutter Docs | https://flutter.dev/docs | Mobile app development |
| Google Maps Flutter | https://pub.dev/packages/google_maps_flutter | Map integration |
| Geolocator Package | https://pub.dev/packages/geolocator | GPS in Flutter |
| Firebase Messaging | https://pub.dev/packages/firebase_messaging | Push notifications |
| Socket.IO Docs | https://socket.io/docs/ | Real-time communication |
| Prisma ORM | https://www.prisma.io/ | Database ORM |
| Twilio SMS | https://www.twilio.com/ | SMS notifications |
| Figma | https://figma.com | UI/UX design (free) |
| Railway | https://railway.app | Easy backend + DB hosting |
| Vercel | https://vercel.com | Easy frontend hosting |
| Sentry | https://sentry.io | Crash monitoring |
| k6 Load Testing | https://k6.io | Backend load testing |
| Supabase | https://supabase.com | Free PostgreSQL hosting |
| UptimeRobot | https://uptimerobot.com | Uptime monitoring (free) |
| Zod (validation) | https://zod.dev | API request validation |
| Postman | https://www.postman.com | API testing |

---

> **💡 Team Tip:** This file is a living checklist. As each step is done, replace `- [ ]` with `- [x]` and commit the change to your feature branch. Everyone on the team can see real-time progress just by opening this file on GitHub. **Work flexibly and communicate in your team group before starting a task.**
>
> **Git Reminder:** Always work on a feature branch. Never push directly to `Source-Code`. Open a PR and get 1 approval before merging.
