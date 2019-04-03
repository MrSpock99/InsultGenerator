package itis.ru.insultgenerator.utils

import android.content.Context
import android.content.Intent
import itis.ru.insultgenerator.view.InsultActivity
import itis.ru.insultgenerator.view.InsultListActivity
import itis.ru.insultgenerator.view.SettingsActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class InsultScreen(private val insult: String) : SupportAppScreen() {
        override fun getActivityIntent(context: Context?): Intent {
            val intent = Intent(context, InsultActivity::class.java)
            intent.putExtra(InsultListActivity.EXTRA_INSULT, insult)
            return intent
        }

        override fun getScreenKey(): String {
            return INSULT_SCREEN
        }
    }

    class SettingsScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context?): Intent {
           return Intent(context, SettingsActivity::class.java)
        }

        override fun getScreenKey(): String {
            return SETTINGS_SCREEN
        }
    }

    companion object {
        const val INSULT_SCREEN = "INSULT_SCREEN"
        const val SETTINGS_SCREEN = "SETTINGS_SCREEN"
    }
}
