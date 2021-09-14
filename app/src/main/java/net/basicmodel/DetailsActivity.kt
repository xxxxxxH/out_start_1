package net.basicmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.layout_activity_details.*
import net.entity.ResourceEntity
import net.fragment.DetailsFragment

/**
 * Copyright (C) 2021,2021/9/14, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class DetailsActivity : AppCompatActivity() {

    val title = arrayOf("Today", "Weekly", "Monthly", "Yearly")
    var views: ArrayList<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_details)
        initData()
    }

    private fun initData() {
        val i = intent
        val entity: ResourceEntity = i.getSerializableExtra("data") as ResourceEntity
        initViewpager(entity)
    }

    private fun initViewpager(entity: ResourceEntity) {
        views = ArrayList()
        views!!.add(DetailsFragment(entity, "today"))
        views!!.add(DetailsFragment(entity, "week"))
        views!!.add(DetailsFragment(entity, "month"))
        views!!.add(DetailsFragment(entity, "year"))
        tab.setViewPager(viewpager, title, this, views)
    }
}