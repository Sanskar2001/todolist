package com.san22.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
const val Dbname="todo.db"
@Database(entities = [task::class],version = 1)
abstract class Appdatabase:RoomDatabase() {
    abstract fun taskDao(): taskdao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: Appdatabase? = null

        fun getDatabase(context: Context): Appdatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Appdatabase::class.java,
                    Dbname
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}