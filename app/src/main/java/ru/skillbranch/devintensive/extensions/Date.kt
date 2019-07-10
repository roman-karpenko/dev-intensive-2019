package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

enum class TimeUnits {
    SECOND {
        override fun getDuration(): Int {
            return 1000;
        }
    },
    MINUTE {
        override fun getDuration(): Int {
            return 60*SECOND.getDuration();
        }
    },
    HOUR {
        override fun getDuration(): Int {
            return 60*MINUTE.getDuration();
        }
    },
    DAY {
        override fun getDuration(): Int {
            return 24*HOUR.getDuration();
        }
    };

    abstract fun getDuration():Int
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, unit:TimeUnits): Date {
    this.time += value * unit.getDuration();
    return this;
}