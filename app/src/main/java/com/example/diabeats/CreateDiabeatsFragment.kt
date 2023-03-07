package com.example.diabeats
	
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.lang.Exception
import java.util.*

class CreateDiabeatsFragment : Fragment(), View.OnClickListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	
	private lateinit var diabeatsBean: DiabeatsBean
	
	private lateinit var idTextField: EditText
	private var idData = ""
	private lateinit var pregnanciesTextField: EditText
	private var pregnanciesData = ""
	private lateinit var glucoseTextField: EditText
	private var glucoseData = ""
	private lateinit var bloodPressureTextField: EditText
	private var bloodPressureData = ""
	private lateinit var skinThicknessTextField: EditText
	private var skinThicknessData = ""
	private lateinit var insulinTextField: EditText
	private var insulinData = ""
	private lateinit var bmiTextField: EditText
	private var bmiData = ""
	private lateinit var diabetesPedigreeFunctionTextField: EditText
	private var diabetesPedigreeFunctionData = ""
	private lateinit var ageTextField: EditText
	private var ageData = ""
	private lateinit var outcomeTextView: TextView
		private var outcomeData = ""

    private lateinit var createDiabeatsButton: Button
	private lateinit var cancelDiabeatsButton: Button

		  		
    companion object {
        fun newInstance(c: Context): CreateDiabeatsFragment {
            val fragment = CreateDiabeatsFragment()
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
		root = inflater.inflate(R.layout.creatediabeats_layout, container, false)

				idTextField = root.findViewById(R.id.createDiabeatsidField)
		pregnanciesTextField = root.findViewById(R.id.createDiabeatspregnanciesField)
		glucoseTextField = root.findViewById(R.id.createDiabeatsglucoseField)
		bloodPressureTextField = root.findViewById(R.id.createDiabeatsbloodPressureField)
		skinThicknessTextField = root.findViewById(R.id.createDiabeatsskinThicknessField)
		insulinTextField = root.findViewById(R.id.createDiabeatsinsulinField)
		bmiTextField = root.findViewById(R.id.createDiabeatsbmiField)
		diabetesPedigreeFunctionTextField = root.findViewById(R.id.createDiabeatsdiabetesPedigreeFunctionField)
		ageTextField = root.findViewById(R.id.createDiabeatsageField)
	outcomeTextView = root.findViewById(R.id.createDiabeatsoutcomeView)
		
		diabeatsBean = DiabeatsBean(myContext)

		createDiabeatsButton = root.findViewById(R.id.createDiabeatsOK)
		createDiabeatsButton.setOnClickListener(this)

		cancelDiabeatsButton = root.findViewById(R.id.createDiabeatsCancel)
		cancelDiabeatsButton.setOnClickListener(this)
		
	    return root
	}

	override fun onClick(v: View?) {
		val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		try {
			imm.hideSoftInputFromWindow(v?.windowToken, 0)
		} catch (e: Exception) {
			 e.message
		}
		when (v?.id) {
			R.id.createDiabeatsOK-> {
				createDiabeatsOK()
			}
			R.id.createDiabeatsCancel-> {
				createDiabeatsCancel()
			}
		}
	}

	private fun createDiabeatsOK () {
		idData = idTextField.text.toString()
		diabeatsBean.setId(idData)
		pregnanciesData = pregnanciesTextField.text.toString()
		diabeatsBean.setPregnancies(pregnanciesData)
		glucoseData = glucoseTextField.text.toString()
		diabeatsBean.setGlucose(glucoseData)
		bloodPressureData = bloodPressureTextField.text.toString()
		diabeatsBean.setBloodPressure(bloodPressureData)
		skinThicknessData = skinThicknessTextField.text.toString()
		diabeatsBean.setSkinThickness(skinThicknessData)
		insulinData = insulinTextField.text.toString()
		diabeatsBean.setInsulin(insulinData)
		bmiData = bmiTextField.text.toString()
		diabeatsBean.setBmi(bmiData)
		diabetesPedigreeFunctionData = diabetesPedigreeFunctionTextField.text.toString()
		diabeatsBean.setDiabetesPedigreeFunction(diabetesPedigreeFunctionData)
		ageData = ageTextField.text.toString()
		diabeatsBean.setAge(ageData)
		outcomeData = outcomeTextView.text.toString()
			diabeatsBean.setOutcome(outcomeData)

			if (diabeatsBean.isCreateDiabeatsError()) {
				Log.w(javaClass.name, diabeatsBean.errors())
				Toast.makeText(myContext, "Errors: " + diabeatsBean.errors(), Toast.LENGTH_LONG).show()
			} else {
					diabeatsBean.createDiabeats()
					Toast.makeText(myContext, "Diabeats created!", Toast.LENGTH_LONG).show()
			}
	}

	private fun createDiabeatsCancel () {
		diabeatsBean.resetData()
		idTextField.setText("")
		pregnanciesTextField.setText("")
		glucoseTextField.setText("")
		bloodPressureTextField.setText("")
		skinThicknessTextField.setText("")
		insulinTextField.setText("")
		bmiTextField.setText("")
		diabetesPedigreeFunctionTextField.setText("")
		ageTextField.setText("")
		outcomeTextView.text = ""
	}
	
		
}
