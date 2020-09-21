package com.san22.todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface taskdao {
    @Insert()
     suspend fun inserttask(task: task):Long

    @Query("Select * from task ")
    fun gettask():LiveData< List<task> >

    @Query("Update task set isfinished=1 where id=:uid")
    fun update(uid:Long)

    @Query("Delete from task where id=:uid")
    fun deletetask(uid: Long)
}