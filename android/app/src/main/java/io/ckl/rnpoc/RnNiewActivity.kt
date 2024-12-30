package io.ckl.rnpoc

import com.facebook.react.ReactActivity
import com.facebook.react.defaults.DefaultReactActivityDelegate

class RnNiewActivity: ReactActivity() {
    override fun getMainComponentName() = "MyReactNativeApp"

    override fun createReactActivityDelegate() =
        DefaultReactActivityDelegate(this,mainComponentName,false)
}