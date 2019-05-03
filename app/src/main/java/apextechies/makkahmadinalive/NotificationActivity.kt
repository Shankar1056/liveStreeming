package apextechies.makkahmadinalive

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import apextechies.makkahmadinalive.adapter.NotificationAdapter
import apextechies.makkahmadinalive.interfaces.OnItemClickListener
import apextechies.makkahmadinalive.ui.model.NotificationDataModel
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    var list: ArrayList<NotificationDataModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        list = intent.getParcelableArrayListExtra("list")

        notificationRV.layoutManager = LinearLayoutManager(this)

        notificationRV.adapter = list?.let {
            NotificationAdapter(it, object : OnItemClickListener {
                override fun onClick(pos: Int) {
                    startActivity(
                        Intent(this@NotificationActivity, NotificationDetailsActivity::class.java).putExtra(
                            "name",
                            list!![pos].category
                        ).putExtra("notification", list!![pos].notification)
                    )
                }
            })

        }
    }
}