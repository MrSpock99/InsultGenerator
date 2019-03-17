package itis.ru.insultgenerator

import itis.ru.insultgenerator.model.SettingsInteractor
import itis.ru.insultgenerator.presenter.SettingsActivityPresenter
import itis.ru.insultgenerator.view.`SettingsView$$State`
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SettingsActivityPresenterTest {
    private lateinit var presenter: SettingsActivityPresenter
    @Mock
    private lateinit var view: `SettingsView$$State`
    @Mock
    private lateinit var settingsInteractor: SettingsInteractor

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SettingsActivityPresenter(settingsInteractor)
        presenter.setViewState(view)
        `when`(settingsInteractor.getPaginationSize()).thenReturn(1)
    }

    @Test
    fun showPaginationDialogWhenNeed() {
        presenter.showPaginationDialog()
        verify(view).showPaginationDialog(anyInt())
    }
}