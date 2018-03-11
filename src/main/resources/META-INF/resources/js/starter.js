function updateHistory(menu, content) {
    window.history.pushState(content, menu, content);
}