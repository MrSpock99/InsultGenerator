package itis.ru.insultgenerator

import itis.ru.insultgenerator.presenter.InsultActivityPresenter
import itis.ru.insultgenerator.view.InsultView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class InsultActivityPresenterTest {
    private lateinit var presenter: InsultActivityPresenter
    @Mock
    private lateinit var view: InsultView
    private val testString: String = "Hello, world!"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = InsultActivityPresenter()
        presenter.attachView(view)
        presenter.setInsultTv(testString)
    }

    @Test
    fun testSetInsultTv() {
        verify(view).updateInsultTextView(testString)
    }
}
