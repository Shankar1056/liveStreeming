package apextechies.livestreemingvideo

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import apextechies.livestreemingvideo.Util.Config
import apextechies.livestreemingvideo.adapter.VideoStreemingAdapter
import apextechies.livestreemingvideo.interfaces.OnItemClickListener
import apextechies.livestreemingvideo.ui.model.VideoDataModel
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_videostreeming.*


class VideoStreamActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val RECOVERY_DIALOG_REQUEST = 1
    var list: ArrayList<VideoDataModel>? = null
    var videoId: String? = null
    var player: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_videostreeming)
        youtube_view!!.initialize(Config.DEVELOPER_KEY, this)
        list = ArrayList()
        list = intent.getParcelableArrayListExtra("list")
        videoListRV.layoutManager = LinearLayoutManager(this)

        videoListRV.adapter = list?.let {
            VideoStreemingAdapter(it, object : OnItemClickListener {
                override fun onClick(pos: Int) {
                    videoId = list!![pos].video_url_id
                    player!!.loadVideo(videoId)
                    player!!.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            })

        }

        videoId = intent.getStringExtra("videoId")

    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        this.player = player
        if (!wasRestored) {
            this.player!!.loadVideo(videoId)
            this.player!!.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        if (errorReason!!.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            Toast.makeText(this, resources.getString(R.string.error_player), Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this)
        }
    }

    private fun getYouTubePlayerProvider(): YouTubePlayer.Provider {
        return youtube_view
    }
}
