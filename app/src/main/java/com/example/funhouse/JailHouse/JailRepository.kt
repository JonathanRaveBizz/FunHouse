package com.example.funhouse.JailHouse

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class JailRepository(private val jailDAO: JailDAO) {

    val allJails: Flow<List<JailObject>> = jailDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(jailObject: JailObject)
    {
        jailDAO.insert(jailObject)
    }
}