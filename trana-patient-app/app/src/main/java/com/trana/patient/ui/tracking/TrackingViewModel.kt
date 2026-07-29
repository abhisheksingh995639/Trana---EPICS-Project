package com.trana.patient.ui.tracking

// TODO: Implement TrackingViewModel.
// Responsibilities:
//   - Subscribe to Firebase /dispatches/{dispatchId} using a ValueEventListener
//   - Subscribe to Firebase /ambulances/{ambulanceId}/currentLocation for GPS stream
//   - Expose ambulance LatLng, ETA minutes, driver info, and dispatch status as StateFlow
//   - Cancel all listeners when ViewModel is cleared (onCleared)
