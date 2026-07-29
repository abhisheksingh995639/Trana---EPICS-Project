package com.trana.patient.service

// TODO: Implement TranaFirebaseMessagingService — handles FCM push notifications.
// Responsibilities:
//   - Override onMessageReceived to handle incoming FCM messages
//   - On dispatch status change notifications: show a system notification with ETA update
//   - On ambulance arrival: show "Ambulance has arrived" high-priority notification
//   - Register this service in AndroidManifest.xml under the MESSAGING_EVENT intent filter
