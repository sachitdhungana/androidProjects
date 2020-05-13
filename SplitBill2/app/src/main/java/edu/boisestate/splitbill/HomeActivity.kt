package edu.boisestate.splitbill


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Creating val according to id

        val priceEditText = this.findViewById<EditText>(R.id.Amount)
        val tipEditText:EditText = findViewById(R.id.Tip)
        val peopleEditText:EditText = findViewById(R.id.People)

       val button: Button = findViewById(R.id.calculate)

      val record:Button = findViewById(R.id.record)


        record.setOnClickListener {
            val intent:Intent = Intent(this, RecordActivity::class.java )
                startActivity(intent)
        }

        // for maps activity
        fab_map.setOnClickListener{
            val intent:Intent = Intent(this, HomeActivity::class.java )
            startActivity(intent)
        }


        button.setOnClickListener {

            val price = priceEditText.text.toString().toInt()
            val tip = tipEditText.text.toString().toInt()
            val peoples = peopleEditText.text.toString().toInt()
            val total = "${ (price + (tip * .01) * price)}"
            val bill = "${ (price + (tip * .01) * price)/peoples }"

            val outputString:String = "The total with tips is $${total} " +
                    "You will pay $${bill}"

            val resultDialog = AlertDialog.Builder(this).create()
            resultDialog.setTitle("Result")

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
    }
}
