package com.example.funhouse.JailHouse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "states")
//class State(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    @ColumnInfo(name = "state") val state: String)
@Entity(tableName = "jails")
class JailObject(
    @PrimaryKey(autoGenerate = true) val id: Int,
    //@ColumnInfo(name = "stateID") val state_id: Int,
    @ColumnInfo(name = "deptFull") val dept_full : String,
    @ColumnInfo(name = "deptSource") val dept_source : String,
    @ColumnInfo(name = "state") val state: String
)
