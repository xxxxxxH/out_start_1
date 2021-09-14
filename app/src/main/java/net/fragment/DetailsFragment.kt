package net.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_fragment.*
import net.basicmodel.R
import net.entity.DetailsEntity
import net.entity.ResourceEntity
import net.http.RequestService
import net.http.RetrofitUtils
import net.utils.ResourceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment(val entity: ResourceEntity, val type: String) : Fragment() {

    val retrofit = RetrofitUtils.get().retrofit()
    val service = retrofit.create(RequestService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        service.getInfo(type, entity.name).enqueue(object : Callback<DetailsEntity> {
            override fun onResponse(call: Call<DetailsEntity>, response: Response<DetailsEntity>) {
                Log.i("xxxxxxH", "response=$response")
                initView(response.body()!!)
            }

            override fun onFailure(call: Call<DetailsEntity>, t: Throwable) {
                Log.i("xxxxxxH", "onFailure=$t")
            }

        })
    }

    private fun initView(detailsEntity: DetailsEntity) {
        Glide.with(requireActivity())
            .load(ResourceManager.get().resId2String(requireActivity(), entity.id)).into(imageview)
        name.text = entity.name
        when (type) {
            "today" -> time.text = detailsEntity.date
            "week" -> time.text = detailsEntity.week
            "month" -> time.text = detailsEntity.month
            "year" -> time.text = detailsEntity.year
        }
        content.text = detailsEntity.horoscope
    }
}