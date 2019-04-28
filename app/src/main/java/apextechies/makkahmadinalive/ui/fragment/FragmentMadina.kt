package apextechies.makkahmadinalive.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apextechies.makkahmadinalive.R
import apextechies.makkahmadinalive.VideoStreamActivity
import apextechies.makkahmadinalive.adapter.VideoListAdapter
import apextechies.makkahmadinalive.interfaces.OnItemClickListener
import apextechies.makkahmadinalive.ui.model.VideoDataModel
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
                    startActivity(
                        Intent(activity, VideoStreamActivity::class.java).putExtra(
                            "videoId",
                            madinaList[pos].video_url_id
                        ).putParcelableArrayListExtra("list", madinaList)
                    )
                }
            })

        }
    }
}