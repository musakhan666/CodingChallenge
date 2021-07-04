package com.android.designpattern.model

import com.google.gson.annotations.SerializedName

class Model() {

    data class ApiResponse(

        @SerializedName("Code") var code: Int,
        @SerializedName("Message") var message: String
    )

    data class InverterModel(
        val inverterImageUrl: String = "",
        val inverterVersion: String = "",
        val inverterCodeCheck: String = "",
        val inverterModel:String = "",
        val inverterCapacity:String = "",
        val inverterFaultMessageValue:String = "",
        val inverterPowerValue:String = "",
        val inverterTodayGenerationValue:String = "",
        val inverterTotalHoursValue:String = "",
        val inverterInnerTempratureValue:String = "",
        val inverterAcVoltageValue:String = "",
        val inverterAcCurrentValue:String = "",
        val inverterAcFrequencyValue:String = "",
        val inverterDcVoltage_Current1Value:String = "",
        val inverterDcVoltage_Current2Value:String = ""
    )

    data class SiteListResponse(
        val userId:Int = 0,
        val Id:Int = 0,
        val title:String = "",
        val body:String = ""
    )



}