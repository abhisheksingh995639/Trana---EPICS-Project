package com.trana.patient.ui.history

/**
 * HistoryScreen — displays a chronological log of the patient's past emergency dispatches.
 *
 * This screen gives patients visibility into their emergency history, which can be useful
 * for medical records, insurance claims, and personal reference.
 *
 * ## UI Elements
 * - Scrollable list of past dispatch cards (newest at top)
 * - Each card shows:
 *   - Date and time (e.g., "29 Jul 2026, 12:10 PM")
 *   - Ambulance type badge — "Trana BLS" or "Trana ALS"
 *   - Hospital name (e.g., "Apollo Speciality Hospital")
 *   - Status chip — "COMPLETED" (green) or "CANCELLED" (grey)
 * - **Tap to expand** — reveals full details:
 *   - Triage answers (Was conscious / Was breathing / Had severe bleeding)
 *   - Driver name and vehicle number
 *   - Response time (time from SOS to ambulance arrival)
 * - Empty state illustration when there are no past dispatches
 *
 * ## Data Source
 * Calls [com.trana.patient.data.firebase.FirebaseRepository.getDispatchHistory] with
 * the current user's UID to fetch all dispatch records from `/dispatches` where
 * `patientId == currentUser.uid`, ordered by `createdAt` descending.
 *
 * ## Navigation
 * - **Incoming:** From [com.trana.patient.ui.home.HomeScreen] (history shortcut)
 *   or automatically from [com.trana.patient.ui.tracking.TrackingScreen] when dispatch is COMPLETED
 * - **No outgoing navigation** — this is a terminal screen (back button returns to Home)
 */

// TODO: Implement HistoryScreen composable:
//
// @Composable
// fun HistoryScreen(
//     navController: NavController,
//     viewModel: HistoryViewModel = viewModel()
// ) {
//     val dispatches by viewModel.dispatchHistory.collectAsState()
//     var expandedDispatchId by remember { mutableStateOf<String?>(null) }
//
//     LazyColumn {
//         items(dispatches) { dispatch ->
//             DispatchHistoryCard(
//                 dispatch = dispatch,
//                 isExpanded = expandedDispatchId == dispatch.id,
//                 onClick = {
//                     expandedDispatchId = if (expandedDispatchId == dispatch.id) null else dispatch.id
//                 }
//             )
//         }
//     }
// }
