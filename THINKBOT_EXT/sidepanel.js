document.addEventListener("DOMContentLoaded", () => {
    // Load saved research notes
    chrome.storage.local.get(["researchNotes"], function (result) {
        const notesElement = document.getElementById("notes");
        if (notesElement && result.researchNotes) {
            notesElement.value = result.researchNotes;
        }
    });

    // Attach event listeners
    document.getElementById("summarize")?.addEventListener("click", summarizeText);
    document.getElementById("savenotesbutton")?.addEventListener("click", saveNotes);
});

async function summarizeText() {
    try {
        // Get active tab
        const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
        if (!tab) {
            showResult("Error: No active tab found.");
            return;
        }

        // Get selected text on the page
        const [executionResult] = await chrome.scripting.executeScript({
            target: { tabId: tab.id },
            function: () => ({ selectionText: window.getSelection().toString() }),
        });

        if (!executionResult || !executionResult.result || !executionResult.result.selectionText) {
            showResult("Please select some text first.");
            return;
        }

        const selectedText = executionResult.result.selectionText;
        console.log("Selected Text:", selectedText);

        // Send request to API
        const response = await fetch("http://localhost:8080/api/research/sum", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ content: selectedText, operation: "summarize" }),
        });

        console.log("API Response:", response);

        if (!response.ok) {
            throw new Error(`API error: ${response.status}`);
        }

        const text = await response.text();
        console.log("API Response Text:", text);

        showResult(text.replace(/\n/g, "<br>"));
    } catch (error) {
        console.error("Error:", error);
        showResult("Error: " + error.message);
    }
}

async function saveNotes() {
    const notesElement = document.getElementById("notes");
    if (!notesElement) {
        alert("Error: Notes element not found.");
        return;
    }

    const notes = notesElement.value;
    chrome.storage.local.set({ researchNotes: notes }, function () {
        alert("Notes are saved successfully.");
    });
}

function showResult(content) {
    const resultElement = document.getElementById("results");
    if (!resultElement) {
        console.error("Error: 'results' element not found in DOM.");
        return;
    }

    resultElement.innerHTML = `
        <div class="result-item">
            <div class="result-content">${content}</div>
        </div>
    `;
}
