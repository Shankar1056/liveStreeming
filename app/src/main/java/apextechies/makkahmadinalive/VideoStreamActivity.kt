package apextechies.makkahmadinalive

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import apextechies.makkahmadinalive.Util.Config
import apextechies.makkahmadinalive.ui.model.VideoDataModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_videostreeming.*


class VideoStreamActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val RECOVERY_DIALOG_REQUEST = 1
    var list: ArrayList<VideoDataModel>? = null
    var videoId: String? = null
    var player: YouTubePlayer? = null
    private lateinit var adRequest: AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_videostreeming)
        youtube_view!!.initialize(Config.DEVELOPER_KEY, this)

        MobileAds.initialize(this, resources.getString(R.string.admob_app_id))
        adRequest = AdRequest.Builder().addTestDevice("C8163AD6515E62D2669557515CAB23B8").build()
        adView.loadAd(adRequest)






        /*list = ArrayList()
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

        }*/

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

    override fun onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    override fun onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    override fun onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
