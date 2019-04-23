package apextechies.livestreemingvideo.ui.model

import com.google.gson.annotations.SerializedName

class UserModel{

    @SerializedName("status")
    var status: String?= null
    @SerializedName("message")
    var message: String?= null
    @SerializedName("data")
    var data: ArrayList<UserDataModel>?= null
}

class UserDataModel {
@SerializedName("id")
var id: String?=null
    @SerializedName("name")
    var name: String?= null
    @SerializedName("mobile")
    var mobile: String?= null
    @SerializedName("email")
    var email: String?= null
    @SerializedName("created_at")
    var created_at: String?= null
    @SerializedName("status")
    var status: String?= null
}
