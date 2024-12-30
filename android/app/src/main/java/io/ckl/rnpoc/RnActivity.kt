package io.ckl.rnpoc

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.facebook.hermes.reactexecutor.HermesExecutorFactory
import com.facebook.react.BuildConfig
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader
import io.ckl.rnpoc.model.Person
import io.ckl.rnpoc.nativemodule.MyAppPackage
import io.ckl.rnpoc.nativemodule.result.ResultInterface
import io.ckl.rnpoc.nativemodule.result.ResultPackage

class RnActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler {
    private lateinit var mReactRootView: ReactRootView
    private lateinit var mReactInstanceManager: ReactInstanceManager
    companion object {
        private const val OVERLAY_PERMISSION_REQ_CODE = 1
        const val RESULT_STRING = "RESULT_STRING"
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result: ActivityResult->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (!Settings.canDrawOverlays(this)){
                // SYSTEM_ALERT_WINDOW permission not granted
            }
        }

        with(result){
            mReactInstanceManager.onActivityResult(this@RnActivity, OVERLAY_PERMISSION_REQ_CODE,resultCode,data)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        SoLoader.init(this,false)

        mReactRootView = ReactRootView(this)


        val packages =  getPackages()

        mReactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .setJavaScriptExecutorFactory(HermesExecutorFactory())
            .addPackages(packages)
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()

        val initialProperties = Bundle()

        val tex3 = intent.getStringExtra(MainActivity.IT_SEND_TEXT)!!
        initialProperties.putString("name",tex3)

        mReactRootView.startReactApplication(mReactInstanceManager,"MyReactNativeApp",initialProperties)
        mReactRootView.appProperties = initialProperties
        setContentView(mReactRootView)

        // It's necessary to add that for versions previous 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${packageName}"))
                launcher.launch(intent)
            }
        }


    }

    // handle lifecycle
    override fun invokeDefaultOnBackPressed() {
        mReactInstanceManager.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        mReactInstanceManager.onHostResume(this,this)
    }

    override fun onPause() {
        super.onPause()
        mReactInstanceManager.onHostPause(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        mReactInstanceManager.onHostDestroy(this)
        mReactRootView.unmountReactApplication()
    }


    // Integrate dev options with emulator
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU){
            mReactInstanceManager.showDevOptionsDialog()
        }

        return super.onKeyUp(keyCode, event)

    }

    private fun getPackages(): List<ReactPackage> = PackageList(application).packages.apply {
        add(MyAppPackage())
        add(ResultPackage(results))
    }

    private val results = object :ResultInterface{
        override fun resultString(moduleName: String, s: String) {
            val it = Intent()
            it.putExtra(RESULT_STRING,s)
            setResult(RESULT_OK,it)
            finish()
        }

        override fun resultPerson(moduleName: String, p: Person) {
           Log.i("RNActivity","Name: ${p.name}, age: ${p.age}, city: ${p.city}")
        }
    }
}