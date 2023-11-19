package com.mid.busecogen.model

class Vaccine() {
    private var name:String=""
    private lateinit var date:String
    private var protectivePeriodMonth:Int=0

    constructor(name: String, date:String,protectivePeriodMonth:Int ):this() {
        this.name=name
        this.date=date
        this.protectivePeriodMonth=protectivePeriodMonth
    }

    override fun toString(): String {
        return "Vaccine:$name \nDate: $date\nProtective Period in Month $protectivePeriodMonth\n"
    }
}