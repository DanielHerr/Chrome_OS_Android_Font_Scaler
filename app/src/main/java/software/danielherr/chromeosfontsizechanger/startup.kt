package software.danielherr.chromeosfontsizechanger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.preference.PreferenceManager

class startup : BroadcastReceiver() {
 override fun onReceive(context: Context, intent: Intent) {
  if(Settings.System.canWrite(context)) {
   var size = PreferenceManager.getDefaultSharedPreferences(context).getFloat("font_scale", 1F)
   Settings.System.putFloat(context.contentResolver, "font_scale", size)
  }
 }
}