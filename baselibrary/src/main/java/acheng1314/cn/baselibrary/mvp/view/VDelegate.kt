package acheng1314.cn.baselibrary.mvp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 增加视图层代理
 */
interface VDelegate {
    fun onCreateRootView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    fun postIntent(intent: Intent?)
    fun initEvent()
    fun initView()
}