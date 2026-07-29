package com.trana.patient.data.model

// TODO: Define data model classes for the Trana Patient App.
// These Kotlin data classes map to the Firebase Realtime Database JSON structure.

// User  → /users/{uid}
// data class User(val name: String, val phone: String, val role: String, val bloodGroup: String)

// Dispatch → /dispatches/{dispatchId}
// data class Dispatch(val patientId: String, val ambulanceId: String, val status: String, val triage: Triage, val etaMinutes: Int)

// Triage → embedded in Dispatch
// data class Triage(val isConscious: Boolean, val isBreathing: Boolean, val severeBleeding: Boolean)

// Ambulance → /ambulances/{ambulanceId}
// data class Ambulance(val vehicleNumber: String, val type: String, val status: String, val currentLocation: Location)

// Location → embedded in Ambulance
// data class Location(val lat: Double, val lng: Double, val heading: Int)
