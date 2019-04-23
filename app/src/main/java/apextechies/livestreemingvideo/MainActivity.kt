package apextechies.livestreemingvideo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import apextechies.livestreemingvideo.Util.Config
import apextechies.livestreemingvideo.retrofit.DownlodableCallback
import apextechies.livestreemingvideo.retrofit.RetrofitDataProvider
import apextechies.livestreemingvideo.ui.fragment.FragmentMadina
import apextechies.livestreemingvideo.ui.fragment.FragmentMakka
import apextechies.livestreemingvideo.ui.fragment.FragmentOther
import apextechies.livestreemingvideo.ui.model.VideoDataModel
import apextechies.livestreemingvideo.ui.model.VideoModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tabIcons = intArrayOf(R.drawable.ic_menu_camera, R.drawable.ic_menu_camera, R.drawable.ic_menu_camera)
    var retrofitDataProvider: RetrofitDataProvider? = null
    var makkaList: ArrayList<VideoDataModel>? = null
    var madinaList: ArrayList<VideoDataModel>? = null
    var otherList: ArrayList<VideoDataModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        retrofitDataProvider = RetrofitDataProvider(this@MainActivity)
        makkaList = ArrayList()
        madinaList = ArrayList()
        otherList = ArrayList()

        getVideList()

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
                            makkaList!!.add(VideoDataModel(
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
}