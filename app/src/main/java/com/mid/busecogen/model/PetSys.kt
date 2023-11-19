package com.mid.busecogen.model

import com.mid.busecogen.R
import java.util.Collections

class PetSys {
    companion object {
        lateinit var pet:Pet
        var vaccineList:ArrayList<Vaccine> = ArrayList()
        var vaccineTypes:ArrayList<String> = ArrayList()
        fun prepareData() {
            pet= Pet("Simba","male",2021,"123123123123","Exotic Shorthair", R.drawable.pet)
            Collections.addAll<Vaccine>(
                vaccineList,
                Vaccine("Internal Parasite Vaccine","28.10.2023",3),
                Vaccine("External Parasite Vaccine","28.10.2023",3),
                Vaccine("Mycosis Vaccine","14.01.2022",12),
                Vaccine("Rabies Vaccine","26.11.2022",12),
                Vaccine("Cat Mixed Vaccine","25.12.2022",12)
            )
            Collections.addAll<String>(
                vaccineTypes,
                "Internal Parasite Vaccine", "External Parasite Vaccine", "Mycosis Vaccine", "Rabies Vaccine", "Cat Mixed Vaccine"
            )
        }

        fun getVaccineList():String{
            return vaccineList.joinToString(separator = "\n") { vaccine ->
                vaccine.toString()
            }
        }

    }
}