package com.example.diabeats

import java.util.ArrayList

class DiabeatsVO  {

    private var id: String = ""
    private var pregnancies: Int = 0
    private var glucose: Int = 0
    private var bloodPressure: Int = 0
    private var skinThickness: Int = 0
    private var insulin: Int = 0
    private var bmi: Double = 0.0
    private var diabetesPedigreeFunction: Double = 0.0
    private var age: Int = 0
    private var outcome: String = ""

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
    
    fun getId(): String {
        return id
    }
    
    fun getPregnancies(): Int {
        return pregnancies
    }
    
    fun getGlucose(): Int {
        return glucose
    }
    
    fun getBloodPressure(): Int {
        return bloodPressure
    }
    
    fun getSkinThickness(): Int {
        return skinThickness
    }
    
    fun getInsulin(): Int {
        return insulin
    }
    
    fun getBmi(): Double {
        return bmi
    }
    
    fun getDiabetesPedigreeFunction(): Double {
        return diabetesPedigreeFunction
    }
    
    fun getAge(): Int {
        return age
    }
    
    fun getOutcome(): String {
        return outcome
    }
    

    fun setId(x: String) {
    	id = x
    }
    
    fun setPregnancies(x: Int) {
    	pregnancies = x
    }
    
    fun setGlucose(x: Int) {
    	glucose = x
    }
    
    fun setBloodPressure(x: Int) {
    	bloodPressure = x
    }
    
    fun setSkinThickness(x: Int) {
    	skinThickness = x
    }
    
    fun setInsulin(x: Int) {
    	insulin = x
    }
    
    fun setBmi(x: Double) {
    	bmi = x
    }
    
    fun setDiabetesPedigreeFunction(x: Double) {
    	diabetesPedigreeFunction = x
    }
    
    fun setAge(x: Int) {
    	age = x
    }
    
    fun setOutcome(x: String) {
    	outcome = x
    }
    
}
