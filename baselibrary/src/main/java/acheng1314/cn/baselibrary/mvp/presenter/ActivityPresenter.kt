package acheng1314.cn.baselibrary.mvp.presenter

import acheng1314.cn.baselibrary.mvp.view.VDelegate
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * 传统的mvp模式加上布局文件一共需要配置四个文件，如何有效的降低文件呢？
 *
 */
abstract class ActivityPresenter<out T : VDelegate> : AppCompatActivity() {
    private var vd: T? = null

    init {
        vd = initViewD()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == vd) throw RuntimeException("view delegate init failed！")
        vd?.postIntent(intent)
        setContentView(vd?.onCreateRootView(layoutInflater, null, savedInstanceState))
    }

    override fun onResume() {
        super.onResume()
        vd?.initView()
        vd?.initEvent()
        doOtherThing()
    }

    abstract fun initViewD(): T

    /**
     * 界面绘制完成做点其他的事情
     */
    abstract fun doOtherThing()

    override fun onDestroy() {
        super.onDestroy()
        vd = null
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (null == vd) {
            vd = initViewD()
        }
    }


}
