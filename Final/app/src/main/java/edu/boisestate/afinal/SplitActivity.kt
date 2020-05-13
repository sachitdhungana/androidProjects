package edu.boisestate.afinal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_split.*

class SplitActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split)

        setSupportActionBar(split_toolbar)
        title = "Split Bill"


        val priceText: EditText = findViewById(R.id.et_bill)
        val tipText: EditText = findViewById(R.id.et_tip)
        val peopleText: EditText = findViewById(R.id.et_people)

        val calculate: ImageButton = findViewById(R.id.calculate_button)



        priceText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                calculate.isEnabled = true
            }

        })



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

            }
            resultDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Nah!!")
            {
                    dialog: DialogInterface?, which: Int ->
                Toast.makeText(this,"You can calculate again!!", Toast.LENGTH_LONG).show()
            }

            resultDialog.show()

        }

        fab_split.setOnClickListener {
            val intent: Intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)

        }





    }
}
