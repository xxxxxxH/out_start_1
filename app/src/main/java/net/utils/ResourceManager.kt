package net.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import net.basicmodel.R
import net.entity.ResourceEntity
import java.util.*

/**
 * Copyright (C) 2021,2021/9/14, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class ResourceManager {
    companion object {
        private var instance: ResourceManager? = null
            get() {
                field?.let {

                } ?: run {
                    field = ResourceManager()
                }
                return field
            }

        @Synchronized
        fun get(): ResourceManager {
            return instance!!
        }
    }

    fun getAllResource(context: Context): ArrayList<ResourceEntity> {
        val result = ArrayList<ResourceEntity>()
        Thread {
            val clazz: Class<*> = R.mipmap::class.java
            for (field in clazz.fields) {
                val name = field.name
                if (name.startsWith("ic") || name.startsWith("ho")) {
                    continue
                }
                val id = context.resources.getIdentifier(name, "mipmap", context.packageName)
                val entity = ResourceEntity()
                entity.name = name
                entity.id = id
                result.add(entity)
            }
        }.start()
        return result
    }

    fun resId2String(context: Context, id: Int): String {
        val r = context.resources
        val uri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + r.getResourcePackageName(id) + "/"
                    + r.getResourceTypeName(id) + "/"
                    + r.getResourceEntryName(id)
        )
        return uri.toString()
    }
}