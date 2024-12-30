package io.ckl.rnpoc.nativemodule.result

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import io.ckl.rnpoc.model.Person

class ResultModule(
    reactContext: ReactApplicationContext,
    private var resultInterface: ResultInterface?): ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "ResultModule"

    @ReactMethod(isBlockingSynchronousMethod = true)
    fun resultString(s: String){
        resultInterface?.resultString(name,s)
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    fun resultObject(r: ReadableMap){
        val p = Person(
            r.getString("name")!!,
            r.getInt("age"),
            r.getString("city")!!,
        )
        resultInterface?.resultPerson(name,p)
    }

    fun sendEvent(value: String){
        reactApplicationContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit("EventReminder",value)
    }
}