package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity() : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage: ImageView
    lateinit var bender:Bender
    lateinit var textView:TextView
    lateinit var messageView:TextView
    lateinit var sendBtn:ImageView

    private fun send() {
        val (phrase, color) = bender.listenAnswer(messageView.text.toString())
        messageView.text = ""
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textView.text = phrase;
    }

    override fun onClick(v: View?) {
        if (v == sendBtn) {
            send()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onCreate")
        val status = savedInstanceState?.getString("status") ?: Bender.Status.NORMAL.name;
        val question = savedInstanceState?.getString("question") ?: Bender.Question.NAME.name;
        benderImage = iv_bender
        textView = tv_text
        messageView = et_message
        sendBtn = iv_send
        bender = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))
        val (r, g, b) = bender.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textView.text = bender.askQuestion()
        sendBtn.setOnClickListener(this)
        messageView.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                send()
                hideKeyboard()
                true
            } else {
                false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("status", bender.status.name)
        outState?.putString("question", bender.question.name)
    }

}
