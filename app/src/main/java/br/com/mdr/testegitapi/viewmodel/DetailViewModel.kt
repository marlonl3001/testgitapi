package br.com.mdr.testegitapi.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import br.com.mdr.testegitapi.model.Repository

class DetailViewModel : ViewModel() {
    var repository = MutableLiveData<Repository>()

}
