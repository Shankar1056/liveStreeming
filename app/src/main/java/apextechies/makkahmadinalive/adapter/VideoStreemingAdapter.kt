package apextechies.makkahmadinalive.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import apextechies.makkahmadinalive.R
import apextechies.makkahmadinalive.Util.Config
import apextechies.makkahmadinalive.interfaces.OnItemClickListener
import apextechies.makkahmadinalive.ui.model.VideoDataModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class VideoStreemingAdapter (private var list: ArrayList<VideoDataModel>,
                             private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<VideoStreemingAdapter.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var textView: TextView
        internal var imageView: ImageView

        init {
            textView = view.findViewById<View>(R.id.textView) as TextView
            imageView = view.findViewById<View>(R.id.imageView) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_video, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        if (list.size > 0) {
            holder.textView.setText(list[position].name)
            Picasso.get().load(Config.VIDEO_LINK + list[position].video_url_id + "/0.jpg").into(holder.imageView);
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}


