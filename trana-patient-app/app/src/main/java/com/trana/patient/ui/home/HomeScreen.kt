package com.trana.patient.ui.home

/**
 * HomeScreen — the main SOS screen and primary entry point after login.
 *
 * This screen's single job is to let the patient **initiate an emergency dispatch**
 * as quickly as possible. Every design decision here prioritises speed and
 * panic-usability over aesthetics.
 *
 * ## UI Elements
 * - **Large SOS Button** (center, full-width, emergency red) — thumb-reachable at the
 *   bottom third of the screen; minimum 120dp height
 * - **Press-and-hold ring animation** — a 3-second countdown arc surrounds the button
 *   while held; releasing before 3 seconds cancels the SOS (prevents accidental triggers)
 * - **Detected address label** — reverse-geocoded from the device's current GPS coordinates,
 *   displayed below the button (e.g., "Near Koregaon Park, Pune")
 * - **History shortcut** — tappable text or bottom-sheet handle to view past dispatches
 *   (navigates to [HistoryScreen])
 *
 * ## Behaviour
 * 1. Screen loads → [HomeViewModel] starts reverse-geocoding the current GPS location
 * 2. User presses and holds the SOS button → 3-second ring animates
 * 3. User holds for 3 full seconds → [HomeViewModel.createSosDispatch] is called
 * 4. Screen navigates to [com.trana.patient.ui.triage.TriageScreen]
 * 5. If the user releases before 3 seconds → animation resets, no dispatch created
 *
 * ## Safety Mechanism
 * The 3-second press-and-hold requirement is intentional and critical — it prevents
 * accidental SOS dispatches from pocket taps or casual app opens. Do NOT change this
 * to a single tap.
 *
 * ## Navigation
 * - **Incoming:** From [com.trana.patient.ui.splash.SplashScreen] (logged-in user)
 *   or from [com.trana.patient.ui.auth.ProfileSetupScreen] (after first setup)
 * - **Outgoing (SOS confirmed):** To [com.trana.patient.ui.triage.TriageScreen]
 * - **Outgoing (history):** To [com.trana.patient.ui.history.HistoryScreen]
 */

// TODO: Implement HomeScreen composable:
//
// @Composable
// fun HomeScreen(
//     navController: NavController,
//     viewModel: HomeViewModel = viewModel()
// ) {
//     val address by viewModel.currentAddress.collectAsState()
//     val dispatchState by viewModel.dispatchState.collectAsState()
//
//     LaunchedEffect(dispatchState) {
//         if (dispatchState is HomeViewModel.DispatchState.Dispatched) {
//             navController.navigate(Screen.Triage.route)
//         }
//     }
//
//     // Build UI: SOS button with press-and-hold detection using PointerInputScope
//     // Use Animatable for the countdown ring sweep animation
// }
