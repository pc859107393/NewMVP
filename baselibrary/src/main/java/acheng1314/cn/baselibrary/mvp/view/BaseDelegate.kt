package acheng1314.cn.baselibrary.mvp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
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

    private lateinit var context: Context

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
        if (views[id] == null && parent == null) {
            val v = rootView?.findViewById<V>(id)
            views[id] = v!!
        } else if (views[id] == null) {
            val v = parent?.findViewById<V>(id)
            views[id] = v!!
        }
        return views[id]!! as V
    }

    override fun notifyContext(context: Context) {
        this.context = context
    }
}