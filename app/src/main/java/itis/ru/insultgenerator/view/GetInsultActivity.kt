package itis.ru.insultgenerator.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import itis.ru.insultgenerator.R
import itis.ru.insultgenerator.di.component.DaggerActivityComponent
import itis.ru.insultgenerator.di.module.AppModule
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
            viewModel.getInsultClick()
        }
        viewModel.insultLiveData.observe(this, Observer {
            pb_dots.visibility = View.GONE
            tv_insult.text = it
        })
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .appModule(AppModule(this))
            .build()
        activityComponent.inject(this)
    }
}
