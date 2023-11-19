package com.mid.busecogen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.mid.busecogen.databinding.ActivityAddVaccineActiviyBinding
import com.mid.busecogen.databinding.ActivityMainBinding
import com.mid.busecogen.model.PetSys
import java.text.SimpleDateFormat
import java.util.Calendar

class AddVaccineActiviy : AppCompatActivity(), View.OnClickListener  {
    lateinit var vaccinebinding: ActivityAddVaccineActiviyBinding
    lateinit var vaccineAdapter: ArrayAdapter<String>
    lateinit var editTextName:EditText
    lateinit var protectivePeriod:String
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    val today = dateFormat.format(calendar.time)
    lateinit var selectedVaccine:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vaccinebinding = ActivityAddVaccineActiviyBinding.inflate(layoutInflater)
        setContentView(vaccinebinding.root)

        vaccineAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PetSys.vaccineTypes)
        vaccinebinding.spinnerAddVaccine.setAdapter(vaccineAdapter)
        vaccinebinding.spinnerAddVaccine.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                selectedVaccine = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        vaccinebinding.tvAddVaccineDate.text = today

        editTextName = findViewById(R.id.etAddProtectivePeriodInMonth)
        editTextName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int,count: Int) {

            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,after: Int) {

            }
            override fun afterTextChanged(s: Editable) {
               protectivePeriod = s.toString()
            }
        })
        vaccinebinding.btnAddVaccine.setOnClickListener(this)

    }




    override fun onClick(v: View) {
        when(v.id){
            R.id.btnAddVaccine -> returnMainActivity()
        }
    }

    fun returnMainActivity(){
        val intentResult = Intent()
        var b= Bundle()
        b.putString("selectedVaccine", selectedVaccine)
        b.putString("date", today)
        b.putString("protectivePeriod", protectivePeriod)
        intentResult.putExtra("msg", b)
        setResult(RESULT_OK, intentResult)
        finish()
    }
}