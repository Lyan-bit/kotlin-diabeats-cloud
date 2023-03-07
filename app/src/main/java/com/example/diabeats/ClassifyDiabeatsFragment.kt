package com.example.diabeats
	
import android.os.Bundle
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.diabeats.* 
import java.lang.Exception

class ClassifyDiabeatsFragment : Fragment(), View.OnClickListener , AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
			
	private lateinit var classifyDiabeatsBean: ClassifyDiabeatsBean
	
	private lateinit var classifyDiabeats: Button
	private lateinit var cancelClassifyDiabeats: Button
	private lateinit var classifyDiabeatsResult: TextView

	private lateinit var classifyDiabeatsdiabeatsSpinner: Spinner
	private var classifyDiabeatsdiabeatsListItems: List<String> = ArrayList()
	private lateinit var classifyDiabeatsdiabeatsAdapter: ArrayAdapter<String>
	private var classifyDiabeatsdiabeatsData = "" 
		  		
    companion object {
        fun newInstance(c: Context): ClassifyDiabeatsFragment {
            val fragment = ClassifyDiabeatsFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		root = inflater.inflate(R.layout.classifydiabeats_layout, container, false)
        model = ModelFacade.getInstance(myContext)
        
		classifyDiabeatsdiabeatsSpinner = root.findViewById(R.id.classifyDiabeatsDiabeatsSpinner)
		Log.i("model",model.listDiabeats().toString())
		Log.i("model",model.allDiabeatsIds().toString())
		classifyDiabeatsdiabeatsListItems = model.allDiabeatsIds() 
		classifyDiabeatsdiabeatsAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, classifyDiabeatsdiabeatsListItems) 
		classifyDiabeatsdiabeatsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) 
		classifyDiabeatsdiabeatsSpinner.adapter = classifyDiabeatsdiabeatsAdapter 
		classifyDiabeatsdiabeatsSpinner.onItemSelectedListener = this 


		classifyDiabeatsResult = root.findViewById(R.id.classifyDiabeatsResult)
		classifyDiabeatsBean = ClassifyDiabeatsBean(myContext)

        classifyDiabeats = root.findViewById(R.id.classifyDiabeatsOK)
        classifyDiabeats.setOnClickListener(this)
	
        cancelClassifyDiabeats = root.findViewById(R.id.classifyDiabeatsCancel)
        cancelClassifyDiabeats.setOnClickListener(this)
        
			
	    return root
	}

	override fun onClick(v: View?) {
        val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            if (v != null) {
                imm.hideSoftInputFromWindow(v?.windowToken, 0)
            }
        } catch (e: Exception) {
        	 e.message
        }

		when (v?.id) {
			
			R.id.classifyDiabeatsOK-> {
				 classifyDiabeatsOK()
			}
			R.id.classifyDiabeatsCancel-> {
				 classifyDiabeatsCancel()
			}
			
		}
		
	}
	
	private fun classifyDiabeatsOK () {
				classifyDiabeatsBean.setDiabeats(classifyDiabeatsdiabeatsData)

	    if (classifyDiabeatsBean.isClassifyDiabeatsError()) {
	       Log.w(javaClass.name, classifyDiabeatsBean.errors())
	       Toast.makeText(myContext, "Errors: " + classifyDiabeatsBean.errors(), Toast.LENGTH_LONG).show()
	    } else {
	       classifyDiabeatsResult.text = classifyDiabeatsBean.classifyDiabeats().toString()
	       Toast.makeText(myContext, "done!", Toast.LENGTH_LONG).show()
	    }
	}
	
	private fun classifyDiabeatsCancel () {
	    classifyDiabeatsBean.resetData()
	    classifyDiabeatsResult.text = ""

	}
	

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
	 	if (parent === classifyDiabeatsdiabeatsSpinner) {
	 	    classifyDiabeatsdiabeatsData = classifyDiabeatsdiabeatsListItems[position]
	 	}
	 	    }
	 	
	 	    override fun onNothingSelected(parent: AdapterView<*>) {
	 	    	//onNothingSelected
	 	    }

}
