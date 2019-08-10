package itis.ru.insultgenerator.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import itis.ru.insultgenerator.model.InsultInteractor
import javax.inject.Inject

class GetInsultViewModel @Inject constructor(
    private val insultInteractor: InsultInteractor
) : ViewModel() {

    val insultLiveData: MutableLiveData<String> = MutableLiveData()

    fun getInsultClick() {
        insultInteractor.getInsult()
            .subscribe({
                insultLiveData.value = it.insult
            }, {
                insultLiveData.value = "FUCK U"
            })
    }
}
