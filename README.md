

# 🎯 LPU CGPA Calculator — Jetpack Compose MVI Project 🌟

A modern CGPA calculator app for LPU students, built with Jetpack Compose and a robust MVI (Model-View-Intent) architecture. Experience a sleek, intuitive UI and seamless state management for accurate CGPA calculations.

---

## 📱 App Screenshots



## 🌟 Features
- Calculate CGPA using Marks, Grade Point, or Letter Grade
- Real-time updates and instant feedback
- Instantly view grade and grade point from marks
- Find minimum marks for a specific grade
- Modern, responsive UI (Jetpack Compose + Material Design)
- Robust state management (MVI)

---

## 🛠️ Tech Stack
- Jetpack Compose
- Kotlin
- Material Design
- MVI Architecture

---

## 🚀 Getting Started

1. **Clone the repository:**
   ```sh
   git clone https://github.com/skp3214/LPUCgpaCalc-JetpackComposeProject.git
   ```
2. **Open in Android Studio:**
   - File > Open > Select the project folder
3. **Build & Run:**
   - Click the 'Run' button or use `Shift + F10`

---

## 🤝 How to Contribute

We welcome contributions from everyone! To get started:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

**Please follow our Code of Conduct and Contributing Guidelines (if available).**

If you have ideas, suggestions, or want to report bugs, please [open an issue](https://github.com/skp3214/LPUCgpaCalc-JetpackComposeProject/issues).

---

## 📚 How MVI is Implemented
- **Model:** Represents the app state (CGPA, grades, marks, etc.)
- **View:** Composables render the UI based on the current state
- **Intent:** User actions (input, button clicks) are dispatched as intents
- **State Management:** ViewModel processes intents, updates state, and triggers UI recomposition
- **Benefits:** Predictable state transitions, easy debugging/testing, scalable for future features

---

## 💡 Why MVI?
- Unidirectional Data Flow for maintainability
- Separation of Concerns: UI, business logic, and state
- Reactive UI: UI updates automatically as state changes

---

