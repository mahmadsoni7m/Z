package tj.soni.z

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent

class ZAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Z ҳоло рӯйдодҳои экранро назорат намекунад.
    }

    override fun onInterrupt() {}

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onDestroy() {
        if (instance === this) {
            instance = null
        }
        super.onDestroy()
    }

    private fun runCommand(command: String): Boolean {
        return when (command.lowercase().trim()) {

            "back" -> performGlobalAction(
                GLOBAL_ACTION_BACK
            )

            "home" -> performGlobalAction(
                GLOBAL_ACTION_HOME
            )

            "notifications" -> performGlobalAction(
                GLOBAL_ACTION_NOTIFICATIONS
            )

            "quick_settings" -> performGlobalAction(
                GLOBAL_ACTION_QUICK_SETTINGS
            )

            "recent" -> performGlobalAction(
                GLOBAL_ACTION_RECENTS
            )

            else -> false
        }
    }

    companion object {
        @Volatile
        private var instance: ZAccessibilityService? = null

        fun execute(command: String): Boolean {
            return instance?.runCommand(command) ?: false
        }
    }
}
