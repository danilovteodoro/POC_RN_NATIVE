package io.ckl.rnpoc

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.ReactRootView
import io.ckl.rnpoc.model.Person
import io.ckl.rnpoc.nativemodule.result.ResultInterface

class RnActivity2 : AppCompatActivity(), ResultInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
//        with(window){
//            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
//            exitTransition = Slide(Gravity.LEFT)
//            enterTransition = Slide(Gravity.RIGHT)
//        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val reactRootView: ReactRootView = (application as MainApplication).run {
             updateRootViewContext(this@RnActivity2)
             reactRootView!!
        }

        intent.getStringExtra(MainActivity.IT_SEND_TEXT)?.also { text->
            reactRootView.appProperties = Bundle().apply {
                putString("name",text)
            }
        }


        (application as MainApplication).registerObserver(this)
        (reactRootView.parent as? ViewGroup)?.removeView(reactRootView)
        setContentView(reactRootView)

    }

    override fun onDestroy() {
        (application as MainApplication).unregisterObserver(this)
        super.onDestroy()
    }

    override fun resultString(moduleName: String, s: String) {
        val it = Intent().apply {
            putExtra(RnActivity.RESULT_STRING,s)
        }
        setResult(RESULT_OK,it)
        finish()

        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_rigth)
    }

    override fun resultPerson(moduleName: String, p: Person) {
        TODO("Not yet implemented")
    }
}