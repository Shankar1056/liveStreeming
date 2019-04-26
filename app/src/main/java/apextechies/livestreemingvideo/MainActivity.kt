package apextechies.livestreemingvideo

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import apextechies.livestreemingvideo.Util.Config
import apextechies.livestreemingvideo.retrofit.DownlodableCallback
import apextechies.livestreemingvideo.retrofit.RetrofitDataProvider
import apextechies.livestreemingvideo.ui.Login
import apextechies.livestreemingvideo.ui.fragment.FragmentMadina
import apextechies.livestreemingvideo.ui.fragment.FragmentMakka
import apextechies.livestreemingvideo.ui.fragment.FragmentOther
import apextechies.livestreemingvideo.ui.model.VideoDataModel
import apextechies.livestreemingvideo.ui.model.VideoModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val tabIcons = intArrayOf(R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera)
    var retrofitDataProvider: RetrofitDataProvider? = null
    var makkaList: ArrayList<VideoDataModel>? = null
    var madinaList: ArrayList<VideoDataModel>? = null
    var otherList: ArrayList<VideoDataModel>? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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
        auth = FirebaseAuth.getInstance()

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

            }
            R.id.nav_share -> {

            }
            R.id.nav_contactUs -> {

            }
            R.id.nav_rate -> {

            }
            R.id.nav_logout -> {
                auth.signOut()
                startActivity(Intent(this@MainActivity, Login::class.java))
                finish()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}