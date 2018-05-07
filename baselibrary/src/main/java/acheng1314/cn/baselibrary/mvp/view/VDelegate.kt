package acheng1314.cn.baselibrary.mvp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
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
    fun notifyContext(context: Context)
    fun notifySupportFragmentManager(supportFragmentManager: FragmentManager)
    fun getSupportFragmentManager(): FragmentManager?
    fun isFragment(value: Boolean)
}