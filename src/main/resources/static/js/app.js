/**
 * Register an event at the document for the specified selector,
 * so events are still catched after DOM changes.
 */
function handleEvent(eventType, selector, handler) {
  document.addEventListener(eventType, function(event) {
    if (event.target.matches(selector + ', ' + selector + ' *')) {
      handler.apply(event.target.closest(selector), arguments);
    }
  });
}

handleEvent('submit', '.js-submit-confirm', function(event) {
  if (!confirm(this.getAttribute('data-confirm-message'))) {
    event.preventDefault();
    return false;
  }
  return true;
});

handleEvent('click', '.js-file-delete', function(event) {
  const $fileDiv = event.target.parentElement;
  const $fileRow = $fileDiv.previousElementSibling;
  $fileRow.removeAttribute('disabled');
  $fileRow.classList.remove('d-none');
  $fileDiv.remove();
});
