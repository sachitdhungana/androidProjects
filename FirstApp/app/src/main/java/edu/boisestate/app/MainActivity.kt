package edu.boisestate.app

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

class MainActivity : AppCompatActivity() {

    lateinit var priceEditText: EditText
    lateinit var tipsEditText: EditText

    lateinit var subTotalButton: Button
    lateinit var totalButton: Button

    lateinit var subTotalTextView: TextView
    lateinit var totalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

// id : SubTotal_Button, Total_Button, SubTotal_View, Total_View, Price, Tips
        priceEditText = findViewById(R.id.Price)
        tipsEditText = findViewById(R.id.Tips)

        subTotalButton = findViewById(R.id.SubTotal_Button)
        totalButton = findViewById(R.id.Total_Button)

        subTotalTextView = findViewById(R.id.SubTotal_View)
        totalTextView = findViewById(R.id.Total_View)

        subTotalButton.setOnClickListener {

            // for sub total get one value
            // and add tax
            val price: Int = priceEditText.text.toString().toInt()
            val subtotal = "${price * 0.06 + price}"
           val outputString:String = "Your Sub Total with tax is  ${subtotal}"

            subTotalTextView.text = outputString

            subTotalTextView.visibility = View.VISIBLE

        }

       totalButton.setOnClickListener {

            // for tips get another value
           // add tax and the tips percentage
            val price: Int = priceEditText.text.toString().toInt()
            val tips = tipsEditText.text.toString().toInt()

           val total = "${price * 0.06 + price + (tips * .01) * price }"

          val outputString:String = "Your Total with tips is  ${total}"

           totalTextView.text = outputString

           totalTextView.visibility = View.VISIBLE
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
