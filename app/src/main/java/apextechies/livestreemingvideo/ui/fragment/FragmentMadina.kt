package apextechies.livestreemingvideo.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apextechies.livestreemingvideo.R
import apextechies.livestreemingvideo.VideoStreamActivity
import apextechies.livestreemingvideo.adapter.VideoListAdapter
import apextechies.livestreemingvideo.interfaces.OnItemClickListener
import apextechies.livestreemingvideo.ui.model.VideoDataModel
import kotlinx.android.synthetic.main.activiy_categoryvideo.*

@SuppressLint("ValidFragment")
class FragmentMadina constructor(val madinaList: ArrayList<VideoDataModel>?) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmentmakka, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoRV.layoutManager = GridLayoutManager(activity, 2)

        videoRV.adapter = madinaList?.let {
            VideoListAdapter(it, object : OnItemClickListener {
                override fun onClick(pos: Int) {
                    startActivity(Intent(activity, VideoStreamActivity::class.java).
                        putExtra("videoId", madinaList[pos].video_url_id))
                }
            })

        }
    }
}