package com.mid.busecogen.model

class Pet() {
    private lateinit var name:String
    private lateinit var gender:String
    private var birthYear:Int=0
    private lateinit var microchipNumber:String
    private lateinit var species:String
    private var imgId:Int=0

    constructor(name: String, gender:String, birthYear:Int, microchipNumber:String,species:String, imgId: Int):this() {
        this.name = name
        this.gender = gender
        this.birthYear = birthYear
        this.microchipNumber=microchipNumber
        this.species=species
        this.imgId = imgId
    }

    fun getName(): String {
        return name
    }

    fun getGender():String{
        return gender
    }

    fun getImgId(): Int {
        return imgId
    }

    override fun toString(): String {
        return "Name: $name\nGender: $gender\nBirthYear:$birthYear\nMicrochip Number:$microchipNumber\n" +
                "Species: $species\n"
    }


}