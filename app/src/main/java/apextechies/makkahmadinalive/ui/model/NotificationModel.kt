package apextechies.makkahmadinalive.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class NotificationModel {
    @SerializedName("status")
    var status: String? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("data")
    var data: ArrayList<NotificationDataModel>? = null
}

@Parcelize
class NotificationDataModel (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("category")
    var category: String? = null,
    @SerializedName("notification")
    var notification: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("is_read")
    var is_read: String? = null,
    @SerializedName("status")
    var status: String? = null
    ) : Parcelable
