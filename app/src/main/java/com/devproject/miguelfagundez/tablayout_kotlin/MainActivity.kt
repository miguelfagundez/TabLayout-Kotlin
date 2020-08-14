package com.devproject.miguelfagundez.tablayout_kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

// Tabs names
val tabsNames = arrayOf("Tab One", "Tab Two", "Tab Three")

// Fragments
var firstFragment: FirstFragment? = null
var secondFragment: SecondFragment? = null
var thirdFragment: ThirdFragment? = null

class MainActivity : AppCompatActivity() {

    // Members
    private var toolbar: Toolbar? = null
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private var fab: FloatingActionButton? = null



    // PagerAdapter
    private var myPagerAdapter: MyPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupFragments()
        setupPagerAdapterAndTabs()
        setupListeners()

        //setSupportActionBar(toolbar)
    }

    private fun setupViews() {
        toolbar = findViewById(R.id.toolbar)
        fab = findViewById(R.id.fab)
        viewPager = findViewById(R.id.mainViewPager)
        tabLayout = findViewById(R.id.mainTabLayout)
    }

    private fun setupFragments() {
        firstFragment = FirstFragment()
        secondFragment = SecondFragment()
        thirdFragment = ThirdFragment()
    }

    private fun setupPagerAdapterAndTabs() {
        myPagerAdapter = MyPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        tabLayout!!.setupWithViewPager(viewPager)
        viewPager!!.adapter = myPagerAdapter
    }

    private fun setupListeners() {
        fab!!.setOnClickListener { view ->
            Snackbar.make(view, "Showing something in your email :)", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Customizing my ViewPager, only showing some elements in each Fragment
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                when (position) {
                    1 -> fab!!.visibility = View.GONE
                    else -> fab!!.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }


}

//**************************************************************
// INNER CLASS PAGER ADAPTER
//**************************************************************
internal class MyPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> firstFragment!!
            1 -> secondFragment!!
            else -> thirdFragment!!
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabsNames.get(position)
    }
}