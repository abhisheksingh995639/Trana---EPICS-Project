# 🛠️ Trana — Complete Build Steps for the Team

> A step-by-step execution guide for building the Trana emergency response platform.
> Follow phases in order. Each step must be complete before the next begins.
> Mark items with ✅ as you finish them so teammates stay in sync.

---

## 📋 Quick Navigation

- [Phase 0 — Team Setup & Planning](#phase-0--team-setup--planning)
- [Phase 1 — Design & Prototyping](#phase-1--design--prototyping)
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

## Phase 0 — Team Setup & Planning

> Do this before writing a single line of code.

### 0.1 Assign Roles
- [ ] Designate a **Project Lead** (manages timeline, meetings, blockers)
- [ ] Assign **Frontend Developers** (Patient App, Driver App)
- [ ] Assign **Backend Developer(s)** (API, dispatch engine, DB)
- [ ] Assign **UI/UX Designer** (wireframes, design system)
- [ ] Assign **DevOps / Cloud** person (hosting, CI/CD)
- [ ] Assign **QA / Tester** (test plans, bug tracking)

### 0.2 Set Up Project Management
- [ ] Create a GitHub repository (or GitLab) for the project
- [ ] Set up a project board (GitHub Projects / Notion / Trello) to track tasks
- [ ] Create a team communication channel (Discord / Slack / WhatsApp group)
- [ ] Schedule weekly sync meetings
- [ ] Agree on a Git branching strategy (e.g., `main` → `dev` → feature branches)
- [ ] Define code review rules (at least 1 reviewer per PR)

### 0.3 Define Tech Stack (Confirm These Choices as a Team)
- [ ] Decide on mobile framework: **Flutter** (recommended) or React Native
- [ ] Decide on backend language: **Node.js + Express** or Django or FastAPI
- [ ] Decide on database: **PostgreSQL** (main) + **Firebase Realtime DB / Redis** (live location)
- [ ] Decide on maps API: **Google Maps Platform** (recommended) or MapBox
- [ ] Decide on cloud: **Firebase** (easiest for beginners) or AWS / GCP
- [ ] Decide on notification service: **Firebase Cloud Messaging (FCM)** + Twilio (SMS)
- [ ] Document final decisions in a `TECH_STACK.md` file

### 0.4 Set Up Development Environment
- [ ] All team members install: Git, VS Code, Flutter SDK / Node.js
- [ ] Set up shared `.env.example` file listing all required environment variables
- [ ] Create a shared API key management document (never commit keys to GitHub)
- [ ] Add a `.gitignore` file covering all platforms (Flutter, Node, environment files)

---

## Phase 1 — Design & Prototyping

> Design first, build second. This prevents rework.

### 1.1 Research & Inspiration
- [ ] Study existing apps: **Uber**, **Ola**, and existing ambulance apps (e.g., Ziqitza, StanPlus)
- [ ] Identify UX pain points specific to emergency situations (panic, poor lighting, stress)
- [ ] Define core design principles: **Simple, Fast, High-Contrast, One-handed usable**

### 1.2 Create User Flows
- [ ] Map the **Patient flow**: Open app → Tap SOS → Triage questions → See ambulance on map → Ambulance arrives
- [ ] Map the **Driver flow**: Receive alert → Accept job → Navigate to patient → Navigate to hospital → Mark complete
- [ ] Map the **Hospital flow**: See incoming queue → View patient details → Update bed availability → Mark patient received
- [ ] Document all edge cases (no ambulance nearby, driver declines, hospital full)

### 1.3 Wireframes (Lo-Fi)
- [ ] Sketch wireframes for all screens in the **Patient App** (pen/paper or Excalidraw)
  - Splash, Login, OTP, Home/SOS, Triage form, Live tracking, History
- [ ] Sketch wireframes for all screens in the **Driver App**
  - Login, Dashboard, Incoming alert, Navigation, Checklist, Patient intake form
- [ ] Sketch wireframes for the **Hospital Dashboard**
  - Login, Emergency queue, Patient detail, Bed management panel

### 1.4 High-Fidelity Designs (Hi-Fi)
- [ ] Set up a shared **Figma** (free) project
- [ ] Define the design system:
  - [ ] Color palette (red/white/dark emergency theme — WCAG AA accessibility)
  - [ ] Typography (clear, bold — e.g., Inter or Poppins)
  - [ ] Component library (buttons, cards, inputs, map overlays, status badges)
  - [ ] Icon set (consistent — e.g., Material Icons)
- [ ] Design all Patient App screens in Hi-Fi
- [ ] Design all Driver App screens in Hi-Fi
- [ ] Design all Hospital Dashboard screens in Hi-Fi
- [ ] Get team review and sign-off before any coding begins

### 1.5 Prototype & Validate
- [ ] Create a clickable prototype in Figma
- [ ] Conduct informal usability tests with 3–5 people outside the team
- [ ] Note feedback and revise designs accordingly
- [ ] **Final design sign-off** from the team before Phase 2

---

## Phase 2 — Backend Foundation

> Build the API server that all apps will communicate with.

### 2.1 Initialize the Backend Project
- [ ] Create a new Node.js project (`npm init`) or equivalent
- [ ] Set up folder structure: `/routes`, `/controllers`, `/models`, `/middleware`, `/services`, `/utils`, `/config`
- [ ] Install core dependencies: Express, dotenv, cors, helmet, morgan
- [ ] Set up a health-check endpoint: `GET /api/health` → `{ status: "ok" }`
- [ ] Verify health-check works locally

### 2.2 Set Up API Structure
- [ ] Define all API routes on paper before coding (RESTful conventions):
  - `POST /api/auth/register`, `POST /api/auth/login`, `POST /api/auth/refresh`
  - `POST /api/sos` — patient sends SOS
  - `GET /api/ambulances/nearby` — find nearest ambulances
  - `PATCH /api/ambulances/:id/location` — driver updates location
  - `GET /api/hospitals/beds`, `PATCH /api/hospitals/:id/beds`
  - `GET /api/dispatch/:id` — get dispatch status
  - `POST /api/checklist` — driver submits equipment checklist
- [ ] Implement centralized error handling middleware
- [ ] Implement request validation middleware (use `joi` or `zod`)
- [ ] Add request logging middleware

### 2.3 Configure Environment
- [ ] Set up `.env` with: `PORT`, `DATABASE_URL`, `JWT_SECRET`, `MAPS_API_KEY`, `FCM_KEY`, etc.
- [ ] Never hardcode secrets in code
- [ ] Document all variables in `.env.example`

---

## Phase 3 — Database Design

> Design your schema carefully — changes later are painful.

### 3.1 Design the Schema
- [ ] **Users**: id, name, phone, email, role (patient/driver/hospital_staff), verified, created_at
- [ ] **Ambulances**: id, driver_id, vehicle_number, type (BLS/ALS), status (available/busy/offline/suspended), last_lat, last_lng, last_checklist_at
- [ ] **Hospitals**: id, name, address, lat, lng, total_icu_beds, available_icu_beds, total_er_beds, available_er_beds, contact_phone
- [ ] **Dispatches**: id, patient_id, ambulance_id, hospital_id, status, emergency_type, requested_at, arrived_at, completed_at
- [ ] **Checklists**: id, ambulance_id, driver_id, oxygen_ok, aed_ok, bp_monitor_ok, iv_kit_ok, stretcher_ok, submitted_at, notes
- [ ] **LocationHistory**: id, ambulance_id, lat, lng, timestamp
- [ ] **TriageData**: id, dispatch_id, emergency_type, is_conscious, is_breathing, notes

### 3.2 Implement the Database
- [ ] Set up PostgreSQL locally (Docker: `docker run -p 5432:5432 postgres`)
- [ ] Choose ORM: **Prisma** (recommended) or Sequelize
- [ ] Write migration files for all tables
- [ ] Run migrations and verify all tables created correctly
- [ ] Seed with test data: 3 patients, 5 ambulances, 2 hospitals
- [ ] Set up **Redis** instance for real-time location caching

---

## Phase 4 — Authentication & User Management

### 4.1 Implement JWT Auth
- [ ] `POST /api/auth/register` → hash password with bcrypt, return JWT
- [ ] `POST /api/auth/login` → validate credentials, return JWT + refresh token
- [ ] `POST /api/auth/refresh` → issue new access token using refresh token
- [ ] JWT middleware to protect all private routes
- [ ] Role-based access control (RBAC): patient / driver / hospital / admin roles

### 4.2 Phone OTP Verification
- [ ] Integrate Twilio or Firebase Phone Auth for OTP-based verification
- [ ] `POST /api/auth/send-otp` → sends 6-digit OTP via SMS
- [ ] `POST /api/auth/verify-otp` → validates OTP, marks phone as verified
- [ ] Require phone verification before user can place an SOS

### 4.3 Driver Onboarding
- [ ] Driver registration with: name, phone, license number, vehicle number, vehicle type (BLS/ALS)
- [ ] New drivers start as `status: pending_verification`
- [ ] Admin endpoint to approve/reject drivers: `PATCH /api/admin/drivers/:id/verify`
- [ ] Only verified drivers can receive dispatch alerts

### 4.4 Hospital Staff Onboarding
- [ ] Hospital staff registration tied to a specific hospital ID
- [ ] Admin approves hospital accounts before activation

---

## Phase 5 — Patient App Development

### 5.1 Project Setup
- [ ] Initialize Flutter project: `flutter create trana_patient`
- [ ] Set up folder structure: `/screens`, `/widgets`, `/services`, `/models`, `/providers`, `/utils`
- [ ] Add dependencies: `http`, `provider`/`riverpod`, `google_maps_flutter`, `geolocator`, `firebase_messaging`, `flutter_secure_storage`
- [ ] Set up app theme, colors, and fonts from Figma design system

### 5.2 Splash, Onboarding & Login Screens
- [ ] **Splash Screen**: Logo display → redirect based on login state
- [ ] **Onboarding Screens**: 3 slides explaining Trana (shown only on first launch)
- [ ] **Phone Login Screen**: Enter phone number → request OTP
- [ ] **OTP Screen**: 6-digit input, auto-read SMS if possible, resend timer
- [ ] **Profile Setup Screen**: Enter name, optional email

### 5.3 Home / SOS Screen
- [ ] Large prominent **SOS button** (red, center, minimum 100x100dp — easy in panic)
- [ ] User's current address at the top
- [ ] Previous dispatch history (last 3)
- [ ] Emergency contact number display (108 / local)
- [ ] 3-second confirmation dialog on SOS tap (prevent accidental triggers)
- [ ] On confirm: capture GPS and POST SOS to backend immediately

### 5.4 Smart Triage Screen
- [ ] 3-question triage form with large tap buttons:
  1. Emergency type (Cardiac / Trauma / Respiratory / Other)
  2. Is the patient conscious? (Yes / No / Unsure)
  3. Is the patient breathing? (Yes / No / Unsure)
- [ ] Optional voice note recording and upload
- [ ] POST triage data to `/api/sos`
- [ ] Show loading state: "Finding nearest ambulance..."

### 5.5 Live Tracking Screen
- [ ] Google Maps integration
- [ ] Patient location (blue dot) and ambulance location (red icon) on map
- [ ] Live route line drawn from ambulance to patient
- [ ] Driver name, ambulance type (BLS/ALS), vehicle number, live ETA
- [ ] **Call Paramedic** button (direct tel: intent)
- [ ] Poll or WebSocket for location updates every 3–5 seconds
- [ ] "Ambulance Arrived" state when status changes to `arrived`

### 5.6 Dispatch History Screen
- [ ] List of past dispatches: date, ambulance, hospital, status
- [ ] Tap to expand for full dispatch details

---

## Phase 6 — Driver / Paramedic App Development

> Must work well with gloves, outdoors, while stressed.

### 6.1 Project Setup
- [ ] Initialize Flutter project: `flutter create trana_driver`
- [ ] Same dependencies + `flutter_background_service` for background location
- [ ] Dark mode UI, large text, oversized tap targets (minimum 56dp)

### 6.2 Login, Verification & Status Toggle
- [ ] Driver login (phone + OTP)
- [ ] Show "Pending Approval" screen if driver not yet verified
- [ ] **Go Online / Offline toggle** prominently on home screen

### 6.3 Daily Equipment Checklist (Required Before Going Online)
- [ ] Checklist items with Yes/No toggle for each:
  - Oxygen Cylinder level OK
  - AED / Defibrillator charged
  - Blood Pressure Monitor present
  - IV Kit & Saline present
  - Stretcher & Spine Board present
  - Gloves, Masks, PPE present
- [ ] Free-text notes field
- [ ] POST to `/api/checklist` with timestamp
- [ ] Driver cannot go online until today's checklist is submitted
- [ ] Warning shown (not blocked) if any item is marked "No"

### 6.4 Incoming Alert Screen
- [ ] Push notification wakes app on new dispatch
- [ ] Alert screen shows: patient location on mini-map, distance, triage info
- [ ] Large **Accept** and **Decline** buttons
- [ ] 20-second countdown timer (auto-declines if ignored)
- [ ] On Accept: PUT `/api/dispatch/:id/accept`, status → `dispatched`

### 6.5 Navigation to Patient
- [ ] Full-screen Google Maps with turn-by-turn to patient location
- [ ] Collapsed patient info card at bottom (expandable)
- [ ] "Arrived at Patient" button → opens Patient Intake Form

### 6.6 Patient Intake Form
- [ ] Quick fields: patient name (optional), approximate age, observed symptoms, consciousness, breathing, allergies
- [ ] POST data to backend → forwarded to hospital dashboard
- [ ] After submit: switch navigation to route to hospital

### 6.7 Navigation to Hospital
- [ ] Route to auto-selected nearest hospital with available beds
- [ ] Show patient summary at bottom
- [ ] "Arrived at Hospital" button → dispatch marked `completed`

### 6.8 Background Location Tracking
- [ ] Background service sends GPS every 5 seconds while driver is online
- [ ] POST to `/api/ambulances/:id/location`
- [ ] Handle battery optimization (prompt user to disable battery saver for Trana)

---

## Phase 7 — Hospital Command Dashboard

> Web dashboard for hospital ward staff — desktop and tablet.

### 7.1 Project Setup
- [ ] Initialize: `npx create-next-app trana-hospital`
- [ ] Install: `axios`, `socket.io-client`, `react-query`, `react-leaflet` or Google Maps JS SDK
- [ ] Responsive layout (desktop + tablet / iPad)

### 7.2 Login Screen
- [ ] Hospital staff email + password login
- [ ] On login: fetch this hospital's data, store in context

### 7.3 Emergency Queue Dashboard
- [ ] Live list of incoming ambulances sorted by ETA
- [ ] Each card: patient condition, emergency type, ambulance number, driver name, live ETA countdown
- [ ] Color coding: Red (< 5 min), Yellow (5–15 min), Green (> 15 min)
- [ ] Click card → Patient Detail Modal
- [ ] Live auto-refresh via WebSocket (no manual reload needed)

### 7.4 Patient Detail Modal
- [ ] Full patient intake form data from driver
- [ ] Ambulance live location on mini-map
- [ ] Projected arrival time
- [ ] Field for internal hospital prep notes

### 7.5 Bed Availability Management Panel
- [ ] ICU Beds: `available / total` with `+` and `−` buttons
- [ ] ER Beds: `available / total` with `+` and `−` buttons
- [ ] One-tap update propagates to backend and ambulances in real-time
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
- [ ] Store all keys in `.env` — never commit to Git

### 8.2 Ambulance Location Service (Backend)
- [ ] `PATCH /api/ambulances/:id/location` → stores lat/lng in Redis
- [ ] `GET /api/ambulances/nearby?lat=&lng=&radius=` → queries active ambulances within radius
- [ ] Implement **Haversine formula** for straight-line distance filtering
- [ ] Use Distance Matrix API for actual driving time on final dispatch decision

### 8.3 Nearest Ambulance Dispatch Algorithm
When SOS is received:
1. Fetch all online, available ambulances within 10 km radius
2. Call Google Distance Matrix API to get actual driving ETA for each
3. Sort by ETA (ascending)
4. Send dispatch alert to #1 closest driver
5. If declined within 20 seconds → move to next driver
6. Repeat until accepted or no ambulances available (notify patient)

- [ ] Implement this as a `DispatchService` on the backend

### 8.4 Hospital Selection Algorithm
When driver submits patient intake form:
1. Get list of hospitals with `available_icu_beds > 0` or `available_er_beds > 0`
2. Use Distance Matrix API from current ambulance location to each hospital
3. Recommend nearest hospital with available beds
4. Allow driver to override (show next 2 options)

- [ ] Implement this as a `HospitalSelectionService`

### 8.5 Route Drawing
- [ ] Patient App: use Directions API to draw route on map, update as ambulance moves
- [ ] Driver App: launch Google Maps native navigation for turn-by-turn
- [ ] Animate ambulance marker smoothly on patient's map (interpolate between coordinate updates)

---

## Phase 9 — Real-Time Dispatch Engine

### 9.1 Set Up WebSockets
- [ ] Install `socket.io` on backend
- [ ] Create namespaces: `/dispatch` and `/hospital`
- [ ] Authenticate socket connections using JWT

### 9.2 Define Socket Events
- [ ] `ambulance:location_update` — driver emits every 5s; patient receives
- [ ] `dispatch:status_change` — backend emits on status changes
- [ ] `dispatch:new_alert` — backend emits to nearby driver on new SOS
- [ ] `hospital:queue_update` — backend emits to hospital when new dispatch targets it
- [ ] `hospital:bed_update` — hospital emits bed changes; backend broadcasts

### 9.3 Location Broadcast
- [ ] Driver opens socket on login
- [ ] Emit `ambulance:location_update { ambulance_id, lat, lng }` every 5 seconds
- [ ] Backend receives → writes to Redis → emits to patient's dispatch room
- [ ] Patient app receives → updates and animates marker on map

### 9.4 Dispatch Rooms
- [ ] On SOS creation: create a socket room named `dispatch_[id]`
- [ ] Patient, assigned driver, and relevant hospital all join this room
- [ ] All events (location, status) scoped to that room only

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
- [ ] On new SOS: push to nearest driver — "New Emergency Nearby"
- [ ] On dispatch accepted: push to patient — "Ambulance is on the way"
- [ ] On ambulance arrived: push to patient — "Ambulance has arrived"
- [ ] On dispatch completed: push to patient — "Dispatch complete. Stay safe."
- [ ] On driver decline: push to next driver in queue

### 10.3 SMS Fallback (Twilio)
- [ ] Integrate Twilio SMS API
- [ ] Send SMS to patient when ambulance is dispatched (backup to push)
- [ ] SMS: "Trana: Ambulance [vehicle] is on the way. ETA: ~X min. Driver: [name] [phone]"
- [ ] Send SMS to hospital when ambulance is 5 minutes away

---

## Phase 11 — Equipment Audit & Verification System

### 11.1 Checklist Backend
- [ ] `POST /api/checklist` → saves daily checklist with timestamp
- [ ] `GET /api/checklist/today?driver_id=` → today's checklist if submitted
- [ ] `GET /api/checklists/history?ambulance_id=` → full audit history
- [ ] Auto-flag ambulances skipping checklist 3+ days in a row

### 11.2 Admin Audit Panel
- [ ] Simple admin web page with:
  - [ ] All registered ambulances + last checklist date
  - [ ] Red flag on ambulances with missing checklists
  - [ ] Individual checklist history per ambulance
  - [ ] Suspend / Reactivate ambulance buttons
  - [ ] All registered drivers with verification status
  - [ ] Approve / Reject pending driver accounts

### 11.3 BLS / ALS Classification in Dispatch
- [ ] Ambulance registration must include `type: BLS | ALS`
- [ ] For cardiac/respiratory/severe triage: dispatch algorithm prefers ALS ambulances
- [ ] Show ambulance type clearly on patient tracking screen

---

## Phase 12 — Testing

### 12.1 Unit Testing (Backend)
- [ ] Test Haversine distance calculation
- [ ] Test nearest ambulance selection algorithm
- [ ] Test hospital selection algorithm
- [ ] Test JWT generation and validation
- [ ] Test checklist submission logic
- [ ] Aim for >70% test coverage on business logic

### 12.2 API Integration Testing
- [ ] Create Postman collection covering every endpoint
- [ ] Test happy paths: full SOS → dispatch → arrive → complete flow
- [ ] Test error cases: no ambulances, hospital full, driver declines, timeout
- [ ] Automate with Newman (Postman CLI) on every PR

### 12.3 Mobile App Testing
- [ ] Test Patient App on low-end Android (2GB RAM, Android 8+), high-end Android, iOS
- [ ] Test Driver App with maximum brightness (outdoor / daylight simulation)
- [ ] Test all tap targets with gloves (minimum 48dp)
- [ ] Test airplane mode → reconnect recovery
- [ ] Run full SOS → ambulance arrives flow end-to-end on 2 real phones

### 12.4 Load Testing
- [ ] Use k6 or Locust to simulate:
  - 50 simultaneous SOS requests
  - 100 ambulances sending location every 5 seconds
- [ ] Ensure API response time < 500ms under pilot load
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
- [ ] Choose cloud: **Railway / Render** (easiest) or AWS EC2 / GCP Cloud Run
- [ ] Set all production environment variables
- [ ] Deploy backend API — verify health-check is live
- [ ] Deploy Redis (Railway built-in or Upstash free tier)
- [ ] Deploy PostgreSQL (Supabase or Railway)

### 13.2 CI/CD Pipeline (GitHub Actions)
- [ ] On every PR: run linting + unit tests + API tests
- [ ] On merge to `main`: auto-deploy backend to production
- [ ] Add CI status badge to README

### 13.3 Hospital Dashboard Deployment
- [ ] Deploy React/Next.js dashboard to **Vercel** (free)
- [ ] Set production API URL as environment variable
- [ ] Test deployed dashboard connects to deployed backend

### 13.4 Mobile App Build
- [ ] Configure both apps with production API URL (not localhost)
- [ ] `flutter build apk --release` for Android
- [ ] Test release APK on a real device
- [ ] Sign APK with a keystore
- [ ] Distribute APK via direct link for pilot (no Play Store needed yet)

### 13.5 Monitoring & Logging
- [ ] Set up **Sentry** (free) for crash reporting in both Flutter apps
- [ ] Set up server logging (Winston for Node.js)
- [ ] Set up **UptimeRobot** (free) to alert team if backend goes down
- [ ] Track basic metrics: SOS per day, average response time

---

## Phase 14 — Pilot Launch Preparation

### 14.1 Fleet Onboarding
- [ ] Identify local ambulance operators willing to pilot
- [ ] Walk each driver through app installation and onboarding
- [ ] Manually verify each driver's license and vehicle documents
- [ ] Conduct practice runs with each driver before going live
- [ ] Classify each ambulance as BLS or ALS in the system

### 14.2 Hospital Onboarding
- [ ] Identify 2–5 partner hospitals
- [ ] Visit each hospital, train ward staff on the dashboard
- [ ] Set up hospital accounts in the system
- [ ] Designate one ward staff member as the "Trana bed update owner" per hospital
- [ ] Run a mock incoming dispatch drill with each hospital

### 14.3 Pilot Readiness Checklist
- [ ] All driver accounts created and verified
- [ ] All hospital accounts created and configured
- [ ] Backend stable — no crashes for 48 hours in staging
- [ ] Both apps tested on actual driver devices
- [ ] Dashboard tested on actual hospital devices (tablets/PCs)
- [ ] Emergency fallback plan if app crashes (direct 108 call)
- [ ] Team has a dedicated support channel for the pilot

### 14.4 Soft Launch
- [ ] Run a controlled simulation: 1 fake SOS → dispatch → navigate → hospital
- [ ] Do this with 3 drivers and 2 hospitals simultaneously
- [ ] Fix any last-minute issues
- [ ] Announce pilot to a small controlled group of test users

---

## Phase 15 — Post-Launch & Iteration

### 15.1 Key Metrics to Track
- [ ] Average time: SOS submitted → ambulance dispatched (target: < 2 min)
- [ ] Average time: dispatch → ambulance arrival (track vs. baseline)
- [ ] % of dispatched ambulances with a submitted checklist that day
- [ ] % of hospital bed data updated within last 30 minutes
- [ ] Number of dispatches per day / week
- [ ] Number of driver declines per dispatch

### 15.2 Feedback Collection
- [ ] In-app rating screen after each dispatch (1–5 stars, optional comment)
- [ ] Interviews with drivers after first 2 weeks
- [ ] Interviews with hospital staff — is the dashboard useful?

### 15.3 Iteration Cadence
- [ ] Weekly review meeting: go through metrics + feedback
- [ ] Prioritize top 3 improvements each week
- [ ] Ship a new app update every 2 weeks during pilot

### 15.4 Scaling Preparation (Months 7+)
- [ ] Write a scaling report: response time improvements, equipment compliance rates, lessons learned
- [ ] Use report to approach additional cities, NGOs, or government partners
- [ ] Plan multi-language support (Hindi + regional language)
- [ ] Explore 108 / 112 government helpline integration (requires formal partnership)

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

---

> **💡 Team Tip:** This file is a living checklist. As each step is done, replace `- [ ]` with `- [x]` and commit the change. Everyone on the team can see real-time progress just by opening this file on GitHub. Assign your name next to items you own.
