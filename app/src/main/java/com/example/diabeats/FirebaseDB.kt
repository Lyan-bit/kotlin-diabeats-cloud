package com.example.diabeats

import com.google.firebase.database.*
import kotlin.collections.ArrayList

class FirebaseDB() {

    var database: DatabaseReference? = null

    companion object {
        private var instance: FirebaseDB? = null
        fun getInstance(): FirebaseDB {
            return instance ?: FirebaseDB()
        }
    }

    init {
        connectByURL("https://diabeats-8bcc9-default-rtdb.firebaseio.com/")
    }

    fun connectByURL(url: String) {
        database = FirebaseDatabase.getInstance(url).reference
        if (database == null) {
            return
        }
        val diabeatsListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get instances from the cloud database
                val diabeatss = dataSnapshot.value as HashMap<String, Object>?
                if (diabeatss != null) {
                    val keys = diabeatss.keys
                    for (key in keys) {
                        val x = diabeatss[key]
                        DiabeatsDAO.parseRaw(x)
                    }
                    // Delete local objects which are not in the cloud:
                    val locals = ArrayList<Diabeats>()
                    locals.addAll(Diabeats.DiabeatsAllInstances)
                    for (x in locals) {
                        if (keys.contains(x.id)) {
                        } else {
                            Diabeats.killDiabeats(x.id)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            	//onCancelled
            }
        }
        database!!.child("diabeatss").addValueEventListener(diabeatsListener)
    }

    fun persistDiabeats(ex: Diabeats) {
        val evo = DiabeatsVO(ex)
        val key = evo.getId()
        if (database == null) {
            return
        }
        database!!.child("diabeatss").child(key).setValue(evo)
    }

    fun deleteDiabeats(ex: Diabeats) {
        val key: String = ex.id
        if (database == null) {
            return
        }
        database!!.child("diabeatss").child(key).removeValue()
    }
}
