package apextechies.livestreemingvideo.ui.model

import com.google.gson.annotations.SerializedName

class VideoModel {
    @SerializedName("status")
    var status: String? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("data")
    var data: ArrayList<VideoDataModel>? = null
}

class VideoDataModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("category")
    var videoCategory: String? = null,
    @SerializedName("video_url_id")
    var video_url_id: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null,
    @SerializedName("status")
    var status: String? = null
)
