package itis.ru.insultgenerator.view

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import itis.ru.insultgenerator.R
import itis.ru.insultgenerator.di.component.DaggerActivityComponent
import itis.ru.insultgenerator.di.module.PresenterModule
import itis.ru.insultgenerator.presenter.InsultActivityPresenter
import kotlinx.android.synthetic.main.activity_insult.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Back
import javax.inject.Inject

class InsultActivity : MvpAppCompatActivity(), InsultView {
    @Inject
    @InjectPresenter
    lateinit var presenter: InsultActivityPresenter

    @ProvidePresenter
    fun provideInsultActivityPresenter() = presenter

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private val navigator = Navigator {
        if (it[0] is Back) {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insult)
        init()
    }

    override fun updateInsultTextView(text: String) {
        tv_insult.text = text
    }

    override fun onSupportNavigateUp(): Boolean {
        presenter.onBackPressed()
        return true
    }

    private fun init() {
        val insult: String = intent.extras[InsultListActivity.EXTRA_INSULT] as String
        presenter.setInsultTv(insult)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .presenterModule(PresenterModule(this))
                .build()
        activityComponent.inject(this)
    }
}
