package it.shu2019.notepad

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

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
    }

    private fun onPostitAddFailure(error: Exception) {
        Log.e("PostitRepository", "Il postit non è stato sincronizzato")
    }

}