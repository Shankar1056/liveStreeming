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
import apextechies.makkahmadinalive.ui.model.NotificationDataModel
import java.util.*

class NotificationAdapter(
    private var list: ArrayList<NotificationDataModel>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var title: TextView
        internal var notification: TextView

        init {
            title = view.findViewById<View>(R.id.title) as TextView
            notification = view.findViewById<View>(R.id.notification) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_notification, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        if (list.size > 0) {
            holder.title.setText(list[position].category)
            holder.notification.setText(list[position].notification)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}


