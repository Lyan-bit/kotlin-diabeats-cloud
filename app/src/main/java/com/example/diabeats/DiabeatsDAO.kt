package com.example.diabeats

import org.json.JSONObject
import java.lang.Exception
import org.json.JSONArray
import kotlin.collections.ArrayList

class DiabeatsDAO {

    companion object {

        fun getURL(command: String?, pars: ArrayList<String>, values: ArrayList<String>): String {
            var res = "base url for the data source"
            if (command != null) {
                res += command
            }
            if (pars.isEmpty()) {
                return res
            }
            res = "$res?"
            for (item in pars.indices) {
                val par = pars[item]
                val vals = values[item]
                res = "$res$par=$vals"
                if (item < pars.size - 1) {
                    res = "$res&"
                }
            }
            return res
        }

        fun isCached(id: String?): Boolean {
            Diabeats.DiabeatsIndex[id] ?: return false
            return true
        }

        fun getCachedInstance(id: String): Diabeats? {
            return Diabeats.DiabeatsIndex[id]
        }

      fun parseCSV(line: String?): Diabeats? {
          if (line == null) {
              return null
          }
          val line1vals: ArrayList<String> = Ocl.tokeniseCSV(line)
          var diabeatsx: Diabeats? = Diabeats.DiabeatsIndex[line1vals[0]]
          if (diabeatsx == null) {
              diabeatsx = Diabeats.createByPKDiabeats(line1vals[0])
          }
          diabeatsx.id = line1vals[0].toString()
          diabeatsx.pregnancies = line1vals[1].toInt()
          diabeatsx.glucose = line1vals[2].toInt()
          diabeatsx.bloodPressure = line1vals[3].toInt()
          diabeatsx.skinThickness = line1vals[4].toInt()
          diabeatsx.insulin = line1vals[5].toInt()
          diabeatsx.bmi = line1vals[6].toDouble()
          diabeatsx.diabetesPedigreeFunction = line1vals[7].toDouble()
          diabeatsx.age = line1vals[8].toInt()
          diabeatsx.outcome = line1vals[9].toString()
          return diabeatsx
      }


        fun parseJSON(obj: JSONObject?): Diabeats? {
            return if (obj == null) {
                null
            } else try {
                val id = obj.getString("id")
                var diabeatsx: Diabeats? = Diabeats.DiabeatsIndex[id]
                if (diabeatsx == null) {
                    diabeatsx = Diabeats.createByPKDiabeats(id)
                }
                diabeatsx.id = obj.getString("id")
                diabeatsx.pregnancies = obj.getInt("pregnancies")
                diabeatsx.glucose = obj.getInt("glucose")
                diabeatsx.bloodPressure = obj.getInt("bloodPressure")
                diabeatsx.skinThickness = obj.getInt("skinThickness")
                diabeatsx.insulin = obj.getInt("insulin")
                diabeatsx.bmi = obj.getDouble("bmi")
                diabeatsx.diabetesPedigreeFunction = obj.getDouble("diabetesPedigreeFunction")
                diabeatsx.age = obj.getInt("age")
                diabeatsx.outcome = obj.getString("outcome")
                diabeatsx
            } catch (e: Exception) {
                null
            }
        }

      fun makeFromCSV(lines: String?): ArrayList<Diabeats> {
          val result: ArrayList<Diabeats> = ArrayList<Diabeats>()
          if (lines == null) {
              return result
          }
          val rows: ArrayList<String> = Ocl.parseCSVtable(lines)
          for (item in rows.indices) {
              val row = rows[item]
              if (row == null || row.trim { it <= ' ' }.isEmpty()) {
                  //trim
              } else {
                  val x: Diabeats? = parseCSV(row)
                  if (x != null) {
                      result.add(x)
                  }
              }
          }
          return result
      }


        fun parseJSONArray(jarray: JSONArray?): ArrayList<Diabeats>? {
            if (jarray == null) {
                return null
            }
            val res: ArrayList<Diabeats> = ArrayList<Diabeats>()
            val len = jarray.length()
            for (i in 0 until len) {
                try {
                    val x = jarray.getJSONObject(i)
                    if (x != null) {
                        val y: Diabeats? = parseJSON(x)
                        if (y != null) {
                            res.add(y)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return res
        }


        fun writeJSON(x: Diabeats): JSONObject? {
            val result = JSONObject()
            try {
                result.put("id", x.id)
                result.put("pregnancies", x.pregnancies)
                result.put("glucose", x.glucose)
                result.put("bloodPressure", x.bloodPressure)
                result.put("skinThickness", x.skinThickness)
                result.put("insulin", x.insulin)
                result.put("bmi", x.bmi)
                result.put("diabetesPedigreeFunction", x.diabetesPedigreeFunction)
                result.put("age", x.age)
                result.put("outcome", x.outcome)
            } catch (e: Exception) {
                return null
            }
            return result
        }


        fun parseRaw(obj: Any?): Diabeats? {
             if (obj == null) {
                 return null
            }
            try { 
                val map = obj as HashMap<String, Object>
                val id: String = map["id"].toString()
                var diabeatsx: Diabeats? = Diabeats.DiabeatsIndex[id]
                if (diabeatsx == null) {
                    diabeatsx = Diabeats.createByPKDiabeats(id)
                }
                diabeatsx.id = map["id"].toString()
                diabeatsx.pregnancies = (map["pregnancies"] as Long?)!!.toLong().toInt()
                diabeatsx.glucose = (map["glucose"] as Long?)!!.toLong().toInt()
                diabeatsx.bloodPressure = (map["bloodPressure"] as Long?)!!.toLong().toInt()
                diabeatsx.skinThickness = (map["skinThickness"] as Long?)!!.toLong().toInt()
                diabeatsx.insulin = (map["insulin"] as Long?)!!.toLong().toInt()
                diabeatsx.bmi = (map["bmi"] as Long?)!!.toLong().toDouble()
                diabeatsx.diabetesPedigreeFunction = (map["diabetesPedigreeFunction"] as Long?)!!.toLong().toDouble()
                diabeatsx.age = (map["age"] as Long?)!!.toLong().toInt()
                diabeatsx.outcome = map["outcome"].toString()
                return diabeatsx
            } catch (e: Exception) {
                return null
            }
        }

        fun writeJSONArray(es: ArrayList<Diabeats>): JSONArray {
            val result = JSONArray()
            for (i in 0 until es.size) {
                val ex: Diabeats = es[i]
                val jx = writeJSON(ex)
                if (jx == null) {
                    //null
                } else {
                    try {
                        result.put(jx)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return result
        }
    }
}
