package io.ckl.rnpoc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var textInput: TextInputEditText
    private lateinit var txtLabel: TextView
    private lateinit var txtReturned: TextView

    companion object {
        const val IT_SEND_TEXT = "itSendText"
    }

    private val rnLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if (result.resultCode == RESULT_OK){
            result.data?.apply {
                val text = getStringExtra(RnActivity.RESULT_STRING)
                text?.apply {
                    if (text.isNotEmpty())
                        updateReturnedView(text)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new)

        btn = findViewById(R.id.btnClick)
        textInput  = findViewById(R.id.inputText)
        txtLabel = findViewById(R.id.txtLabel)
        txtReturned = findViewById(R.id.txtReturned)

        btn.setOnClickListener {
            val text =  textInput.text.toString()
            clearKeyboard(textInput)
            startRnAct(text)
        }

        (application as MainApplication).updateRootViewContext(this)
    }

    private fun startRnAct(sendingText: String){
        val intent = Intent(this,RnActivity2::class.java)
        intent.putExtra(IT_SEND_TEXT,sendingText)
        textInput.setText("")
        rnLauncher.launch(intent)
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
    }

    private fun clearKeyboard(view: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)

    }

    private fun updateReturnedView(text: String) {
        txtLabel.visibility = View.VISIBLE
        txtReturned.visibility = View.VISIBLE
        txtReturned.text = text
    }
}