package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<S> : ViewModel() {
    private val composeDisposable = CompositeDisposable()

    protected open val status = MutableLiveData<S>()
    val observableStatus: LiveData<S>
        get() = status

    protected val action = MutableLiveData<TibiaAction>()
    val observableAction: LiveData<TibiaAction> = action

    private val loading = MutableLiveData(false)
    val observableLoading: LiveData<Boolean> = loading

    protected fun Disposable.toDisposable(): Disposable =
        apply { composeDisposable.add(this) }

    override fun onCleared() {
        super.onCleared()
        composeDisposable.dispose()
    }

    protected fun showLoading() {
        loading.value = true
    }

    protected fun hideLoading() {
        loading.value = false
    }
}