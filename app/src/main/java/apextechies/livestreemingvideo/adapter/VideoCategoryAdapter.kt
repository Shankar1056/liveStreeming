package apextechies.livestreemingvideo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import apextechies.livestreemingvideo.R
import apextechies.livestreemingvideo.Util.Config
import apextechies.livestreemingvideo.interfaces.OnItemClickListener
import apextechies.livestreemingvideo.ui.model.CategoryDataModel
import com.squareup.picasso.Picasso
import java.util.*

class VideoCategoryAdapter( private var list: ArrayList<CategoryDataModel>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<VideoCategoryAdapter.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var textView: TextView

        init {
            textView = view.findViewById<View>(R.id.textView) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_category, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        if (list.size>0) {
            holder.textView.setText(list[position].name)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}