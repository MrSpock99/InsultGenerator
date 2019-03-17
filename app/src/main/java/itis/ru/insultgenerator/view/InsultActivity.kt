package itis.ru.insultgenerator.view

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import itis.ru.insultgenerator.R
import itis.ru.insultgenerator.presenter.InsultActivityPresenter
import kotlinx.android.synthetic.main.activity_insult.*

class InsultActivity : MvpAppCompatActivity(), InsultView {
    @InjectPresenter
    lateinit var presenter: InsultActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insult)
        init()
    }

    override fun updateInsultTextView(text: String) {
        tv_insult.text = text
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun init() {
        val insult: String = intent.extras[InsultListActivity.EXTRA_INSULT] as String
        presenter.setInsultTv(insult)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
