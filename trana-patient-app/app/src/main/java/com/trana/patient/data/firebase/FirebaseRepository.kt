package com.trana.patient.data.firebase

// TODO: Implement FirebaseRepository — the single data access layer for Firebase.
// Responsibilities:
//   - getUserProfile(uid): read from /users/{uid}
//   - saveUserProfile(uid, user): write to /users/{uid}
//   - createDispatch(dispatch): write to /dispatches/{id}
//   - observeDispatch(dispatchId): return a Flow of Dispatch (live updates)
//   - observeAmbulanceLocation(ambulanceId): return a Flow of Location (live GPS)
//   - getDispatchHistory(patientId): query /dispatches filtered by patientId
//
// All read operations should return Flow<Result<T>> to handle loading and error states.
