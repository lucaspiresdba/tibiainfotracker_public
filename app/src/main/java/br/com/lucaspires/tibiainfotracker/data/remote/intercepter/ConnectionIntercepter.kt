package br.com.lucaspires.tibiainfotracker.data.remote.intercepter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

private const val GOOGLE_DNS = "8.8.8.8"

internal class ConnectionIntercepter(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (isNetworkConnected() && isInternetAvailable()) chain.proceed(chain.request()) else throw TibiaException.NoConnection
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val timeoutMs = 1500
            val socket = Socket()
            socket.connect(InetSocketAddress(GOOGLE_DNS, 53), timeoutMs)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}

