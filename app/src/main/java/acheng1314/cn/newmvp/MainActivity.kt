package acheng1314.cn.newmvp

import acheng1314.cn.baselibrary.mvp.presenter.ActivityPresenter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : ActivityPresenter<MainDelegate>() {
    override fun doOtherThing() {

    }

    override fun initViewD() = MainDelegate()

}
