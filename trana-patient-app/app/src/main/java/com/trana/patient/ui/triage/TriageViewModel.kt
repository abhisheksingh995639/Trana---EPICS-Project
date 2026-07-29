package com.trana.patient.ui.triage

// TODO: Implement TriageViewModel.
// Responsibilities:
//   - Store triage answers (isConscious, isBreathing, severeBleeding) as state
//   - On form completion, update the active dispatch record in Firebase:
//       /dispatches/{dispatchId}/triage → { isConscious, isBreathing, severeBleeding }
//   - Expose dispatch ID (needed by TrackingViewModel to subscribe to live updates)
