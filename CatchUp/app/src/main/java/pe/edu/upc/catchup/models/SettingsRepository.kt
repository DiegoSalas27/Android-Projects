package pe.edu.upc.catchup.models

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SettingsRepository(context: Context) {
    val context: Context? = context
    var didShowOnBoarding: Boolean
    get() {
        context?.apply {
            return getSharedPreferences("catch_up", MODE_PRIVATE)
                .getBoolean("didShowOnBoarding", false)

        }
        return false
    }
    set(value)  {
        context?.apply {
            getSharedPreferences("catch_up", MODE_PRIVATE)
                .edit()
                .putBoolean("didShowOnBoarding", value)
                .apply()
        }
    }

    var shouldShowOnBoarding: Boolean
    get() = !didShowOnBoarding
    set(value) { didShowOnBoarding = !value }
}