package com.trana.patient.ui.onboarding

/**
 * OnboardingScreen — a 3-slide introduction pager shown only on the first app install.
 *
 * This screen introduces new users to Trana's three core features before directing them
 * to the phone login flow. It is shown **only once** — after the user completes login,
 * they are never shown onboarding again (the SplashScreen skips it for logged-in users).
 *
 * ## Slides
 * | Slide | Title                              | Subtitle                                            | Illustration      |
 * |-------|------------------------------------|-----------------------------------------------------|-------------------|
 * | 1/3   | "Emergency help is one tap away"   | "Press and hold SOS — we dispatch the nearest ambulance instantly" | SOS button visual |
 * | 2/3   | "Smart triage, faster response"    | "Answer 3 quick questions so the right ambulance reaches you" | Triage form visual |
 * | 3/3   | "Track your ambulance in real time"| "See your ambulance move toward you, live on the map" | Map tracking visual |
 *
 * ## UI Elements
 * - Horizontal pager (swipe-to-advance or dot indicator tap)
 * - Page dot indicators (bottom center)
 * - "Next" button (advances to next slide)
 * - "Get Started" button — visible only on the last slide
 * - "Skip" text button (top-right corner) — jumps directly to PhoneLoginScreen
 *
 * ## Navigation
 * - **Incoming:** From [com.trana.patient.ui.splash.SplashScreen] (not logged in)
 * - **Outgoing (Get Started or Skip):** To [com.trana.patient.ui.auth.PhoneLoginScreen]
 */

// TODO: Implement OnboardingScreen composable:
//
// @Composable
// fun OnboardingScreen(navController: NavController) {
//     val pagerState = rememberPagerState(pageCount = { 3 })
//
//     HorizontalPager(state = pagerState) { page ->
//         OnboardingPage(slide = onboardingSlides[page])
//     }
//
//     // Show "Next" or "Get Started" button based on pagerState.currentPage
//     // "Get Started" navigates to PhoneLogin and pops onboarding from back stack
// }
