package edu.boisestate.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_split_bill.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


      // id :  Price, Name
       val priceEditText:EditText = findViewById(R.id.Price)
        val nameEditText:EditText = findViewById(R.id.Name)
        val button: Button = findViewById(R.id.button)

        val resultTextView: TextView = findViewById(R.id.result_textview)

        button.setOnClickListener {
            val friend: Friend = Friend(nameEditText.text.toString())
            val price = priceEditText.text.toString().toInt()

            val intent:Intent = Intent(this, SplitBillActivity::class.java )
            intent.putExtra("friend", friend)
            intent.putExtra("price", price)

            startActivityForResult(intent, 10)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            val pay: Int = data?.getIntExtra("price", 0 ) ?: 0
            val price = "${pay/2}"
            val outputString:String = "You only have to pay $${price}"
            result_textview.text= outputString

             result_textview.visibility = View.VISIBLE
        } else if (resultCode == Activity.RESULT_CANCELED){

            val pay: Int = data?.getIntExtra("price", 0 ) ?: 0
            val outputString:String = "You will have to pay $${pay}"
            result_textview.text= outputString

            result_textview.visibility = View.VISIBLE
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
