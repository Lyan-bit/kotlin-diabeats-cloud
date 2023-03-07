package com.example.diabeats

import java.util.HashMap

class Diabeats {

    init {
        DiabeatsAllInstances.add(this)
    }

    companion object {
        var DiabeatsAllInstances = ArrayList<Diabeats>()
        fun createDiabeats(): Diabeats {
            return Diabeats()
        }
        
        var DiabeatsIndex: HashMap<String, Diabeats> = HashMap<String, Diabeats>()
        
        fun createByPKDiabeats(idx: String): Diabeats {
            var result: Diabeats? = DiabeatsIndex[idx]
            if (result != null) { return result }
                  result = Diabeats()
                  DiabeatsIndex.put(idx,result)
                  result.id = idx
                  return result
        }
        
		fun killDiabeats(idx: String?) {
            val rem = DiabeatsIndex[idx] ?: return
            val remd = ArrayList<Diabeats>()
            remd.add(rem)
            DiabeatsIndex.remove(idx)
            DiabeatsAllInstances.removeAll(remd)
        }        
    }

    var id = ""  /* identity */
    var pregnancies = 0 
    var glucose = 0 
    var bloodPressure = 0 
    var skinThickness = 0 
    var insulin = 0 
    var bmi = 0.0 
    var diabetesPedigreeFunction = 0.0 
    var age = 0 
    var outcome = ""  /* derived */

}
