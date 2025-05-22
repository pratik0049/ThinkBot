chrome.runtime.onInstalled.addListener(() => {
    console.log("Think Bot Extension Installed!");
});

// Ensure the side panel opens when the extension icon is clicked
chrome.action.onClicked.addListener((tab) => {
    if (chrome.sidePanel && chrome.sidePanel.setOptions) {
        chrome.sidePanel.setOptions({
            tabId: tab.id,
            path: "sidepanel.html",
            enabled: true
        }).then(() => {
            console.log("Side Panel Activated!");
        }).catch((error) => {
            console.error("Error opening Side Panel:", error);
        });
    } else {
        console.error("chrome.sidePanel API is not supported in this version of Chrome.");
    }
});
