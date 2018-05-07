package acheng1314.cn.newmvp

import acheng1314.cn.baselibrary.mvp.view.BaseDelegate
import android.widget.TextView

class MainDelegate : BaseDelegate() {
    override fun setLayoutId() = R.layout.activity_main

    private var count: Int = 1
    override fun initEvent() {
        view.setOnClickListener {
            if (count % 2 == 0)
                getSupportFragmentManager()?.beginTransaction()?.replace(R.id.mLL, DemoFragment())?.commit()
            else view.text = "我现在是TextView界面，再次点击一下老子就是Fragment了"
            count++
        }
    }

    private lateinit var view: TextView
    override fun initView() {
        view = getView(R.id.mtv)
    }
}