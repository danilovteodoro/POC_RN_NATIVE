package io.ckl.rnpoc.nativemodule

import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.util.UUID

class CalendarModule(reactContext: ReactApplicationContext): ReactContextBaseJavaModule(reactContext) {
    private val name = "CalendarModule"
    override fun getName() = name

    @ReactMethod
    fun createCalendarEvent(name: String, location: String, promise: Promise){
        Log.d(this.name,"Create an event called $name and location: $location" )

        try {
            val eventId = UUID.randomUUID().toString()
            promise.resolve(eventId)
        }catch (e: Throwable){
            promise.reject("Create an event error",e)
        }
    }



}