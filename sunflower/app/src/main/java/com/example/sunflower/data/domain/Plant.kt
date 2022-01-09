package com.example.sunflower.data.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.Calendar.DAY_OF_YEAR

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7,
    val imageUrl: String = ""
) {
    /**
     * 是否需要浇水，如果当前的日期 > (最后一次浇水的日期 + 浇水的间隔) 则返回 true，
     * 否则，返回 false
     */
    fun shouldBeWatered(curDate: Calendar, lastWateringDate: Calendar) =
        // .apply{ lambda } 直接返回的是运算后的 lastWateringDate 对象, add() 是 void 方法
        curDate > lastWateringDate.apply { add(DAY_OF_YEAR, wateringInterval) }

    override fun toString(): String = name
}