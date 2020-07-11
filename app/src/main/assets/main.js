"use strict"

var size = Math.round(android.getFontSize() * 100)
sizeslider.value = size
sizeoutput.value = size

function settingsClosed() {
 if(explanation.open && android.canModifySettings()) {
  explanation.close()
} }

if(android.canModifySettings() == false) {
 explanation.showModal()
}

grant.addEventListener("click", function() {
 android.openSettingsPermission()
}, { passive: true })

grant.addEventListener("click", function() {
 setInterval(settingsClosed, 100)
}, { passive: true, once: true })

explanation.addEventListener("cancel", function(event) {
 event.preventDefault()
})

sizeslider.addEventListener("input", function() {
 sizeoutput.value = sizeslider.valueAsNumber
 android.setFontSize(sizeslider.valueAsNumber / 100)
})