package com.example.diabeats

import android.content.Context
import java.lang.Exception

class DiabeatsBean(c: Context) {

    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var id = ""
    private var pregnancies = ""
    private var dpregnancies = 0
    private var glucose = ""
    private var dglucose = 0
    private var bloodPressure = ""
    private var dbloodPressure = 0
    private var skinThickness = ""
    private var dskinThickness = 0
    private var insulin = ""
    private var dinsulin = 0
    private var bmi = ""
    private var dbmi = 0.0
    private var diabetesPedigreeFunction = ""
    private var ddiabetesPedigreeFunction = 0.0
    private var age = ""
    private var dage = 0
    private var outcome = ""

    private var errors = ArrayList<String>()

    fun setId(idx: String) {
	 id = idx
    }
    
    fun setPregnancies(pregnanciesx: String) {
	 pregnancies = pregnanciesx
    }
    
    fun setGlucose(glucosex: String) {
	 glucose = glucosex
    }
    
    fun setBloodPressure(bloodPressurex: String) {
	 bloodPressure = bloodPressurex
    }
    
    fun setSkinThickness(skinThicknessx: String) {
	 skinThickness = skinThicknessx
    }
    
    fun setInsulin(insulinx: String) {
	 insulin = insulinx
    }
    
    fun setBmi(bmix: String) {
	 bmi = bmix
    }
    
    fun setDiabetesPedigreeFunction(diabetesPedigreeFunctionx: String) {
	 diabetesPedigreeFunction = diabetesPedigreeFunctionx
    }
    
    fun setAge(agex: String) {
	 age = agex
    }
    
    fun setOutcome(outcomex: String) {
	 outcome = outcomex
    }
    


    fun resetData() {
	  id = ""
	  pregnancies = ""
	  glucose = ""
	  bloodPressure = ""
	  skinThickness = ""
	  insulin = ""
	  bmi = ""
	  diabetesPedigreeFunction = ""
	  age = ""
	  outcome = ""
    }
    
    fun isCreateDiabeatsError(): Boolean {
	        
	 errors.clear()
	        
          if (id != "") {
	//validate
}
	else {
	 	  errors.add("id cannot be empty")
	}
    try {
	  dpregnancies = pregnancies.toInt()
	} catch (e: Exception) {
	  errors.add("pregnancies is not a Int")
	}
    try {
	  dglucose = glucose.toInt()
	} catch (e: Exception) {
	  errors.add("glucose is not a Int")
	}
    try {
	  dbloodPressure = bloodPressure.toInt()
	} catch (e: Exception) {
	  errors.add("bloodPressure is not a Int")
	}
    try {
	  dskinThickness = skinThickness.toInt()
	} catch (e: Exception) {
	  errors.add("skinThickness is not a Int")
	}
    try {
	  dinsulin = insulin.toInt()
	} catch (e: Exception) {
	  errors.add("insulin is not a Int")
	}
    try {
	  dbmi = bmi.toDouble()
	} catch (e: Exception) {
	  errors.add("bmi is not a Double")
	}
    try {
	  ddiabetesPedigreeFunction = diabetesPedigreeFunction.toDouble()
	} catch (e: Exception) {
	  errors.add("diabetesPedigreeFunction is not a Double")
	}
    try {
	  dage = age.toInt()
	} catch (e: Exception) {
	  errors.add("age is not a Int")
	}

	        return errors.size > 0
	    }
	    
	    fun createDiabeats() {
	        model.createDiabeats(DiabeatsVO(id, dpregnancies, dglucose, dbloodPressure, dskinThickness, dinsulin, dbmi, ddiabetesPedigreeFunction, dage, outcome))
	        resetData()
	    }

    fun isListDiabeatsError(): Boolean {
	        errors.clear()
	        return errors.size > 0
	    }


		fun isSearchDiabeatsIdError(allDiabeatsIds: List<String>): Boolean {
    	   errors.clear()
   	       if (!allDiabeatsIds.contains(id)) {
    	       errors.add("The id is not exist")
    	   }
           return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }



}

