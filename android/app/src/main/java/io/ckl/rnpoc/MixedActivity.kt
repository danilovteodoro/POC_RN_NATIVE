package io.ckl.rnpoc

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.facebook.react.ReactFragment
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import io.ckl.rnpoc.model.Person
import io.ckl.rnpoc.nativemodule.result.ResultInterface

class MixedActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler{
    private lateinit var lstView:ListView
    private var items = mutableListOf(
        "Android Item 1",
        "Android Item 2",
        "Android Item 3",
        "Android Item 4",
        "Android Item 5",
    )
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mixed)

        val appbar:Toolbar = findViewById(R.id.appbar)
        setSupportActionBar(appbar)

        lstView = findViewById(R.id.nativeListView)
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, items)
        lstView.adapter = adapter

        lstView.setOnItemClickListener { _, _, position, _ ->
            val item = items[position]
            (application as MainApplication).sendEvent(item)
            items.remove(item)
            adapter.notifyDataSetChanged()
        }

        val rnFragment = ReactFragment.Builder()
            .setComponentName("SimpleList")
            .setLaunchOptions(getInitialProperties())
            .setFabricEnabled(false)
            .build()

        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.rnFrame,rnFragment)
        tx.commit()

        (application as MainApplication).registerObserver(resultModule)
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MainApplication).unregisterObserver(resultModule)
    }

    private fun getInitialProperties() = Bundle().apply{
        val b = listOf(bundle("name","user1"),bundle("name","user2"))
        putParcelableArray("users",b.toTypedArray())
    }

    private fun bundle(key:String, value: String) = Bundle().apply {
        putString(key,value)
    }



    override fun invokeDefaultOnBackPressed() {
    }

    val resultModule = object: ResultInterface{
        override fun resultString(moduleName: String, s: String) {
           items.add(s)
            runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }

        override fun resultPerson(moduleName: String, p: Person) {
            TODO("Not yet implemented")
        }
    }
}