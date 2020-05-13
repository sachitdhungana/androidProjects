package edu.boisestate.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_split_bill.*
import kotlinx.android.synthetic.main.content_split_bill.view.*

class SplitBillActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split_bill)
        setSupportActionBar(toolbar)

        //getting value from intent
        val friend:Friend = intent.getSerializableExtra("friend") as Friend
        val price:Int = intent.getIntExtra("price", 0)

        val nametextView:TextView = findViewById(R.id.Name_textView)
        val pricetextView:TextView = findViewById(R.id.priceTextView)

        //displaying the value
        nametextView.text = friend.firstName
        pricetextView.text= price.toString()






        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val yesButton: Button = findViewById(R.id.yes_button)
        val noButton: Button = findViewById(R.id.no_button)

        val returnIntent:Intent = Intent()
        returnIntent.putExtra("price",price )

        yesButton.setOnClickListener{
            setResult(Activity.RESULT_OK, returnIntent)
            finish()

        }

        noButton.setOnClickListener{
            setResult(Activity.RESULT_CANCELED, returnIntent)
            finish()

        }
    }


}
