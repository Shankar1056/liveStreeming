package apextechies.livestreemingvideo.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import apextechies.livestreemingvideo.MainActivity
import apextechies.livestreemingvideo.R
import apextechies.livestreemingvideo.Util.Config
import apextechies.livestreemingvideo.retrofit.DownlodableCallback
import apextechies.livestreemingvideo.retrofit.RetrofitDataProvider
import apextechies.livestreemingvideo.ui.model.UserModel
import kotlinx.android.synthetic.main.activity_signup.*

class SignUp : AppCompatActivity() {

    private lateinit var retrofitDataProvider: RetrofitDataProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        retrofitDataProvider = RetrofitDataProvider(this)
        input_mobile.setText(intent.getStringExtra("mobile"))
        submitButton.setOnClickListener {

            if (TextUtils.isEmpty(input_name.text.toString())) {
                Toast.makeText(this@SignUp, "Enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(input_mobile.text.toString())) {
                Toast.makeText(this@SignUp, "Enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(input_email.text.toString())) {
                Toast.makeText(this@SignUp, "Enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            retrofitDataProvider.signup(input_name.text.toString(),
                input_email.text.toString(),
                input_mobile.text.toString(),
                object : DownlodableCallback<UserModel> {
                    override fun onSuccess(result: UserModel?) {
                        if (result!!.status.equals(Config.TRUE)) {
                            startActivity(Intent(this@SignUp, MainActivity::class.java))
                            finish()
                        }
                    }

                    override fun onFailure(error: String?) {
                    }

                    override fun onUnauthorized(errorNumber: Int) {
                    }
                })
        }
    }
}