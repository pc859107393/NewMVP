package acheng1314.cn.baselibrary.mvp.view

import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 视图层代理
 */
@SuppressWarnings("unchecked")
abstract class BaseDelegate : VDelegate {
    private var rootView: View? = null  //根布局
    private var intent: Intent? = null  //activity传递的数据

    private var views: HashMap<Int, View> = hashMapOf()

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

    fun <V : View> getView(@IdRes id: Int): V {
        if (views[id] == null) {
            val v = rootView?.findViewById<V>(id)
            views[id] = v!!
        }
        return views[id]!! as V
    }
}