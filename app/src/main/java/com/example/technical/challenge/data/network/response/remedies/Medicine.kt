package com.example.technical.challenge.data.network.response.remedies

import com.google.gson.annotations.SerializedName

data class Medicine(
@SerializedName("_id") var Id: String?= null,
@SerializedName("ampp_id") var amppId: String?= null,
@SerializedName("ampp_name") var amppName: String?= null,
@SerializedName("amp_id" ) var ampId : String?= null,
@SerializedName("vmpp_id") var vmppId: String?= null,
@SerializedName("discontinued_date") var discontinuedDate: String?= null,
@SerializedName("pip_code") var pipCode : String?= null,
@SerializedName("prescription_charges") var prescriptionCharges : Int?= null,
@SerializedName("nhs_price") var nhsPrice: Int?= null,
@SerializedName("nhs_price_date") var nhsPriceDate: String?= null,
@SerializedName("gtin") var gtin  : ArrayList<String> = arrayListOf(),
@SerializedName("start_date") var startDate: String?= null,
@SerializedName("medicine_name") var medicineName: String?= null,
@SerializedName("generic_name" ) var genericName : String?= null,
@SerializedName("course_quantity"  ) var courseQuantity  : Int?= null,
@SerializedName("medicine_id") var medicineId  : String?= null,
@SerializedName("name") var name  : String?= null,
@SerializedName("controlled") var controlled  : Boolean?  = null

)