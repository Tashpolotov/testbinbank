package kg.aza.testbinbank.core

import android.content.Context
import android.content.Intent
import android.net.Uri

fun OpenMap(context: Context, latitude: Int?, longitude: Int?) {
    val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}