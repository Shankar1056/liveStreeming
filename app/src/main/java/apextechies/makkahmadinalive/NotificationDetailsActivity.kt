package apextechies.makkahmadinalive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notificationdetails.*

class NotificationDetailsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificationdetails)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        notificationtitle.text = intent.getStringExtra("name")
        notificationDetails.text = intent.getStringExtra("notification")

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}