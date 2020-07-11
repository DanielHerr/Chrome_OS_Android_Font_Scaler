package software.danielherr.fontscaler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.edit
import androidx.preference.PreferenceManager

lateinit var webview: WebView

class container {
 var context: Context
 var activity: Activity
 constructor(context: Context) {
  this.context = context
  this.activity = context as Activity
 }
 @JavascriptInterface fun getFontSize(): Float {
  return(Settings.System.getFloat(context.contentResolver, "font_scale"))
 }
 @JavascriptInterface fun setFontSize(size: Float) {
  Settings.System.putFloat(context.contentResolver, "font_scale", size)
  PreferenceManager.getDefaultSharedPreferences(context).edit {
   putFloat("font_scale", size)
  }
 }
 @JavascriptInterface fun canModifySettings(): Boolean {
  return(Settings.System.canWrite(context))
 }
 @JavascriptInterface fun openSettingsPermission() {
  var prompt = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
  prompt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  prompt.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
  prompt.data = Uri.parse("package:" + context.packageName)
  startActivityForResult(activity, prompt, 100, null)
 }
}

class MainActivity : AppCompatActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)

  WebView.setWebContentsDebuggingEnabled(true)
  webview = WebView(this)
  setContentView(webview)
  webview.settings.javaScriptEnabled = true
  webview.addJavascriptInterface(container(this), "android")
  webview.loadUrl("file:///android_asset/main.html")
 }
 override fun onActivityResult(request: Int, result: Int, data: Intent?) {
  super.onActivityResult(request, result, data)
  webview.evaluateJavascript("settingsClosed()", null)
 }
 override fun onResume() {
  super.onResume()
  webview.evaluateJavascript("settingsClosed()", null)
 }
}