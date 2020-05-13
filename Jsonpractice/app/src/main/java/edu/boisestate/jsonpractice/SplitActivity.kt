package edu.boisestate.jsonpractice

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_split.*

class SplitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split)
        setSupportActionBar(split_toolbar)

        val apiHOST :String = BuildConfig.WEB_SERVER + "/api/v1"


        if(BuildConfig.BUILD_TYPE =="release")
            debug_button.visibility = View.GONE


        title = "Split Bill"

        val priceText:EditText =  this.findViewById(R.id.et_bill)
        val tipText:EditText = findViewById(R.id.et_tip)
        val peopleText:EditText = findViewById(R.id.et_people)

        val calculate:ImageButton = findViewById(R.id.calculate_button)


        this.calculate_button.setOnClickListener {
            val price = priceText.text.toString().toInt()
            val tip = tipText.text.toString().toInt()
            val people = peopleText.text.toString().toInt()

            val total = "${(price + (tip * .01) * price)}"
            val bill = "${(price + (tip * .01) * price) / people}"

            val outputString: String = "The total with tips is $${total} " +
                    "Per person $${bill}"

            val resultDialog = AlertDialog.Builder(this).create()
            resultDialog.setTitle("Result")
            resultDialog.setIcon(R.drawable.icon)

            resultDialog.setMessage("\"The total with tips is \$${total} \" \n" +
                    "    \"You will pay \$${bill}\"")
            resultDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Looks Good")
            { dialog: DialogInterface?, which: Int ->
                Toast.makeText(this, "You will only pay $${bill}", Toast.LENGTH_LONG).show()

            }               // trying to display messaging but nt working
            resultDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Nah!!")
            {
                    dialog: DialogInterface?, which: Int ->
                Toast.makeText(this,"You can calculate again!!", Toast.LENGTH_LONG).show()
            }

            resultDialog.show()



        }

        frag_button.setOnClickListener {
            val intent: Intent = Intent(this, BottomNav::class.java )
            startActivity(intent)
        }

        fab_split.setOnClickListener{
            val intent: Intent = Intent(this, MovieActivity::class.java )
            startActivity(intent)

        }


        fab_map.setOnClickListener{
            val intent: Intent = Intent(this, StepsActivity::class.java )
            startActivity(intent)

        }

    }
}
