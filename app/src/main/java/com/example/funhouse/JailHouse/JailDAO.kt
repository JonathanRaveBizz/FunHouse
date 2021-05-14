package com.example.funhouse.JailHouse

import android.content.Context
import androidx.room.*

@Dao
interface JailDAO {
    @Query("SELECT * FROM states ORDER BY state ASC")
    fun getStates(): List<State>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertState(state: State)

    @Query("SELECT * FROM jails WHERE :stateID")
    fun getDept(stateID: Int):List<JailObject>
}

public abstract class JailRoomDB : RoomDatabase() {

    abstract fun JailDAO(): JailDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: JailRoomDB? = null

        fun getDatabase(context: Context): JailRoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JailRoomDB::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}