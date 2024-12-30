package io.ckl.rnpoc.nativemodule

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager

class MyAppPackage: ReactPackage {

    override fun createNativeModules(reactContext: ReactApplicationContext): MutableList<NativeModule> {

       return listOf(CalendarModule(reactContext)).toMutableList()
}

    override fun createViewManagers(p0: ReactApplicationContext):
            MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()
}

