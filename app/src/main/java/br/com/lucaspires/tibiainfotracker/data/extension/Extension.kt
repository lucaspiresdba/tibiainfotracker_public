package br.com.lucaspires.tibiainfotracker.data.extension

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.defaultSchedulers(): Single<T> =
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())