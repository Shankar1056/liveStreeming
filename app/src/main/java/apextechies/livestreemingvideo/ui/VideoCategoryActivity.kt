package apextechies.livestreemingvideo.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import apextechies.livestreemingvideo.R
import apextechies.livestreemingvideo.Util.Config
import apextechies.livestreemingvideo.adapter.VideoCategoryAdapter
import apextechies.livestreemingvideo.interfaces.OnItemClickListener
import apextechies.livestreemingvideo.retrofit.DownlodableCallback
import apextechies.livestreemingvideo.retrofit.RetrofitDataProvider
import apextechies.livestreemingvideo.ui.model.CategoryModel
import kotlinx.android.synthetic.main.activiy_categoryvideo.*

class VideoCategoryActivity : AppCompatActivity() {
    var retrofitDataProvider: RetrofitDataProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_categoryvideo)

        videoRV.layoutManager = GridLayoutManager(this, 2)
        retrofitDataProvider = RetrofitDataProvider(this@VideoCategoryActivity)

        getVideoCategoryList();
    }

    private fun getVideoCategoryList() {
        retrofitDataProvider!!.category(object : DownlodableCallback<CategoryModel> {
            override fun onSuccess(result: CategoryModel?) {
                if (result!!.status.equals(Config.TRUE)) {
                    videoRV.adapter = result.data?.let {
                        VideoCategoryAdapter(it, object : OnItemClickListener {
                            override fun onClick(pos: Int) {
                                startActivity(
                                    Intent(this@VideoCategoryActivity, VideoListActivity::class.java).putExtra(
                                        "cat_id",
                                        result.data!![pos].id
                                    )
                                )
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

    }
}