package apextechies.makkahmadinalive

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import apextechies.makkahmadinalive.Util.Config
import apextechies.makkahmadinalive.retrofit.DownlodableCallback
import apextechies.makkahmadinalive.retrofit.RetrofitDataProvider
import apextechies.makkahmadinalive.ui.fragment.FragmentMadina
import apextechies.makkahmadinalive.ui.fragment.FragmentMakka
import apextechies.makkahmadinalive.ui.fragment.FragmentOther
import apextechies.makkahmadinalive.ui.model.NotificationDataModel
import apextechies.makkahmadinalive.ui.model.NotificationModel
import apextechies.makkahmadinalive.ui.model.VideoDataModel
import apextechies.makkahmadinalive.ui.model.VideoModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val tabIcons = intArrayOf(R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera)
    var retrofitDataProvider: RetrofitDataProvider? = null
    var makkaList: ArrayList<VideoDataModel>? = null
    var madinaList: ArrayList<VideoDataModel>? = null
    var otherList: ArrayList<VideoDataModel>? = null
    var notificationList: ArrayList<NotificationDataModel>? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var adRequest: AdRequest
    private lateinit var mInterstitialAd: InterstitialAd


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        retrofitDataProvider = RetrofitDataProvider(this@MainActivity)
        makkaList = ArrayList()
        madinaList = ArrayList()
        otherList = ArrayList()

        getVideList()
        getNotification()
        auth = FirebaseAuth.getInstance()


        MobileAds.initialize(this, resources.getString(R.string.admob_app_id))
        loadBannerAds()
        loadInterstitleAds()

        notificationLayout.setOnClickListener {
            startActivity(Intent(this@MainActivity, NotificationActivity::class.java).
            putParcelableArrayListExtra("list", notificationList))
        }
    }

    private fun getNotification() {
        retrofitDataProvider!!.notification("", "", "", "fetch", object : DownlodableCallback<NotificationModel> {
            override fun onSuccess(result: NotificationModel?) {

                if (result!!.status.equals(Config.TRUE)) {
                    if (result.data!!.size>0) {
                        notificationList = result.data
                        notificationText.text = result.data!!.size.toString()
                        notificationLayout.visibility = View.VISIBLE
                    } else{
                        notificationLayout.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(error: String?) {
            }

            override fun onUnauthorized(errorNumber: Int) {
            }

        })
    }

    private fun loadInterstitleAds() {
        mInterstitialAd = InterstitialAd(this@MainActivity)
        mInterstitialAd.adUnitId = resources.getString(R.string.interstitial_full_screen)
        adRequest = AdRequest.Builder().build()
        mInterstitialAd.loadAd(adRequest)

    }

    private fun loadBannerAds() {
        adRequest = AdRequest.Builder().addTestDevice(resources.getString(R.string.banner_home_footer)).build()
        adView.loadAd(adRequest)
    }


    private fun getVideList() {
        retrofitDataProvider!!.video(object : DownlodableCallback<VideoModel> {
            override fun onSuccess(result: VideoModel?) {
                makkaList!!.clear()
                madinaList!!.clear()
                otherList!!.clear()
                if (result!!.status.equals(Config.TRUE)) {
                    for (i in 0 until result.data!!.size) {
                        if (result.data!![i].videoCategory.equals(Config.MAKKA)) {
                            makkaList!!.add(
                                VideoDataModel(
                                    result.data!![i].id, result.data!![i].name, result.data!![i].videoCategory,
                                    result.data!![i].video_url_id, result.data!![i].created_at, result.data!![i].status
                                )
                            )
                        } else if (result.data!![i].videoCategory.equals(Config.MADINA)) {
                            madinaList!!.add(
                                VideoDataModel(
                                    result.data!![i].id, result.data!![i].name, result.data!![i].videoCategory,
                                    result.data!![i].video_url_id, result.data!![i].created_at, result.data!![i].status
                                )
                            )
                        } else {
                            otherList!!.add(
                                VideoDataModel(
                                    result.data!![i].id, result.data!![i].name, result.data!![i].videoCategory,
                                    result.data!![i].video_url_id, result.data!![i].created_at, result.data!![i].status
                                )
                            )
                        }

                    }

                }

                setupViewPager(viewpager!!)
                tabs!!.setupWithViewPager(viewpager)
            }

            override fun onFailure(error: String?) {
            }

            override fun onUnauthorized(errorNumber: Int) {
            }
        })

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentMakka(makkaList), "Makka")
        adapter.addFragment(FragmentMadina(madinaList), "Madina")
        adapter.addFragment(FragmentOther(otherList), "Others")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_privacyPolicy -> {

            }

            R.id.nav_share -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=apextechies.makkahmadinalive"
                )
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }
            R.id.nav_contactUs -> {
                alllowPermission()

            }
            R.id.nav_rate -> {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=apextechies.makkahmadinalive")
                )
                startActivity(intent)
            }
            /* R.id.nav_logout -> {
                 auth.signOut()
                 startActivity(Intent(this@MainActivity, Login::class.java))
                 finish()
             }*/
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun alllowPermission() {

        var permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                123
            )
        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:7338489786")
            startActivity(callIntent)
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {

            123 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                alllowPermission()
            } else {
                Log.d("TAG", "Call Permission Not Granted")
            }

            else -> {
            }
        }
    }


    override fun onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    override fun onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    override fun onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    override fun onBackPressed() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
        super.onBackPressed()
    }

}