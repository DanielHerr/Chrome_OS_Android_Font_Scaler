"use strict"

if(android.canModifySettings() == false) {
 explanation.showModal()
}

var size = Math.round(android.getFontSize() * 100)
sizeslider.value = size
sizeoutput.value = size

grant.addEventListener("click", function() {
 android.openSettingsPermission()
})

explanation.addEventListener("cancel", function(event) {
 event.preventDefault()
})

sizeslider.addEventListener("input", function() {
 sizeoutput.value = sizeslider.valueAsNumber
 android.setFontSize(sizeslider.valueAsNumber / 100)
})

function settingsClosed() {
 if(explanation.open && android.canModifySettings()) {
  explanation.close()
 }
}