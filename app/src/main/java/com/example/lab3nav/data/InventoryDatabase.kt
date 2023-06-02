package com.example.lab3nav.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [InventoryItem::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun itemDao(): InventoryDao

    companion object {
        @Volatile
        private var Instance : InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context,
                    InventoryDatabase::class.java,
                    "inventory_database").
                fallbackToDestructiveMigration()
                    .build()
                    .also{Instance = it}
            }
        }

    }
}