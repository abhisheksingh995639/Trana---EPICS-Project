package com.trana.patient.ui.triage

/**
 * TriageViewModel — manages the 3-step triage form state and Firebase write.
 *
 * ## State
 * | StateFlow      | Type    | Description                                   |
 * |----------------|---------|-----------------------------------------------|
 * | `currentStep`  | Int     | Current question step (1, 2, or 3)            |
 * | `isConscious`  | Boolean? | Answer to Q1; null until answered             |
 * | `isBreathing`  | Boolean? | Answer to Q2; null until answered             |
 * | `severeBleeding` | Boolean? | Answer to Q3; null until answered           |
 * | `isSubmitting` | Boolean | True while the Firebase write is in progress  |
 * | `isSubmitted`  | Boolean | True after successful Firebase write          |
 * | `error`        | String? | Error message if Firebase write fails         |
 *
 * ## Step Progression
 * ```
 * answerStep(answer)
 *   if step == 1 → sets isConscious   = answer, currentStep = 2
 *   if step == 2 → sets isBreathing   = answer, currentStep = 3
 *   if step == 3 → sets severeBleeding = answer → calls submitTriage()
 * ```
 *
 * ## Firebase Write
 * [submitTriage] writes to `/dispatches/{dispatchId}/triage`:
 * ```json
 * {
 *   "isConscious": true,
 *   "isBreathing": false,
 *   "severeBleeding": false
 * }
 * ```
 * The [dispatchId] is the key returned by [HomeViewModel.createSosDispatch] and passed
 * to this ViewModel at construction or via a `savedStateHandle` argument.
 */

// TODO: Implement TriageViewModel:
//
// class TriageViewModel(
//     private val dispatchId: String,
//     private val repository: FirebaseRepository = FirebaseRepository()
// ) : ViewModel() {
//
//     private val _currentStep = MutableStateFlow(1)
//     val currentStep: StateFlow<Int> = _currentStep
//
//     private var isConscious: Boolean? = null
//     private var isBreathing: Boolean? = null
//     private var severeBleeding: Boolean? = null
//
//     private val _isSubmitted = MutableStateFlow(false)
//     val isSubmitted: StateFlow<Boolean> = _isSubmitted
//
//     /**
//      * Records the patient's answer for the current triage step and advances to the next.
//      *
//      * After the third and final answer is recorded, automatically calls [submitTriage].
//      *
//      * @param answer `true` for YES, `false` for NO.
//      */
//     fun answerStep(answer: Boolean) { ... }
//
//     /**
//      * Writes the completed triage answers to Firebase at `/dispatches/{dispatchId}/triage`.
//      *
//      * Called automatically after the third question is answered.
//      * Transitions [isSubmitted] to `true` on success, or populates [error] on failure.
//      */
//     private fun submitTriage() {
//         viewModelScope.launch { ... }
//     }
// }
