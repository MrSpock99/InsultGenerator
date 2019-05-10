package itis.ru.insultgenerator

import io.reactivex.Single
import itis.ru.insultgenerator.model.Insult
import itis.ru.insultgenerator.model.InsultInteractor
import itis.ru.insultgenerator.model.SettingsInteractor
import itis.ru.insultgenerator.presenter.InsultListActivityPresenter
import itis.ru.insultgenerator.view.`InsultListView$$State`
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class InsultListActivityPresenterTest {
    private lateinit var presenter: InsultListActivityPresenter
    @Mock
    private lateinit var view: `InsultListView$$State`
    @Mock
    private lateinit var insultInteractor: InsultInteractor
    @Mock
    private lateinit var settingsInteractor: SettingsInteractor
    @Mock
    private lateinit var router: Router

    private val testList: MutableList<Insult> = listOf(Insult(), Insult()).toMutableList()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(insultInteractor.getInsultListAsync()).thenReturn(Single.just(testList))
        `when`(insultInteractor.getInsultListFromDb()).thenReturn(Single.just(testList))
        `when`(settingsInteractor.getPaginationSize()).thenReturn(0)
        doNothing().`when`(settingsInteractor).savePaginationSize(ArgumentMatchers.isA(Int::class.java))
        presenter = InsultListActivityPresenter(insultInteractor, settingsInteractor, router)
        presenter.setViewState(view)
    }

    @Test
    fun testUpdateInsultListWhenSuccess() {
        presenter.updateInsultList()
        verify(insultInteractor).getInsultListAsync()
        verify(view).updateListView(Mockito.anyList())
    }

    @Test
    fun testUpdateInsultListFromCacheWhenSuccess() {
        presenter.updateInsultListFromCache()
        verify(insultInteractor).getInsultListFromDb()
        verify(view).updateListView(Mockito.anyList())
    }

    @Test
    fun testOnInsultClick() {
        val insultMock = mock(Insult::class.java)
        presenter.onInsultClick(insultMock)
        verify(view).navigateToDetails(insultMock)
    }

    @Test
    fun testPaginationWhenUserReachEndOfList() {
        presenter.loadNextElements(1)
        verify(view).addItemsToListView(anyList())
    }
}
