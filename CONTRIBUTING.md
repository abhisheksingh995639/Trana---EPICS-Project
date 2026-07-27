# Contributing to Trana 🚑

First off — thank you for taking the time to contribute! Trana is an open-source EPICS project aimed at saving lives by fixing India's fragmented emergency response infrastructure. Every contribution, no matter how small, moves us closer to that mission.

Please read this guide carefully before making your first contribution.

---

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
- [Good First Issues](#good-first-issues)
- [Development Setup](#development-setup)
- [Branching Strategy](#branching-strategy)
- [Submitting a Pull Request](#submitting-a-pull-request)
- [Commit Message Guidelines](#commit-message-guidelines)
- [Reporting Bugs](#reporting-bugs)
- [Suggesting Features](#suggesting-features)
- [Style Guidelines](#style-guidelines)

---

## Code of Conduct

This project and everyone participating in it is governed by the [Trana Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code. Please report unacceptable behavior via a GitHub Issue labeled `code-of-conduct`.

---

## How Can I Contribute?

There are many ways to contribute that don't require writing code:

- 📖 **Improve documentation** — Fix typos, clarify confusing sections, or add missing context in the README or docs.
- 🐛 **Report bugs** — Open a bug report if something doesn't work as expected.
- 💡 **Suggest features** — Open a feature request to share ideas aligned with the project roadmap.
- 🧪 **Write tests** — Help improve reliability by adding unit or integration tests.
- 🔍 **Review Pull Requests** — Review open PRs and leave constructive feedback.
- 💻 **Write code** — Pick up an open issue and implement a fix or feature.

---

## Good First Issues

New to the codebase? Look for issues tagged with:

- [`good first issue`](../../issues?q=label%3A%22good+first+issue%22) — Small, well-scoped tasks ideal for first-time contributors.
- [`help wanted`](../../issues?q=label%3A%22help+wanted%22) — Issues where maintainers need external input or implementation.
- [`documentation`](../../issues?q=label%3A%22documentation%22) — Writing or improving docs — no coding required.

---

## Development Setup

> **Note:** The codebase is currently in the proposal/pre-development stage. This section will be updated as the stack is finalized. Check back as Phase 1 and Phase 2 of the roadmap progress.

### Prerequisites (Planned)

Based on the proposed technology stack, you will likely need:

- **Flutter SDK** (for mobile apps — Patient and Driver interfaces)
- **Node.js** (for backend API services)
- **A modern browser** (for the Hospital Web Dashboard)
- **Git** (for version control)

### Steps

1. **Fork** this repository by clicking the "Fork" button at the top of the GitHub page.
2. **Clone** your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/Trana---EPICS-Project.git
   cd Trana---EPICS-Project
   ```
3. **Add the upstream remote** so you can pull in future changes:
   ```bash
   git remote add upstream https://github.com/ORIGINAL_ORG/Trana---EPICS-Project.git
   ```
4. **Install dependencies** (commands will be added when the codebase is initialized in Phase 2).

---

## Branching Strategy

We use a simple feature-branch workflow:

| Branch | Purpose |
|--------|---------|
| `main` | Stable, reviewed code only. Never commit directly. |
| `develop` | Integration branch for completed features. |
| `feature/<short-name>` | Your work branch for a new feature or fix. |
| `fix/<short-name>` | Your work branch for a bug fix. |
| `docs/<short-name>` | Your work branch for documentation changes. |

**Always branch off `develop`, not `main`.**

```bash
# Sync your local develop with upstream
git checkout develop
git pull upstream develop

# Create your feature branch
git checkout -b feature/your-feature-name
```

---

## Submitting a Pull Request

1. **Ensure your branch is up to date** with `upstream/develop` before opening a PR.
2. **Make your changes** and commit them (see [Commit Message Guidelines](#commit-message-guidelines)).
3. **Push** your branch to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```
4. **Open a Pull Request** on GitHub from your fork's branch into `Trana---EPICS-Project:develop`.
5. **Fill in the PR template** — describe what you changed and why, and link the relevant issue (e.g., `Closes #42`).
6. **Wait for a review** — a maintainer will review your PR and may request changes.
7. Once approved, a maintainer will **merge** your PR.

### PR Checklist

Before submitting, please confirm:

- [ ] My code follows the style guidelines for this project
- [ ] I have performed a self-review of my code
- [ ] I have added comments where needed for complex logic
- [ ] My changes do not introduce new warnings or errors
- [ ] I have linked the related issue in the PR description
- [ ] I have updated relevant documentation if necessary

---

## Commit Message Guidelines

We follow a simplified version of [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>(<scope>): <short summary>
```

**Types:**

| Type | When to Use |
|------|------------|
| `feat` | A new feature |
| `fix` | A bug fix |
| `docs` | Documentation only changes |
| `style` | Formatting, missing semicolons, etc. — no logic change |
| `refactor` | Code change that neither fixes a bug nor adds a feature |
| `test` | Adding or updating tests |
| `chore` | Build process, tooling, dependency updates |

**Examples:**

```
feat(dispatch): add nearest-vehicle algorithm for SOS requests
fix(tracking): resolve stale GPS coordinate on reconnection
docs(readme): update technology stack section
```

---

## Reporting Bugs

Use the **Bug Report** issue template. Please include:

- A clear and descriptive title
- Steps to reproduce the behavior
- What you expected to happen vs. what actually happened
- Screenshots or logs if applicable
- Your environment (OS, device, browser version)

---

## Suggesting Features

Use the **Feature Request** issue template. Please include:

- A clear description of the problem your feature solves
- How it aligns with Trana's core mission (reducing response times, improving equipment standards, or hospital coordination)
- Any alternative solutions you've considered

---

## Style Guidelines

> Full style guides will be published here as the codebase is initialized. General principles:

- **Clarity over cleverness** — Write code that a new contributor can understand without context.
- **Document non-obvious logic** — Add inline comments explaining *why*, not just *what*.
- **Accessibility first** — All UI components should be accessible (WCAG 2.1 AA minimum).
- **Offline resilience** — Given India's variable network conditions, design for graceful degradation.

---

## Questions?

If you're unsure about anything, open a [GitHub Discussion](../../discussions) or comment on the relevant issue. We'd rather answer a question than have you stuck.

Thank you again for contributing to Trana. 🙏
