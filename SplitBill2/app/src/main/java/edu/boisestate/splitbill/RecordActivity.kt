package edu.boisestate.splitbill


import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.boisestate.splitbill.DTO.ToDo
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
       // setSupportActionBar(dashboard_toolbar1)
        title = "My Notes"


        dbHandler = DBHandler(this)
        rv_record.layoutManager = LinearLayoutManager(this)


        fab_record.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
            val toDoName = view.findViewById<EditText>(R.id.et_todo)

            dialog.setView(view)

           dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                if (toDoName.text.isNotEmpty()) {
                    val toDo = ToDo()
                    toDo.name = toDoName.text.toString()
                    dbHandler.addToDo(toDo)
                    refreshList()
                }

            }
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

            }
            dialog.show()
        }
    }


    override fun onResume () {
        refreshList()
        super.onResume()
    }

    private fun refreshList(){
        rv_record.adapter = RecordAdapter(this,dbHandler.getToDos())
    }



        class RecordAdapter(val context: Context, val list: MutableList<ToDo>) :
            RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

            override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
                return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_child_dashboard, p0, false))
            }

            override fun getItemCount(): Int {
             return list.size

             }

            override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
                holder.toDoName.text = list[p1].name
            }

             class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
                val toDoName: TextView = v.findViewById(R.id.tv_todo_name)
        }
    }
}
