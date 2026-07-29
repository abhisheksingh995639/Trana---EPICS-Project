package com.trana.patient.ui.tracking

/**
 * TrackingScreen — the live ambulance tracking map shown after triage is complete.
 *
 * This is the most technically complex screen in the patient app. It combines
 * Google Maps with two real-time Firebase listeners to show the ambulance moving
 * toward the patient in real time.
 *
 * ## UI Layers
 *
 * ### Full-Screen Google Map
 * - **Patient location pin** (blue) — the patient's GPS position at SOS time
 * - **Ambulance location pin** (red Trana icon) — updates every 3 seconds as the
 *   driver's GPS foreground service pushes to Firebase
 * - **Route polyline** — drawn from ambulance pin to patient pin using the
 *   Google Maps Directions API; redrawn when ambulance moves significantly
 * - The camera follows the ambulance marker with a smooth animation
 *
 * ### Bottom Info Card (Overlay)
 * - Driver name and vehicle number (e.g., "Rajesh Kumar — MH-12-EM-4521")
 * - Ambulance type badge — "Trana BLS" or "Trana ALS" (color-coded)
 * - Live ETA countdown (e.g., "~5 min away") — refreshed every 30 seconds
 * - **"Call Driver"** button — triggers a `tel:` Intent to call the driver's phone number
 *
 * ## Firebase Subscriptions
 * This screen opens **two concurrent** Firebase Realtime listeners via [TrackingViewModel]:
 * 1. `/dispatches/{dispatchId}` — monitors `status` and `etaMinutes` changes
 * 2. `/ambulances/{ambulanceId}/currentLocation` — receives live GPS coordinates
 *
 * Both listeners must be cancelled in [TrackingViewModel.onCleared] to prevent memory leaks.
 *
 * ## Dispatch Completion
 * When the dispatch `status` changes to `"COMPLETED"`:
 * 1. Show an "Ambulance has arrived!" confirmation dialog or overlay
 * 2. Wait 2 seconds, then navigate to [com.trana.patient.ui.history.HistoryScreen]
 * 3. Pop the entire back stack (the user should not be able to go back to triage or home SOS screen)
 *
 * ## Navigation
 * - **Incoming:** From [com.trana.patient.ui.triage.TriageScreen] after triage is submitted
 * - **Outgoing (completed):** To [com.trana.patient.ui.history.HistoryScreen]
 */

// TODO: Implement TrackingScreen composable:
//
// @Composable
// fun TrackingScreen(
//     navController: NavController,
//     dispatchId: String,
//     viewModel: TrackingViewModel = viewModel()
// ) {
//     val dispatchStatus by viewModel.dispatchStatus.collectAsState()
//     val ambulanceLocation by viewModel.ambulanceLocation.collectAsState()
//     val etaMinutes by viewModel.etaMinutes.collectAsState()
//     val driverInfo by viewModel.driverInfo.collectAsState()
//
//     LaunchedEffect(dispatchStatus) {
//         if (dispatchStatus == "COMPLETED") {
//             delay(2000)
//             navController.navigate(Screen.History.route) {
//                 popUpTo(Screen.Home.route) { inclusive = false }
//             }
//         }
//     }
//
//     // Use GoogleMap composable from maps-compose
//     // Add Marker for patient and animated Marker for ambulance
//     // Overlay a BottomSheet or Card with driver info
// }
