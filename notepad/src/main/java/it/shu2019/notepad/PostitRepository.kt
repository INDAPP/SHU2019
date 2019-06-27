package it.shu2019.notepad

import android.util.Log
import com.google.firebase.firestore.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-18.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
object PostitRepository {
    private var list: MutableList<Postit> = mutableListOf()

    var postitRepositoryChangeListener: ((List<Postit>) -> Unit)? = null

    init {
        val db = FirebaseFirestore.getInstance()
        val postitCollection = db.collection("postit")

        postitCollection.addSnapshotListener(this::onPostitListUpdate)
    }

    fun add(postit: Postit) {
        list.add(postit)

        val db = FirebaseFirestore.getInstance()
        val postitCollection = db.collection("postit")
        postitCollection.add(postit)
            .addOnSuccessListener(this::onPostitAddSuccess)
            .addOnFailureListener(this::onPostitAddFailure)
    }

    fun getList(): List<Postit> {
        return list
    }

    private fun onPostitListUpdate(querySnapshot: QuerySnapshot?, error: FirebaseFirestoreException?) {
        val postitSnapshots = querySnapshot?.documents ?: return
        val postitList = postitSnapshots.map { snapshot -> snapshot.toObject(Postit::class.java) }
        this.list = postitList.filterNotNull().toMutableList()
        postitRepositoryChangeListener?.invoke(this.list)
        Log.d("PostitRepository", "La lista di postit è stata aggiornata")
    }

    private fun onPostitAddSuccess(ref: DocumentReference) {
        Log.d("PostitRepository", "Il postit è stato sincronizzato")
        ref.get()
            .addOnSuccessListener(this::onPostitGetSuccess)
            .addOnFailureListener(this::onPostitGetFailure)
    }

    private fun onPostitAddFailure(error: Exception) {
        Log.e("PostitRepository", "Il postit non è stato sincronizzato")
    }

    private fun onPostitGetSuccess(snapshot: DocumentSnapshot) {
        Log.d("PostitRepository", "Il postit è stato recuperato")
        val postit = snapshot.toObject(Postit::class.java) ?: return
        val pushNotification = PushNotification(
            notification = Notification(
                "Una nuova nota è stata aggiunta",
                postit.title
            ),
            condition = "'postit' in topics"
        )
        Client.firebaseHttpService.send(
            pushNotification,
            authorization = "key=$FirebaseServerKey"
        ).enqueue(notificationCallback)
    }

    private fun onPostitGetFailure(error: Exception) {
        Log.e("PostitRepository", "Il postit non è stato recuperato")
    }

    private val notificationCallback = object: Callback<Message> {

        override fun onFailure(call: Call<Message>, t: Throwable) {
            Log.e("PostitRepository", "La notifica push non è stata inviata")
        }

        override fun onResponse(call: Call<Message>, response: Response<Message>) {
            Log.i("PostitRepository", "La notifica push è stata inviata")
        }

    }

}

//TODO: modificare prima di avviare
const val FirebaseServerKey = "<inserire la stringa di autorizzazione>"