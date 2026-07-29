package com.trana.patient.ui.tracking

/**
 * TrackingViewModel — manages real-time Firebase data subscriptions for the tracking screen.
 *
 * This ViewModel opens two concurrent Firebase Realtime Database listeners and exposes
 * the live data as [kotlinx.coroutines.flow.StateFlow]s for [TrackingScreen] to observe.
 *
 * ## Firebase Listeners
 *
 * ### Dispatch Listener
 * Path: `/dispatches/{dispatchId}`
 * - Fires on any field change in the dispatch document
 * - Updates [dispatchStatus] and [etaMinutes] and [driverInfo]
 *
 * ### Ambulance Location Listener
 * Path: `/ambulances/{ambulanceId}/currentLocation`
 * - Fires every ~3 seconds as the driver's GPS service pushes a new fix
 * - Updates [ambulanceLocation] with the new [com.trana.patient.data.model.Location]
 *
 * ## Exposed State
 * | StateFlow          | Type       | Description                                   |
 * |--------------------|------------|-----------------------------------------------|
 * | `dispatchStatus`   | String     | "EN_ROUTE" / "ARRIVED" / "COMPLETED"          |
 * | `ambulanceLocation`| Location?  | Latest GPS coordinates of the ambulance        |
 * | `etaMinutes`       | Int        | Estimated minutes to arrival                   |
 * | `driverInfo`       | DriverInfo?| Driver name, phone, vehicle number, type       |
 *
 * ## Resource Cleanup
 * Firebase `ValueEventListener`s are permanent by default — they continue firing even
 * after the ViewModel is no longer used. To prevent memory leaks and unnecessary reads,
 * both listeners **must be removed** in `onCleared()`:
 *
 * ```kotlin
 * override fun onCleared() {
 *     super.onCleared()
 *     dispatchRef.removeEventListener(dispatchListener)
 *     locationRef.removeEventListener(locationListener)
 * }
 * ```
 *
 * Alternatively, use `callbackFlow { awaitClose { ref.removeEventListener(listener) } }`
 * which handles cleanup automatically when the Flow is cancelled.
 */

// TODO: Implement TrackingViewModel:
//
// class TrackingViewModel(
//     private val dispatchId: String,
//     private val repository: FirebaseRepository = FirebaseRepository()
// ) : ViewModel() {
//
//     private val _dispatchStatus = MutableStateFlow("EN_ROUTE")
//     val dispatchStatus: StateFlow<String> = _dispatchStatus
//
//     private val _ambulanceLocation = MutableStateFlow<Location?>(null)
//     val ambulanceLocation: StateFlow<Location?> = _ambulanceLocation
//
//     private val _etaMinutes = MutableStateFlow(0)
//     val etaMinutes: StateFlow<Int> = _etaMinutes
//
//     init {
//         // Start observing dispatch and ambulance location via repository flows
//         viewModelScope.launch {
//             repository.observeDispatch(dispatchId).collect { result -> ... }
//         }
//     }
//
//     override fun onCleared() {
//         super.onCleared()
//         // Cancel all Firebase listeners here to prevent memory leaks
//     }
// }
