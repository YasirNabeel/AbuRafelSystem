package com.dx777.yasirnramaya.aburafelsystem.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dx777.yasirnramaya.aburafelsystem.R
import com.dx777.yasirnramaya.aburafelsystem.fragments.customer_orders.CustomerInfoFragment
import com.dx777.yasirnramaya.aburafelsystem.fragments.shop_needs.FactorySuppliesSectionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragment: Fragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_orders -> {
                fragment = CustomerInfoFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_needs -> {
                fragment = FactorySuppliesSectionFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun customerInfo(){
        fragment = CustomerInfoFragment()
        if (fragment !=null){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentsContainer, fragment!!,fragment?.tag).commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        customerInfo()
    }
}
