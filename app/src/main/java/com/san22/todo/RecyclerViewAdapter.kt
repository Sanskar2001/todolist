package com.san22.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_task.view.*
import kotlinx.android.synthetic.main.itemv.view.*
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter(val list:List<task>):RecyclerView.Adapter<RecyclerViewAdapter.taskviewholder>()
{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskviewholder {
       return taskviewholder(LayoutInflater.from(parent.context).inflate(R.layout.itemv,parent,false))
    }

    override fun onBindViewHolder(holder: taskviewholder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    override fun getItemCount()=list.size

    class taskviewholder(itemview:View):RecyclerView.ViewHolder(itemview) {
        fun bind(tasks: task) {
          with(itemView)
          {
              tv.text= tasks.title
              desbox.text=tasks.descrip
              tvcat.text=tasks.category
              val tformat="h:mm a"
              val colors = resources.getIntArray(R.array.random_color)
              val randomColor = colors[Random().nextInt(colors.size)]
              colortag.setBackgroundColor(randomColor)
              val dformat="EEE,d MMM,yyyy"
              val sdft=SimpleDateFormat(tformat)
              val sdfd=SimpleDateFormat(dformat)
              tvtime.text=sdft.format(Date(tasks.time))
              tvdate.text=sdfd.format(Date(tasks.date))


          }
        }

    }

}

