package com.trana.patient.ui.home

/**
 * HomeViewModel — manages state for the SOS home screen.
 *
 * ## Responsibilities
 *
 * ### 1. Current Address (Reverse Geocoding)
 * Uses [com.google.android.gms.location.FusedLocationProviderClient] to get the device's
 * current GPS fix, then calls [android.location.Geocoder] to convert coordinates to a
 * human-readable address string (e.g., "Near Koregaon Park, Pune, Maharashtra").
 *
 * ### 2. SOS Dispatch Creation
 * When the patient confirms an SOS (3-second hold on [HomeScreen]):
 * 1. Captures the current GPS location
 * 2. Calls [com.trana.patient.data.firebase.FirebaseRepository.createDispatch] to write
 *    a new dispatch record to `/dispatches/{newPushKey}` with `status = "PENDING"`
 * 3. Stores the returned `dispatchId` for the triage step
 * 4. Transitions [dispatchState] to [DispatchState.Dispatched]
 *
 * ## Exposed State
 * | StateFlow        | Type           | Description                               |
 * |------------------|----------------|-------------------------------------------|
 * | `currentAddress` | String         | Reverse-geocoded address string           |
 * | `dispatchState`  | DispatchState  | IDLE / DISPATCHING / Dispatched(id)       |
 *
 * ## DispatchState
 * ```kotlin
 * sealed class DispatchState {
 *     object Idle        : DispatchState()
 *     object Dispatching : DispatchState()  // Firebase write in progress
 *     data class Dispatched(val dispatchId: String) : DispatchState()
 *     data class Error(val message: String) : DispatchState()
 * }
 * ```
 */

// TODO: Implement HomeViewModel:
//
// import androidx.lifecycle.ViewModel
// import androidx.lifecycle.viewModelScope
// import kotlinx.coroutines.flow.MutableStateFlow
// import kotlinx.coroutines.flow.StateFlow
// import kotlinx.coroutines.launch
//
// class HomeViewModel(
//     private val repository: FirebaseRepository = FirebaseRepository()
// ) : ViewModel() {
//
//     sealed class DispatchState {
//         object Idle : DispatchState()
//         object Dispatching : DispatchState()
//         data class Dispatched(val dispatchId: String) : DispatchState()
//         data class Error(val message: String) : DispatchState()
//     }
//
//     private val _currentAddress = MutableStateFlow("Detecting location...")
//     val currentAddress: StateFlow<String> = _currentAddress
//
//     private val _dispatchState = MutableStateFlow<DispatchState>(DispatchState.Idle)
//     val dispatchState: StateFlow<DispatchState> = _dispatchState
//
//     /**
//      * Reverse-geocodes the device's current GPS coordinates to a human-readable address.
//      * Updates [currentAddress] on success; leaves it as "Location unavailable" on failure.
//      *
//      * Must be called only after location permission has been granted.
//      *
//      * @param context Application context required by FusedLocationProviderClient.
//      */
//     fun detectCurrentAddress(context: Context) { ... }
//
//     /**
//      * Creates an emergency SOS dispatch record in Firebase.
//      *
//      * Called when the patient completes the 3-second press-and-hold on HomeScreen.
//      * Transitions [dispatchState] through Dispatching → Dispatched(id) on success,
//      * or → Error(message) on failure.
//      *
//      * @param patientId Firebase Auth UID of the current user.
//      * @param location  Current GPS coordinates of the patient.
//      */
//     fun createSosDispatch(patientId: String, location: Location) {
//         viewModelScope.launch { ... }
//     }
// }
