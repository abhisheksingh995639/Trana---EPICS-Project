package com.trana.patient

/**
 * TranaApplication — the Application class for the Trana Patient App.
 *
 * This class is instantiated by the Android OS before any Activity, Service,
 * or BroadcastReceiver. It is the first entry point of the application lifecycle.
 *
 * ## Responsibilities
 * - Initialize Firebase (FirebaseApp.initializeApp) — must happen before any
 *   Firebase SDK call
 * - Set up any app-wide singletons (e.g., FirebaseRepository instance, DI graph)
 * - Configure crash reporting or logging libraries if used
 *
 * ## Registration
 * This class is registered in `AndroidManifest.xml`:
 * ```xml
 * <application android:name=".TranaApplication" ...>
 * ```
 *
 * ## Usage Example
 * ```kotlin
 * class TranaApplication : Application() {
 *     override fun onCreate() {
 *         super.onCreate()
 *         // Firebase auto-initializes via google-services.json — no manual call needed
 *         // for standard initialization. Only call FirebaseApp.initializeApp(this)
 *         // if you have a non-default configuration.
 *     }
 * }
 * ```
 */

// TODO: Implement TranaApplication:
//
// import android.app.Application
// import com.google.firebase.FirebaseApp
//
// class TranaApplication : Application() {
//     override fun onCreate() {
//         super.onCreate()
//         // Firebase is initialized automatically via the google-services.json plugin.
//         // Add any app-wide singleton setup here (e.g., DI, crash reporting).
//     }
// }
