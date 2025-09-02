

---

# Think – Smart Research Assistant

## 🚀 Overview

**ThinkBot** is a smart research assistant built with **Spring Boot, HTML, CSS, JavaScript, and a Chrome Extension**, powered by the **Gemini API**.
It helps users summarize web content instantly and save important notes for future reference, making research more efficient.

---

## ✨ Features

* 🔹 **AI-Powered Summarization** – Generates quick insights from large chunks of text.
* 🔹 **Chrome Extension** – Seamlessly integrates with your browser for easy access.
* 🔹 **Notes Storage** – Save important notes directly in **local storage** for quick retrieval.
* 🔹 **Clean UI** – Built with HTML, CSS, and JavaScript for a simple user experience.

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot
* **Frontend:** HTML, CSS, JavaScript
* **Browser Integration:** Chrome Extension
* **AI Engine:** Gemini API
* **Storage:** Local Storage

---

## 📂 Project Structure

```
Think/
│── backend/          # Spring Boot backend
│── extension/        # Chrome extension files
│   ├── manifest.json
│   ├── popup.html
│   ├── popup.js
│── frontend/         # Web UI (HTML, CSS, JS)
│── README.md
```

---

## ⚡ Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/think.git
cd think
```

### 2. Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

### 3. Chrome Extension

1. Open **Chrome** → Navigate to `chrome://extensions/`
2. Enable **Developer Mode**
3. Click **Load unpacked** and select the `extension/` folder

### 4. Run the Application

* The backend will handle API requests.
* The Chrome extension will provide UI integration.

---

## 📸 Screenshots

<img width="594" height="685" alt="Screenshot (44)" src="https://github.com/user-attachments/assets/f179fb4a-2520-43d9-aabb-1351fcd4f320" />

---

## 🔮 Future Enhancements

* Cloud-based note storage (instead of local storage).
* Multi-language support for summaries.
* Collaboration features for shared research.

