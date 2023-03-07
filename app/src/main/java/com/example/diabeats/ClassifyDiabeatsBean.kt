package com.example.diabeats

import android.content.Context
import java.util.*

class ClassifyDiabeatsBean(c: Context) {
    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var diabeats = ""
	private var instanceDiabeats: Diabeats? = null
	

    private var errors = ArrayList<String>()

    fun setDiabeats(diabeatsx: String) {
        diabeats = diabeatsx
    }
    

    fun resetData() {
        diabeats = ""
    }

    fun isClassifyDiabeatsError(): Boolean {
        errors.clear()
        instanceDiabeats = model.getDiabeatsByPK(diabeats)
        if (instanceDiabeats == null) {
            errors.add("diabeats must be a valid Diabeats id")
        }
        

        return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }

     fun classifyDiabeats (): String {
        return model.classifyDiabeats(instanceDiabeats!!)
     }
}
