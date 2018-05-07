package acheng1314.cn.newmvp

import acheng1314.cn.baselibrary.mvp.presenter.FragmentPresenter

class DemoFragment : FragmentPresenter<MainDelegate>() {
    override fun doOtherThing() {

    }

    override fun initViewD() = MainDelegate()

}