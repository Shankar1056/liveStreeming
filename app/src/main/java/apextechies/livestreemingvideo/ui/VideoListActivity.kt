package apextechies.livestreemingvideo.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import apextechies.livestreemingvideo.R
import apextechies.livestreemingvideo.Util.Config
import apextechies.livestreemingvideo.VideoStreamActivity
import apextechies.livestreemingvideo.adapter.VideoListAdapter
import apextechies.livestreemingvideo.interfaces.OnItemClickListener
import apextechies.livestreemingvideo.retrofit.DownlodableCallback
import apextechies.livestreemingvideo.retrofit.RetrofitDataProvider
import apextechies.livestreemingvideo.ui.model.VideoModel
import kotlinx.android.synthetic.main.activiy_categoryvideo.*


class VideoListActivity : AppCompatActivity() {
    var retrofitDataProvider: RetrofitDataProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_categoryvideo)
        videoRV.layoutManager = GridLayoutManager(this, 2)
        retrofitDataProvider = RetrofitDataProvider(this@VideoListActivity)

        //getVideList()
    }

    /*private fun getVideList() {
        retrofitDataProvider!!.video(intent.getStringExtra("cat_id"), object : DownlodableCallback<VideoModel> {
            override fun onSuccess(result: VideoModel?) {
                if (result!!.status.equals(Config.TRUE)) {
                    videoRV.adapter = result.data?.let {
                        VideoListAdapter(it, object : OnItemClickListener {
                            override fun onClick(pos: Int) {
                                startActivity(Intent(this@VideoListActivity, VideoStreamActivity::class.java))
                            }
                        })
                    }
                }
            }

            override fun onFailure(error: String?) {
            }

            override fun onUnauthorized(errorNumber: Int) {
            }
        })
*/


}
