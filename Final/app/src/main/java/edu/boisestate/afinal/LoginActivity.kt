package edu.boisestate.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var username :EditText
    lateinit var password: EditText
    lateinit var login_button :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.et_username)
        password = findViewById(R.id.et_password)
        login_button = findViewById(R.id.login_button)

        val usernamet = et_username.text


        username.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                login_button.isEnabled = true
            }

        })




        login_button.setOnClickListener {
            val intent: Intent = Intent(this, SplitActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Welcome $usernamet", Toast.LENGTH_LONG).show()
        }


    }
}
