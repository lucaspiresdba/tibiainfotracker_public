package br.com.lucaspires.tibiainfotracker.data.firebase

import br.com.lucaspires.tibiainfotracker.data.remote.exception.TibiaException
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import io.reactivex.SingleEmitter

internal const val NO_CONNECTION_FIREBASE_ERROR = "Client is offline"

internal class FirebaseListeners(private val stream: SingleEmitter<List<String>>) :
    OnSuccessListener<DataSnapshot>,
    OnFailureListener {
    override fun onSuccess(data: DataSnapshot?) {
        val worldList = arrayListOf<String>()
        data?.children?.forEach { world ->
            world.getValue(String::class.java)?.let {
                worldList.add(it)
            }
        }
        stream.onSuccess(worldList)
    }

    override fun onFailure(ex: Exception) {
        ex.message?.let {
            if (it.contains(NO_CONNECTION_FIREBASE_ERROR)) {
                stream.onError(TibiaException.NoConnection)
                return
            }
        }
        stream.onError(ex)
    }
}