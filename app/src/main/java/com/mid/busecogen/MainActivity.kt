package com.mid.busecogen

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mid.busecogen.databinding.ActivityMainBinding
import com.mid.busecogen.model.PetSys
import com.mid.busecogen.model.Vaccine

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var customDialog:Dialog
    lateinit var btnClose:Button
    lateinit var tvDialogDetail:TextView
    lateinit var binding: ActivityMainBinding
    private lateinit var addVaccineActivityLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PetSys.prepareData()
        binding.imgPet.setImageResource(PetSys.pet.getImgId())
        binding.tvPetName.text = PetSys.pet.getName()
        binding.tvPetGender.text = PetSys.pet.getGender()

        addVaccineActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val bundle = result.data?.getBundleExtra("msg")
                if (bundle != null) {
                    alertDialogCreate(bundle)
                }
            }
        }

        binding.imgDetail.setOnClickListener(this)
        binding.btnDisplayVaccineHistory.setOnClickListener(this)
        binding.btnOpenAddVaccine.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btnDisplayVaccineHistory -> displayVaccineHistory()
            R.id.btnOpenAddVaccine -> openAddVaccine()
            R.id.imgDetail -> showDialog()
        }
    }

    fun displayVaccineHistory(){
       binding.tvVacineDetail.text = PetSys.getVaccineList()
    }
    fun openAddVaccine(){
        var secondActivityIntent: Intent = Intent(this@MainActivity, AddVaccineActiviy::class.java)
        addVaccineActivityLauncher.launch(secondActivityIntent)
    }
    fun alertDialogCreate(bundle:Bundle){
        val selectedVaccine = bundle.getString("selectedVaccine")
        val date = bundle.getString("date")
        val protectivePeriod = bundle.getString("protectivePeriod")
        var mesasage:String= selectedVaccine.toString()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure to add vaccine")
        var newVaccine: Vaccine? =
            protectivePeriod?.let { Vaccine(selectedVaccine.toString(), date.toString(), it.toInt()) }

        builder.setMessage(newVaccine.toString())
        builder.setNegativeButton("No") {dialog, which ->

        }
        builder.setPositiveButton("Yes") { dialog, which ->

            if (protectivePeriod != null) {
                if (newVaccine != null) {
                    PetSys.vaccineList.add(newVaccine)
                }
            }else{
                Toast.makeText(this, "Vaccine is Cancelled", Toast.LENGTH_SHORT).show()
            }
            binding.tvVacineDetail.text = PetSys.getVaccineList()
        }
        builder.create()
        builder.show()
    }



    fun showDialog(){
        customDialog = Dialog(this)
        customDialog.setContentView(R.layout.dialog_pet_detail)
        tvDialogDetail = customDialog.findViewById(R.id.tvDialogDetail)
        tvDialogDetail.text = PetSys.pet.toString()
        btnClose= customDialog.findViewById(R.id.btnDialogClose)
        btnClose.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }
}