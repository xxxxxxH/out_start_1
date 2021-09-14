package net.basicmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.adapter.MyAdapter
import net.entity.ResourceEntity
import net.utils.ResourceManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val data = ResourceManager.get().getAllResource(this) as ArrayList<ResourceEntity>
        val myAdapter = MyAdapter(this,R.layout.layout_item_icon,data)
        recycler.layoutManager = GridLayoutManager(this, 3)
        recycler.adapter = myAdapter
    }
}