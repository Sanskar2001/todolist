package com.san22.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class task(
    var title: String,
    var descrip:String,
    var category:String,
    var date:Long,
    var time:Long,
    var isfinished:Int=-1,
    @PrimaryKey(autoGenerate = true)
    var id:Long=0


) {


}