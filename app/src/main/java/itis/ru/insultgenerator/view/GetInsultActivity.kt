package itis.ru.insultgenerator.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import itis.ru.insultgenerator.R
import itis.ru.insultgenerator.di.component.DaggerActivityComponent
import itis.ru.insultgenerator.di.module.AppModule
import itis.ru.insultgenerator.utils.Ad
import itis.ru.insultgenerator.utils.ViewModelFactory
import itis.ru.insultgenerator.viewmodel.GetInsultViewModel
import kotlinx.android.synthetic.main.activity_get_insult.*
import javax.inject.Inject

class GetInsultActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private lateinit var viewModel: GetInsultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_insult)

        viewModel =
            ViewModelProviders.of(this, this.viewModeFactory)
                .get(GetInsultViewModel::class.java)

        btn_get_insult.setOnClickListener {
            pb_dots.visibility = View.VISIBLE
            tv_insult.text = ""
            viewModel.getInsultClick()
        }

        tv_insult.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.primaryClip = ClipData.newPlainText("", tv_insult.text.toString())
            Toast.makeText(this, "Text is copied", Toast.LENGTH_SHORT).show()
        }

        viewModel.insultLiveData.observe(this, Observer {
            pb_dots.visibility = View.GONE
            tv_insult.text = it
        })

        Ad.showBannerAd(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_privacy_policy -> {
                startActivity(Intent(this, PrivacyPolicyActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .appModule(AppModule(this))
            .build()
        activityComponent.inject(this)
    }
}
