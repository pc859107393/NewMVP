package acheng1314.cn.baselibrary.mvp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 视图层代理
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseDelegate : VDelegate {
    private var rootView: View? = null  //根布局
    private var intent: Intent? = null  //activity传递的数据

    private var views: HashMap<Int, View> = hashMapOf()

    lateinit var context: Context
    private lateinit var supportFragmentManager: FragmentManager
    private var isFragment: Boolean = false

    override fun onCreateRootView(inflater: LayoutInflater
                                  , container: ViewGroup?
                                  , savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(setLayoutId(), null, false)
        return rootView!!
    }

    abstract fun setLayoutId(): Int

    override fun postIntent(intent: Intent?) {
        this.intent = intent
    }

    /**
     * 方法重载，快捷findViewById，并存储下次可以快捷查找
     * @param id 控件id
     * @param parent 父控件为null从根布局查找控件，否则从对应的父布局查找控件
     */
    @JvmOverloads
    fun <V : View> getView(@IdRes id: Int, parent: View? = null): V {
        var v: V? = null
        if (views[id] == null)
            if (parent == null) {
                v = rootView?.findViewById(id)
                views[id] = v!!
            } else {
                v = parent.findViewById(id)
                views[id] = v!!
            }
        return views[id] as V
    }

    override fun notifyContext(context: Context) {
        this.context = context
    }

    override fun notifySupportFragmentManager(supportFragmentManager: FragmentManager) {
        this.supportFragmentManager = supportFragmentManager
    }

    override fun getSupportFragmentManager(): FragmentManager? = if (!isFragment) this.supportFragmentManager else null

    override fun isFragment(value: Boolean) {
        this.isFragment = value
    }
}