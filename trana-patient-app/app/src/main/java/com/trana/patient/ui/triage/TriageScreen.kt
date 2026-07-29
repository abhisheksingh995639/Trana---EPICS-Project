package com.trana.patient.ui.triage

/**
 * TriageScreen — a rapid 3-step emergency assessment form.
 *
 * Immediately after an SOS is confirmed, the patient (or a bystander) answers 3 binary
 * yes/no questions to help the dispatch system determine the severity of the emergency
 * and select the appropriate ambulance type (BLS vs. ALS).
 *
 * This screen is designed for **extreme stress conditions**: full-width buttons,
 * high-contrast colors, and a single question per step to minimize cognitive load.
 *
 * ## Questions
 * | Step | Question                          | Buttons         | Firebase Field    |
 * |------|-----------------------------------|-----------------|-------------------|
 * | 1/3  | "Is the patient conscious?"        | YES (green) / NO (red) | `isConscious`  |
 * | 2/3  | "Is the patient breathing normally?" | YES (green) / NO (red) | `isBreathing` |
 * | 3/3  | "Is there severe bleeding?"        | YES (red) / NO (green) | `severeBleeding` |
 *
 * ## UI Elements (per step)
 * - Question text (large, bold, centered)
 * - Step indicator (e.g., "Step 2 of 3" with a progress bar or dot indicators)
 * - Two full-width answer buttons (YES / NO) — minimum 72dp height, thumb-reachable
 *
 * ## Behaviour
 * 1. Screen loads at Step 1; user taps YES or NO
 * 2. [TriageViewModel] stores the answer and advances to Step 2
 * 3. After all 3 steps are answered, [TriageViewModel.submitTriage] is called:
 *    - Writes `{ isConscious, isBreathing, severeBleeding }` to
 *      `/dispatches/{dispatchId}/triage` in Firebase
 * 4. On successful write → navigate to [com.trana.patient.ui.tracking.TrackingScreen]
 *
 * ## Dispatch Algorithm Impact
 * If `isConscious == false` or `isBreathing == false`, the backend dispatch algorithm
 * **prefers an ALS ambulance** (Advanced Life Support with ventilator and ECG monitor)
 * over a BLS ambulance.
 *
 * ## Navigation
 * - **Incoming:** From [com.trana.patient.ui.home.HomeScreen] after SOS confirmation
 * - **Outgoing:** To [com.trana.patient.ui.tracking.TrackingScreen]
 */

// TODO: Implement TriageScreen composable:
//
// @Composable
// fun TriageScreen(
//     navController: NavController,
//     dispatchId: String,  // passed from HomeScreen after dispatch creation
//     viewModel: TriageViewModel = viewModel()
// ) {
//     val step by viewModel.currentStep.collectAsState()
//     val isSubmitted by viewModel.isSubmitted.collectAsState()
//
//     LaunchedEffect(isSubmitted) {
//         if (isSubmitted) navController.navigate(Screen.Tracking.route)
//     }
//
//     // Render question and YES/NO buttons based on current step
//     // Use AnimatedContent or slide transitions between steps
// }
