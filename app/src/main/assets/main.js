"use strict"

if(android.canModifySettings() == false) {
 explanation.showModal()
}

grant.addEventListener("click", function() {
 android.openSettingsPermission()
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