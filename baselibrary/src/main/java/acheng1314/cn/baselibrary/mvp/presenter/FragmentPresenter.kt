package acheng1314.cn.baselibrary.mvp.presenter

import acheng1314.cn.baselibrary.mvp.view.VDelegate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class FragmentPresenter<out T : VDelegate> : Fragment() {
    private var vd: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vd = initViewD()
        vd?.isFragment(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return vd?.onCreateRootView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vd?.initView()
        vd?.initEvent()
        doOtherThing()
    }

    override fun onDestroy() {
        super.onDestroy()
        vd = null
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        vd = initViewD()
    }

    abstract fun initViewD(): T

    abstract fun doOtherThing()
}