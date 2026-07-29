package com.trana.patient.ui.triage

// TODO: Implement TriageScreen composable.
// Responsibilities:
//   - Display 3 large-button yes/no questions (one per screen):
//       Q1: "Is the patient conscious?" (Yes / No)
//       Q2: "Is the patient breathing normally?" (Yes / No)
//       Q3: "Is there severe bleeding?" (Yes / No)
//   - Each answer is a full-width colored button (accessible, thumb-reach)
//   - Progress indicator (Step 1/3, 2/3, 3/3)
//   - On completion → save triage data to the dispatch record in Firebase and navigate to TrackingScreen
