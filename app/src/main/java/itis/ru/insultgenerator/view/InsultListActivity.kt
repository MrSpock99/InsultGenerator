package itis.ru.insultgenerator.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import itis.ru.insultgenerator.InsultAdapter
import itis.ru.insultgenerator.R
import itis.ru.insultgenerator.di.component.DaggerActivityComponent
import itis.ru.insultgenerator.di.module.PresenterModule
import itis.ru.insultgenerator.model.Insult
import itis.ru.insultgenerator.presenter.InsultListActivityPresenter
import kotlinx.android.synthetic.main.activity_insult_list.*
import javax.inject.Inject

class InsultListActivity : MvpAppCompatActivity(), InsultListView {
    @Inject
    @InjectPresenter
    lateinit var presenter: InsultListActivityPresenter

    @ProvidePresenter
    fun provideInsultListActivityPresenter() = presenter

    private var insultAdapter: InsultAdapter? = null
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insult_list)
        swipeContainer.setOnRefreshListener {
            refreshInsulsts()
        }
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_settings ->
                presenter.onMenuItemClick()
        }
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        presenter.updateInsultListFromCache()
    }

    override fun updateListView(list: MutableList<Insult>) {
        insultAdapter?.submitList(list)
    }

    override fun addItemsToListView(list: List<Insult>) {
        val insultList = insultAdapter?.list
        insultList?.addAll(list)
        insultAdapter?.submitList(insultList)
    }

    override fun hideProgress() {
        swipeContainer.isRefreshing = false
        isLoading = false
    }

    override fun navigateToDetails(insult: Insult) {
        val intent = Intent(this@InsultListActivity, InsultActivity::class.java)
        intent.putExtra(EXTRA_INSULT, insult.insult)
        startActivity(intent)
    }

    override fun navigateToSettings() {
        val intent = Intent(this@InsultListActivity, SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun showProgress() {
        swipeContainer.isRefreshing = true
        isLoading = true
    }

    private fun init() {
        val linearLayoutManager = LinearLayoutManager(this)
        rv_insults.apply {
            layoutManager = linearLayoutManager
        }
        rv_insults.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var currentPage = 0
            private val isLastPage = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = linearLayoutManager.childCount
                val totalItemCount: Int = linearLayoutManager.itemCount
                val firstVisibleItemPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()
                val paginationSize: Int = presenter.getPaginationSize()
                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= paginationSize
                    ) {
                        isLoading = true
                        presenter.loadNextElements(++currentPage)
                    }
                }
            }
        })
        initAdapter()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .presenterModule(PresenterModule(this))
            .build()
        activityComponent.inject(this)
    }

    private fun initAdapter() {
        insultAdapter = InsultAdapter {
            presenter.onInsultClick(it)
        }
        rv_insults.adapter = insultAdapter
        presenter.updateInsultList()
    }

    //вызвать при pull refresh
    private fun refreshInsulsts() {
        presenter.updateInsultList()
    }

    companion object {
        const val EXTRA_INSULT: String = "EXTRA_INSULT"
    }
}
