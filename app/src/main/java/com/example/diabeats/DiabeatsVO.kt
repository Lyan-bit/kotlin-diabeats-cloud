package com.example.diabeats

import java.util.ArrayList

class DiabeatsVO  {

 var id: String = ""
     var pregnancies: Int = 0
     var glucose: Int = 0
     var bloodPressure: Int = 0
     var skinThickness: Int = 0
     var insulin: Int = 0
     var bmi: Double = 0.0
     var diabetesPedigreeFunction: Double = 0.0
     var age: Int = 0
     var outcome: String = ""

    constructor() {
    	//constructor
    }

    constructor(idx: String, 
        pregnanciesx: Int, 
        glucosex: Int, 
        bloodPressurex: Int, 
        skinThicknessx: Int, 
        insulinx: Int, 
        bmix: Double, 
        diabetesPedigreeFunctionx: Double, 
        agex: Int, 
        outcomex: String
        ) {
        this.id = idx
        this.pregnancies = pregnanciesx
        this.glucose = glucosex
        this.bloodPressure = bloodPressurex
        this.skinThickness = skinThicknessx
        this.insulin = insulinx
        this.bmi = bmix
        this.diabetesPedigreeFunction = diabetesPedigreeFunctionx
        this.age = agex
        this.outcome = outcomex
    }

    constructor (x: Diabeats) {
        id = x.id
        pregnancies = x.pregnancies
        glucose = x.glucose
        bloodPressure = x.bloodPressure
        skinThickness = x.skinThickness
        insulin = x.insulin
        bmi = x.bmi
        diabetesPedigreeFunction = x.diabetesPedigreeFunction
        age = x.age
        outcome = x.outcome
    }

    override fun toString(): String {
        return "id = $id,pregnancies = $pregnancies,glucose = $glucose,bloodPressure = $bloodPressure,skinThickness = $skinThickness,insulin = $insulin,bmi = $bmi,diabetesPedigreeFunction = $diabetesPedigreeFunction,age = $age,outcome = $outcome"
    }

    fun toStringList(list: List<DiabeatsVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    
}
