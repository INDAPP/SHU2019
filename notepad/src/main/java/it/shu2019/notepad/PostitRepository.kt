package it.shu2019.notepad

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-18.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
object PostitRepository {
    private var list: MutableList<Postit> = mutableListOf()

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

    private fun onPostitAddSuccess(ref: DocumentReference) {
        Log.d("PostitRepository", "Il postit è stato sincronizzato")
    }

    private fun onPostitAddFailure(error: Exception) {
        Log.e("PostitRepository", "Il postit non è stato sincronizzato")
    }

}