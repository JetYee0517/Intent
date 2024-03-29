package com.example.intent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSend.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage(){
        //Input validation
        if(editTextMessage.text.isEmpty()){
            editTextMessage.setError("Value required")
            return
        }

        if(editTextLucky.text.isEmpty()){
            editTextLucky.setError("Value required")
            return
        }

        //Explicit Intent : Component namemust be provide
        val intent = Intent(this, SecondActivity::class.java)

        //Insert extra to the Intent
        val message = editTextMessage.text.toString()
        intent.putExtra(EXTRA_MSG, message)

        // no return value from second
        // startActivity(intent)
        val lucky = editTextLucky.text.toString().toInt()
        intent.putExtra(EXTRA_LUCKY, lucky)
        //return value from the second
        startActivityForResult(intent, REQUEST_CODE)




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(EXTRA_REPLY)
                //Display reply here
                textViewReply.text=reply
            }
        }
    }

    companion object{
        const val EXTRA_MSG = "com.example.intent.EXTRA_MSG"
        const val EXTRA_LUCKY = "com.example.intent.EXTRA_LUCKY"
        const val EXTRA_REPLY ="com.example.intent.REPLY"
        const val REQUEST_CODE = 1
    }
}
