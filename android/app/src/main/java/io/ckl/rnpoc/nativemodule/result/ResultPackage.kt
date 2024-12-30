package io.ckl.rnpoc.nativemodule.result

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager

class ResultPackage(private var resultInterface: ResultInterface?): ReactPackage{
    private var resultModule: ResultModule? = null


    override fun createNativeModules(reactContext: ReactApplicationContext): MutableList<NativeModule> {
        resultModule = ResultModule(reactContext,resultInterface)
        return listOf(resultModule!!).toMutableList()
    }

    override fun createViewManagers(p0: ReactApplicationContext): MutableList<ViewManager<View, ReactShadowNode<*>>> {
       return mutableListOf()
    }

    fun sendEvent(value: String){
        resultModule?.run {
            sendEvent(value)
        }
    }
}