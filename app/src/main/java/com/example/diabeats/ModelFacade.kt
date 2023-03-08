package com.example.diabeats

import android.content.Context
import java.util.ArrayList
import android.content.res.AssetManager
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.util.*

class ModelFacade private constructor(context: Context) {

    private var cdb: FirebaseDB = FirebaseDB.getInstance()
    private val assetManager: AssetManager = context.assets
    private var fileSystem: FileAccessor

    private var currentDiabeats: DiabeatsVO? = null
    private var currentDiabeatss: ArrayList<DiabeatsVO> = ArrayList()

    init {
    	//init
        fileSystem = FileAccessor(context)
	}

    companion object {
        private var instance: ModelFacade? = null
        fun getInstance(context: Context): ModelFacade {
            return instance ?: ModelFacade(context)
        }
    }
    
	/* This metatype code requires OclType.java, OclAttribute.java, OclOperation.java */
	fun initialiseOclTypes() {
			val diabeatsOclType: OclType = OclType.createByPKOclType("Diabeats")
		diabeatsOclType.setMetatype(Diabeats::class.java)
		    }
    
    fun createDiabeats(x: DiabeatsVO) { 
			 editDiabeats(x)
	 }
				    
	fun editDiabeats(x: DiabeatsVO) {
		     var obj = getDiabeatsByPK(x.getId())
		     if (obj == null) {
		         obj = Diabeats.createByPKDiabeats(x.getId())
			 }
		  obj.id = x.getId()
		  obj.pregnancies = x.getPregnancies()
		  obj.glucose = x.getGlucose()
		  obj.bloodPressure = x.getBloodPressure()
		  obj.skinThickness = x.getSkinThickness()
		  obj.insulin = x.getInsulin()
		  obj.bmi = x.getBmi()
		  obj.diabetesPedigreeFunction = x.getDiabetesPedigreeFunction()
		  obj.age = x.getAge()
		  obj.outcome = x.getOutcome()
			 cdb.persistDiabeats(obj)
			 currentDiabeats = x
		
	 }
	 
    fun setSelectedDiabeats(x: DiabeatsVO) {
			  currentDiabeats = x
	}

    fun classifyDiabeats(diabeats: Diabeats) : String {
	    var result = ""
		lateinit var tflite : Interpreter
	    lateinit var tflitemodel : ByteBuffer
	
	    try{
		    tflitemodel = loadModelFile(assetManager, "diabeats.tflite")
	    	tflite = Interpreter(tflitemodel) 
	    } catch(ex: Exception){
		  ex.printStackTrace()
	    }
	        
	    val inputVal: FloatArray = floatArrayOf(
	            ((diabeats.pregnancies - 0) / (17 - 0)).toFloat(),
	            ((diabeats.glucose - 0) / (199 - 0)).toFloat(),
	            ((diabeats.bloodPressure - 0) / (122 - 0)).toFloat(),
	            ((diabeats.skinThickness - 0) / (99 - 0)).toFloat(),
	            ((diabeats.insulin - 0) / (846 - 0)).toFloat(),
	            ((diabeats.bmi - 0) / (67.1 - 0)).toFloat(),
	            ((diabeats.diabetesPedigreeFunction - 0.78) / (2.42 - 0.78)).toFloat(),
	            ((diabeats.age - 21) / (81 - 21)).toFloat()
	        )
	    val outputVal: ByteBuffer = ByteBuffer.allocateDirect(8)
	    outputVal.order(ByteOrder.nativeOrder())
	    tflite.run(inputVal, outputVal)
	    outputVal.rewind()
	        
	  	val labelsList : List<String> = listOf ("positive","negative")
	    val output = FloatArray(2)
	        for (i in 0..1) {
	            output[i] = outputVal.float
	        }
	        
	    result = getSortedResult(output, labelsList).get(0).toString()
	        
	        diabeats.outcome = result
	        persistDiabeats(diabeats)
	        
	     return result
	    }
	    
    data class Recognition(
	     var id: String = "",
	     var title: String = "",
	     var confidence: Float = 0F
	     )  {
		override fun toString(): String {
		     return "$title ($confidence%)"
		}
	}
	    
	private fun getSortedResult(labelProbArray: FloatArray, labelList: List<String>): List<Recognition> {
	    
	       val pq = PriorityQueue(
	           labelList.size,
	           Comparator<Recognition> {
	                   (_, _, confidence1), (_, _, confidence2)
	                 -> confidence1.compareTo(confidence2) * -1
	           })
	    
	      for (i in labelList.indices) {
	           val confidence = labelProbArray[i]
	           pq.add(
	               Recognition("" + i,
	                   if (labelList.size > i) labelList[i] else "Unknown", confidence*100))
	            }
	           val recognitions = ArrayList<Recognition>()
	           val recognitionsSize = Math.min(pq.size, labelList.size)
	    
	           if (pq.size != 0) {
	               for (i in 0 until recognitionsSize) {
	                   recognitions.add(pq.poll())
	               }
	           }
	           else {
	               recognitions.add(Recognition("0", "Unknown",100F))
	           }
	           return recognitions
	}
	        	   
	private fun loadModelFile(assetManager: AssetManager, modelPath: String): ByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            startOffset, declaredLength)
    }



	    	fun listDiabeats(): ArrayList<DiabeatsVO> {
		  val diabeatss: ArrayList<Diabeats> = Diabeats.DiabeatsAllInstances
		  currentDiabeatss.clear()
		  for (i in diabeatss.indices) {
		       currentDiabeatss.add(DiabeatsVO(diabeatss[i]))
		  }
			      
		 return currentDiabeatss
	}
	
	fun listAllDiabeats(): ArrayList<Diabeats> {
		  val diabeatss: ArrayList<Diabeats> = Diabeats.DiabeatsAllInstances    
		  return diabeatss
	}
	

			    
    fun stringListDiabeats(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentDiabeatss.indices) {
            res.add(currentDiabeatss[x].toString())
        }
        return res
    }

    fun getDiabeatsByPK(value: String): Diabeats? {
        return Diabeats.DiabeatsIndex[value]
    }
    
    fun retrieveDiabeats(value: String): Diabeats? {
            return getDiabeatsByPK(value)
    }

    fun allDiabeatsIds(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
            for (x in currentDiabeatss.indices) {
                res.add(currentDiabeatss[x].getId())
            }
        return res
    }
    
    fun setSelectedDiabeats(i: Int) {
        if (i < currentDiabeatss.size) {
            currentDiabeats = currentDiabeatss[i]
        }
    }

    fun getSelectedDiabeats(): DiabeatsVO? {
        return currentDiabeats
    }

    fun persistDiabeats(x: Diabeats) {
        val vo = DiabeatsVO(x)
        cdb.persistDiabeats(x)
        currentDiabeats = vo
    }

		
}
