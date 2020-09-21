package com.san22.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val lister = arrayListOf<task>()
    var adapt = RecyclerViewAdapter(lister)

    val db by lazy {
       Appdatabase.getDatabase(this)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         setSupportActionBar(toolbar)


        rv.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=this@MainActivity.adapt
        }
        fb.setOnClickListener()
        {
            startActivity(Intent(this,TaskActivity::class.java))


        }

        db.taskDao().gettask().observe(this, Observer {


            if (!it.isNullOrEmpty()) {


                lister.clear()
                lister.addAll(it)

                Log.i("Data Fetching ${it.size}"," Attempted")
                Log.i("Size of internal list=","${lister.size}")

            } else {
                lister.clear()
                Log.i("First element"," OK")

            }
            adapt.notifyDataSetChanged()
            Log.i("Size of list=","${it.size}")
        })








    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.history->
                startActivity(Intent(this,historyActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
