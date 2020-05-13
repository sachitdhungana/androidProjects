package edu.boisestate.jsonpractice

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.getSystemService
import edu.boisestate.jsonpractice.R.layout.activity_steps
import kotlinx.android.synthetic.main.activity_steps.*

class StepsActivity : AppCompatActivity(),SensorEventListener {


    var running = false
    var sensorManager: SensorManager? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_steps)
        setSupportActionBar(steps_toolbar)
        title="Your Total Steps!! "
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null){
            Toast.makeText(this, "No Step counter Sensor !", Toast.LENGTH_SHORT ).show()
        }else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }


    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)

    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(running) {
            stepsValue.setText("" + event.values[0])
        }


    }



}
