package net.adapter

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import net.basicmodel.R
import net.entity.ResourceEntity
import net.utils.ResourceManager

/**
 * Copyright (C) 2021,2021/9/14, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class MyAdapter(
    private val activity: Activity,
    layoutResId: Int,
    data: ArrayList<ResourceEntity>?
) :
    BaseQuickAdapter<ResourceEntity, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: ResourceEntity) {
        val imageView = holder.getView<ImageView>(R.id.img)
        Glide.with(activity).load(ResourceManager.get().resId2String(activity, item.id))
            .into(imageView)
        holder.setText(R.id.name, item.name)
    }
}