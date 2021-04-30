package br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action

sealed class TibiaAction {
    object GenericError : TibiaAction()
    object NoInternet : TibiaAction()
    object NotFoundData : TibiaAction()
}