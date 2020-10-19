package com.example.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "rps_table")
class Game (


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "result")
    var result: String,

    @ColumnInfo(name = "p_move")
    var pMove: Short,

    @ColumnInfo(name = "c_move")
    var cMove: Short,

    @ColumnInfo(name = "date")
    val date: Date
)