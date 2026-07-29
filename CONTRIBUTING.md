# 🤝 Contributing to Trana

Thank you for contributing to the Trana Emergency Response Platform!  
This guide covers everything you need to know before submitting code, filing issues, or making documentation changes.

---

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Project Structure](#project-structure)
- [Development Setup](#development-setup)
- [Git Workflow](#git-workflow)
- [Coding Standards](#coding-standards)
- [Submitting a Pull Request](#submitting-a-pull-request)
- [Issue Reporting](#issue-reporting)
- [Documentation](#documentation)

---

## Code of Conduct

This is an EPICS (Engineering Projects in Community Service) project. All contributors are expected to be respectful, inclusive, and constructive in all interactions — in code reviews, issues, and discussions.

---

## Project Structure

```
Trana---EPICS-Project/
├── firebase/              # Firebase schema & security rules (shared by all apps)
├── trana-patient-app/     # Patient SOS Android app (Kotlin + Jetpack Compose)
├── trana-driver-app/      # Driver/Paramedic Android app (Kotlin + Jetpack Compose)
├── trana-desktop-app/     # Hospital Command Dashboard (Python + HTML/CSS/JS)
├── README.md              # Project-level overview and proposal
├── implementation.md      # Technical architecture & implementation blueprint
├── STEPS.md               # Phase-by-phase execution checklist
└── CONTRIBUTING.md        # This file
```

Each sub-project has its own `README.md` with setup instructions for that component.

---

## Development Setup

### Prerequisites

| Tool | Version | Used For |
|------|---------|----------|
| **Git** | Latest | Version control |
| **Android Studio** | Hedgehog+ | Kotlin Android apps |
| **Kotlin** | 1.9+ | Mobile apps |
| **Python** | 3.10+ | Desktop dashboard |
| **pip** | Latest | Python dependencies |

### Cloning the Repository

```bash
git clone https://github.com/YOUR_ORG/Trana---EPICS-Project.git
cd "Trana---EPICS-Project"
```

### Setting Up Each Sub-Project

- **Patient App** — see [`trana-patient-app/README.md`](./trana-patient-app/README.md)
- **Driver App** — see [`trana-driver-app/README.md`](./trana-driver-app/README.md)
- **Desktop App** — see [`trana-desktop-app/README.md`](./trana-desktop-app/README.md)
- **Firebase** — see [`firebase/README.md`](./firebase/README.md)

### API Keys & Secrets

> ⚠️ **Never commit API keys, credentials, or `google-services.json` to Git.**

All secrets are managed via:
- `local.properties` (Android — gitignored)
- `.env` files (Python — gitignored)
- A **private shared document** (Notion / Google Doc) maintained by the project lead

---

## Git Workflow

We use a **feature-branch workflow**. The `Source-Code` branch is protected and requires at least **1 PR approval** before merging.

### Branch Naming Convention

```
feature/<short-description>       # New feature
fix/<short-description>           # Bug fix
docs/<short-description>          # Documentation only
refactor/<short-description>      # Code cleanup, no behaviour change
test/<short-description>          # Tests only
```

**Examples:**
```
feature/home-sos-button
fix/otp-resend-timer
docs/firebase-schema-readme
```

### Step-by-Step Workflow

```bash
# 1. Always branch off the latest Source-Code
git checkout Source-Code
git pull origin Source-Code

# 2. Create your feature branch
git checkout -b feature/your-feature-name

# 3. Make your changes, commit often with clear messages
git add .
git commit -m "feat: add 3-second SOS press-and-hold confirmation ring"

# 4. Push and open a Pull Request
git push origin feature/your-feature-name
```

### Commit Message Format

Follow the **Conventional Commits** style:

```
<type>(<scope>): <short description>

[optional body]
[optional footer]
```

| Type | When to use |
|------|-------------|
| `feat` | New feature |
| `fix` | Bug fix |
| `docs` | Documentation changes |
| `style` | Formatting, no logic change |
| `refactor` | Code refactor, no feature change |
| `test` | Adding or fixing tests |
| `chore` | Build process, dependency updates |

**Examples:**
```
feat(patient-app): implement OTP verification screen with resend timer
fix(firebase): correct dispatch status enum values
docs(readme): add Maps API key setup instructions
```

---

## Coding Standards

### Kotlin (Android Apps)

- Follow the [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use **Jetpack Compose** for all UI — no XML layouts
- Follow the **MVVM pattern**: `Screen → ViewModel → Repository`
- All Firebase operations must return `Flow<Result<T>>` to handle loading/error states
- Use `StateFlow` to expose UI state from ViewModels
- Add **KDoc comments** to all public functions and classes
- Use `sealed class` for UI state (e.g., `UiState.Loading`, `UiState.Success`, `UiState.Error`)

```kotlin
/**
 * Sends a 6-digit OTP to the given phone number using Firebase Phone Auth.
 *
 * @param phoneNumber The full phone number including country code (e.g., +919876543210)
 * @param activity    The calling Activity required by Firebase Phone Auth SDK
 */
fun sendOtp(phoneNumber: String, activity: Activity) { ... }
```

### Python (Desktop App)

- Follow [PEP 8](https://peps.python.org/pep-0008/)
- Add **docstrings** to all functions and classes (Google-style preferred)
- Use type hints throughout
- Keep `main.py` minimal — delegate logic to dedicated modules

```python
def get_active_dispatches(hospital_id: str) -> list[dict]:
    """
    Fetches all active dispatch records for a given hospital.

    Args:
        hospital_id: The Firebase key of the hospital (e.g., "hosp_01").

    Returns:
        A list of dispatch dictionaries sorted by ETA ascending.
    """
```

### HTML / CSS / JavaScript (Dashboard UI)

- Keep HTML semantic — use `<section>`, `<article>`, `<header>`, `<nav>` appropriately
- All IDs must be unique and descriptive (e.g., `#inbound-queue-list`, `#icu-bed-counter`)
- Follow the design system defined in `ui/css/style.css` — do not add inline styles
- JavaScript: use `const`/`let` (no `var`), async/await for Firebase calls, JSDoc comments

---

## Submitting a Pull Request

1. Make sure your branch is up to date with `Source-Code` before opening a PR
2. Fill in the PR template (title, description, what was changed, how to test)
3. Link any related issues in the PR description (`Closes #42`)
4. Assign **at least 1 reviewer** from the team
5. All CI checks must pass before merging
6. Squash merge into `Source-Code` — keep the commit history clean

### PR Checklist

Before opening your PR, confirm:

- [ ] Code follows the project's coding standards
- [ ] No API keys, secrets, or `google-services.json` committed
- [ ] All new functions/classes have KDoc or docstring comments
- [ ] `STEPS.md` updated to mark completed tasks with `[x]`
- [ ] The feature was manually tested on a device or emulator
- [ ] No debug `println` / `console.log` / `print` left in production code

---

## Issue Reporting

Use GitHub Issues to report bugs or request features.

**For bugs**, include:
- Steps to reproduce
- Expected behaviour vs. actual behaviour
- Device / OS version
- Relevant logs or screenshots

**For feature requests**, reference the `STEPS.md` phase where it belongs and explain the user value.

---

## Documentation

- All new modules, classes, and non-obvious functions must include inline documentation
- If you change the Firebase schema, update `firebase/schema.json` and `firebase/README.md`
- If you add a new screen or route, update the relevant `README.md` and `implementation.md`
- Keep `STEPS.md` updated — check off tasks as you complete them

---

*For questions, use the team communication channel. For urgent issues during pilot, contact the project lead directly.*
