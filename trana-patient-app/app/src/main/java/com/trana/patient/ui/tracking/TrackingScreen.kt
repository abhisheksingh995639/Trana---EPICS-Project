package com.trana.patient.ui.tracking

// TODO: Implement TrackingScreen composable — the live ambulance tracking map.
// Responsibilities:
//   - Full-screen Google Map showing:
//       • Patient location pin (blue)
//       • Ambulance location pin (animated, moving in real time from Firebase)
//       • Route polyline between ambulance and patient
//   - Bottom card overlay showing:
//       • Driver name, vehicle number, and ambulance type (BLS / ALS)
//       • Live ETA countdown (updated every 30 seconds)
//       • "Call Driver" button (triggers CALL_PHONE intent)
//   - Subscribes to Firebase /dispatches/{dispatchId} for status changes
//   - Subscribes to Firebase /ambulances/{ambulanceId}/currentLocation for live GPS
//   - On dispatch status = COMPLETED → show arrival confirmation and navigate to History
