package com.example.diabeats

import java.util.ArrayList

class DiabeatsVO  {

        var id: String = ""
     var age: Int = 0
     var bmi: Float = 0.0F
     var glucose: Float = 0.0F
     var insulin: Float = 0.0F
     var homa: Float = 0.0F
     var leptin: Float = 0.0F
     var adiponectin: Float = 0.0F
     var resistin: Float = 0.0F
     var mcp: Float = 0.0F
     var outcome: String = ""

    constructor() {
    	//constructor
    }

    constructor(idx: String, 
        agex: Int, 
        bmix: Float, 
        glucosex: Float, 
        insulinx: Float, 
        homax: Float, 
        leptinx: Float, 
        adiponectinx: Float, 
        resistinx: Float, 
        mcpx: Float, 
        outcomex: String
        ) {
        this.id = idx
        this.age = agex
        this.bmi = bmix
        this.glucose = glucosex
        this.insulin = insulinx
        this.homa = homax
        this.leptin = leptinx
        this.adiponectin = adiponectinx
        this.resistin = resistinx
        this.mcp = mcpx
        this.outcome = outcomex
    }

    constructor (x: BreastCancer) {
        id = x.id
        age = x.age
        bmi = x.bmi
        glucose = x.glucose
        insulin = x.insulin
        homa = x.homa
        leptin = x.leptin
        adiponectin = x.adiponectin
        resistin = x.resistin
        mcp = x.mcp
        outcome = x.outcome
    }

    override fun toString(): String {
        return "id = $id,age = $age,bmi = $bmi,glucose = $glucose,insulin = $insulin,homa = $homa,leptin = $leptin,adiponectin = $adiponectin,resistin = $resistin,mcp = $mcp,outcome = $outcome"
    }

    fun toStringList(list: List<BreastCancerVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    
    
}
