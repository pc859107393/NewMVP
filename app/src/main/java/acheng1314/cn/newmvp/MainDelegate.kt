package acheng1314.cn.newmvp

import acheng1314.cn.baselibrary.mvp.view.BaseDelegate
import android.widget.TextView

class MainDelegate : BaseDelegate() {
    override fun setLayoutId() = R.layout.activity_main

    override fun initEvent() {
        view.setOnClickListener {
            view.text = "吹牛逼"
        }
    }

    private lateinit var view: TextView
    override fun initView() {
        view = getView(R.id.mtv)
    }
}