package br.com.lucaspires.tibiainfotracker.data.remote.exception

import java.io.IOException

sealed class TibiaException : Exception() {
    object NoConnection : IOException()
}
