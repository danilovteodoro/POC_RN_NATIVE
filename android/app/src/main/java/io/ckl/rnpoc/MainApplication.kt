package io.ckl.rnpoc

import android.app.Activity
import android.app.Application
import android.content.MutableContextWrapper
import android.util.Log
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactRootView
import com.facebook.react.defaults.DefaultReactNativeHost
import com.facebook.soloader.SoLoader
import io.ckl.rnpoc.model.Person
import io.ckl.rnpoc.nativemodule.result.ResultInterface
import io.ckl.rnpoc.nativemodule.result.ResultPackage
import io.ckl.rnpoc.util.FileUtils

class MainApplication: Application(), ReactApplication, ResultInterface{
    private var resultPackage = ResultPackage(this)
    var reactRootView: ReactRootView? = null
    private var contextWrapper: MutableContextWrapper? = null
    private var observer:ResultInterface? = null


    fun updateRootViewContext(activity: Activity) {
        if(contextWrapper == null){
            contextWrapper = MutableContextWrapper(activity)
            reactRootView = ReactRootView(contextWrapper).apply {
                startReactApplication(reactNativeHost.reactInstanceManager,"MyReactNativeApp")
            }
            return
        }

        contextWrapper?.apply {
            baseContext = activity
        }
    }

    override fun onCreate() {
        super.onCreate()
//        FileUtils.copyBundleFile(this)
        FileUtils.downloadFromStorage(this)
        SoLoader.init(this,false)
    }


     override val reactNativeHost = object: DefaultReactNativeHost(this){
         // This config is used to load the bundle from file
         override fun getBundleAssetName() = "index.android.bundle"
         override fun getJSBundleFile() = "assets://index.android.bundle"

         // This config is used to load the bundle from a firebase storage
//         override fun getJSBundleFile() = FileUtils.getJSBundleLocation(this@MainApplication)
//         override fun getJSMainModuleName() = "index"

//         override fun getJSIModulePackage() =  super.getJSIModulePackage()

        override fun getPackages() = PackageList(this).packages.apply {
            add(resultPackage)
        }


        override fun getUseDeveloperSupport(): Boolean {
            return BuildConfig.DEBUG
        }
    }



     fun registerObserver(r: ResultInterface){
        observer = r
    }

     fun unregisterObserver(r: ResultInterface){
        observer = r
        observer = null
    }

    override fun resultString(moduleName: String, s: String) {
        Log.d("OBSERVER >>",s)
        observer?.resultString(moduleName,s)
    }

    override fun resultPerson(moduleName: String, p: Person) {
        TODO("Not yet implemented")
    }

    fun sendEvent(value: String) {
        resultPackage.sendEvent(value)
    }
}

