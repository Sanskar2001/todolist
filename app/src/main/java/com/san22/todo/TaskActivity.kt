package com.san22.todo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.room.Room
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*



class TaskActivity : AppCompatActivity(), View.OnClickListener {

    var category= arrayListOf<String>("Personal","Work","Banking","Birthday","Shopping")
    lateinit var mycalender:Calendar
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener
    var finalDate = 0L
    var finalTime = 0L

    val db by lazy{
      Appdatabase.getDatabase(this)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        datebox.setOnClickListener(this)
        timeedit.setOnClickListener(this)
        saveb.setOnClickListener(this)
        setupspin()
    }

    private fun setupspin() {
        spinner.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,category)

    }



    override fun onClick(view: View?) {

        when(view?.id)
        {
            R.id.datebox->
            {    Log.i("Pressed","OK")
                setlistener()
            }
            R.id.timeedit->
            {
                timesetlistener()
            }
            R.id.saveb->
            {  Log.i("Pressed","OK")
                saveTask()
            }
        }
    }

    private fun saveTask() {

        val cat=spinner.selectedItem.toString()
        val title= tasktitle.text.toString()
        val description=desbox1.text.toString()


        GlobalScope.launch(Dispatchers.Main) {
            val id= withContext(Dispatchers.IO) {

                return@withContext db.taskDao().inserttask( task(title,description,cat,finalDate,finalTime) )

            }




            finish()
        }
    }

    private fun timesetlistener() {



        mycalender=Calendar.getInstance()
        timeSetListener=TimePickerDialog.OnTimeSetListener() { _:TimePicker, hour: Int, min:Int ->
            mycalender.set(Calendar.HOUR_OF_DAY,hour)
            mycalender.set(Calendar.MINUTE,min)

            updateTime()
        }

        val timePickerDialog=TimePickerDialog(this,timeSetListener,mycalender.get(Calendar.HOUR_OF_DAY),mycalender.get(Calendar.MINUTE),false)
       timePickerDialog.show()
    }

    private fun updateTime() {

        val format="h:mm a"
        val sdf=SimpleDateFormat(format)
        timeedit.setText(sdf.format(mycalender.time))
        finalTime=mycalender.time.time

    }

    private fun setlistener() {
        mycalender=Calendar.getInstance()
        dateSetListener=DatePickerDialog.OnDateSetListener  { _: DatePicker, year: Int, month: Int, day: Int ->
            mycalender.set(Calendar.YEAR,year)
            mycalender.set(Calendar.MONTH,month)
            mycalender.set(Calendar.DAY_OF_MONTH,day)
             updateDate()
        }

        val datePickerDialog=DatePickerDialog(this,dateSetListener,mycalender.get(Calendar.YEAR),mycalender.get(Calendar.MONTH),mycalender.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.datePicker.minDate=System.currentTimeMillis()
        datePickerDialog.show()
       
    }

  private fun updateDate()
  {
      val format="EEE, d, MMM yyyy"
      val sdf=SimpleDateFormat(format)
      datebox.setText(sdf.format(mycalender.time))
      finalDate=mycalender.time.time

  }
}
