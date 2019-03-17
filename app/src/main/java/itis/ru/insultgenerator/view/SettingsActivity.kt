package itis.ru.insultgenerator.view

import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import itis.ru.insultgenerator.R
import itis.ru.insultgenerator.model.SettingsInteractor
import itis.ru.insultgenerator.presenter.SettingsActivityPresenter
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.dialog_pagination.view.*

class SettingsActivity : MvpAppCompatActivity(), SettingsView {
    @InjectPresenter
    lateinit var presenter: SettingsActivityPresenter

    @ProvidePresenter
    fun provideSettingsActivityPresenter(): SettingsActivityPresenter {
        return SettingsActivityPresenter(SettingsInteractor())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        init()
    }

    override fun showPaginationDialog(paginationSize: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_pagination, null)
        dialogView.et_pagination_size.setText(paginationSize.toString())
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle(getString(R.string.pagination_dialog_title))
        builder.setPositiveButton(getString(R.string.all_ok)) { _, _ ->
            presenter.setPaginationSize(dialogView.et_pagination_size.text.toString().toInt())
        }
        builder.show()
    }

    private fun init() {
        tv_pagination_settings.setOnClickListener {
            presenter.showPaginationDialog()
        }
    }
}
