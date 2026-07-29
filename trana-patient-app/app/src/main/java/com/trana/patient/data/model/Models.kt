package com.trana.patient.data.model

/**
 * Data models for the Trana Patient App.
 *
 * These Kotlin data classes map directly to the Firebase Realtime Database JSON structure
 * defined in `firebase/schema.json`. Firebase deserializes JSON into these classes
 * automatically using its reflection-based deserialization.
 *
 * Important: All fields must have default values so Firebase can deserialize
 * them even when some JSON fields are missing (partial reads).
 *
 * Firebase tree paths:
 *   /users/{uid}          → [User]
 *   /ambulances/{id}      → [Ambulance]
 *   /dispatches/{id}      → [Dispatch]
 *   /hospitals/{id}       → [Hospital]
 *   /checklists/{id}      → [Checklist]
 */

// TODO: Uncomment and finalize these data classes when implementation begins.
// Ensure each field exactly matches the corresponding Firebase JSON key.

// ─────────────────────────────────────────────────────────────────────────────
// User → /users/{uid}
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Represents a registered user in the Trana system.
 *
 * Stored at `/users/{uid}` where uid is the Firebase Auth UID.
 *
 * @property name        Full display name of the user.
 * @property phone       Phone number including country code (e.g., "+919876543210").
 * @property role        User role: "patient" | "driver" | "hospital_staff" | "admin".
 * @property bloodGroup  Blood group (patients only), e.g., "O+", "AB-".
 * @property createdAt   ISO 8601 timestamp of account creation.
 */
// data class User(
//     val name: String = "",
//     val phone: String = "",
//     val role: String = "",
//     val bloodGroup: String = "",
//     val createdAt: String = ""
// )

// ─────────────────────────────────────────────────────────────────────────────
// Dispatch → /dispatches/{dispatchId}
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Represents an active or completed emergency dispatch event.
 *
 * Stored at `/dispatches/{dispatchId}`.
 * Created when the patient confirms an SOS; updated as the dispatch progresses.
 *
 * @property patientId   Firebase UID of the patient who triggered the SOS.
 * @property ambulanceId Firebase key of the assigned ambulance (e.g., "amb_01").
 * @property status      Current state: "PENDING" | "EN_ROUTE" | "ARRIVED" | "COMPLETED" | "CANCELLED".
 * @property triage      Embedded triage answers collected from the patient.
 * @property origin      Patient's GPS coordinates at the time of SOS.
 * @property etaMinutes  Estimated time of arrival in minutes (updated periodically).
 * @property createdAt   ISO 8601 timestamp of SOS creation.
 */
// data class Dispatch(
//     val patientId: String = "",
//     val ambulanceId: String = "",
//     val status: String = "PENDING",
//     val triage: Triage = Triage(),
//     val origin: Location = Location(),
//     val etaMinutes: Int = 0,
//     val createdAt: String = ""
// )

// ─────────────────────────────────────────────────────────────────────────────
// Triage → embedded inside Dispatch at /dispatches/{id}/triage
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Triage answers collected from the patient during the TriageScreen.
 *
 * Embedded inside [Dispatch] at `/dispatches/{id}/triage`.
 * Used by the dispatch algorithm to determine the appropriate ambulance type
 * (ALS is preferred for unconscious or non-breathing patients).
 *
 * @property isConscious    Whether the patient is conscious (true = Yes, false = No).
 * @property isBreathing    Whether the patient is breathing normally.
 * @property severeBleeding Whether there is severe, uncontrolled bleeding.
 * @property notes          Optional free-text notes from the patient or bystander.
 */
// data class Triage(
//     val isConscious: Boolean = true,
//     val isBreathing: Boolean = true,
//     val severeBleeding: Boolean = false,
//     val notes: String = ""
// )

// ─────────────────────────────────────────────────────────────────────────────
// Ambulance → /ambulances/{ambulanceId}
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Represents a registered ambulance in the Trana fleet.
 *
 * Stored at `/ambulances/{ambulanceId}`.
 * The driver app updates [currentLocation] every 3 seconds via the GPS foreground service.
 *
 * @property driverId              Firebase UID of the assigned driver.
 * @property vehicleNumber         License plate number (e.g., "MH-12-EM-4521").
 * @property type                  Ambulance category: "BLS" (Basic Life Support) | "ALS" (Advanced Life Support).
 * @property status                Operational state: "ONLINE" | "OFFLINE" | "BUSY" | "SUSPENDED".
 * @property equipmentVerifiedToday True if the driver has submitted and passed today's equipment checklist.
 * @property currentLocation       Latest GPS coordinates, updated in real time by the driver app.
 */
// data class Ambulance(
//     val driverId: String = "",
//     val vehicleNumber: String = "",
//     val type: String = "BLS",
//     val status: String = "OFFLINE",
//     val equipmentVerifiedToday: Boolean = false,
//     val currentLocation: Location = Location()
// )

// ─────────────────────────────────────────────────────────────────────────────
// Location → embedded inside Ambulance at /ambulances/{id}/currentLocation
// ─────────────────────────────────────────────────────────────────────────────

/**
 * GPS coordinates with heading, embedded inside [Ambulance].
 *
 * Also used to represent the patient's origin in [Dispatch].
 *
 * @property lat       Latitude in decimal degrees (WGS84).
 * @property lng       Longitude in decimal degrees (WGS84).
 * @property heading   Bearing in degrees (0–360), where 0 = North. Used to rotate the ambulance marker.
 * @property updatedAt ISO 8601 timestamp of the last GPS fix (for staleness detection).
 */
// data class Location(
//     val lat: Double = 0.0,
//     val lng: Double = 0.0,
//     val heading: Int = 0,
//     val updatedAt: String = ""
// )
