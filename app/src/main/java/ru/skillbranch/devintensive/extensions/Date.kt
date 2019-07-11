package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

enum class TimeUnits {
    SECOND {
        override fun plural(num: Int): String {
            return getWordForm(num, "секунд", "у", "ы", "")
        }

        override fun getMSec(): Long {
            return 1000;
        }
    },
    MINUTE {
        override fun plural(num: Int): String {
            return getWordForm(num, "минут", "у", "ы", "")
        }

        override fun getMSec(): Long {
            return 60*SECOND.getMSec();
        }
    },
    HOUR {
        override fun plural(num: Int): String {
            return getWordForm(num, "час", "", "а", "ов")
        }

        override fun getMSec(): Long {
            return 60*MINUTE.getMSec();
        }
    },
    DAY {
        override fun plural(num: Int): String {
            return getWordForm(num, "", "день", "дня", "дней")
        }

        override fun getMSec(): Long {
            return 24*HOUR.getMSec();
        }
    };

    protected fun getWordForm(num: Int, base:String, single:String, paucus:String, plural:String):String {
        if (num in 5..20) {
            return "$num $base$plural"
        }
        return when (num % 10) {
            1 -> "$num $base$single"
            in 2..4 -> "$num $base$paucus"
            else -> "$num $base$plural"
        }
    }
    abstract fun getMSec():Long
    abstract fun plural(num:Int):String
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, unit:TimeUnits): Date {
    this.time += value.toLong() * unit.getMSec();
    return this;
}

fun Date.humanizeDiff() : String {
    var diff:Long = Date().time - this.time;
    val before:Boolean = diff > 0;
    diff = abs(diff);
    if (diff > 360*TimeUnits.DAY.getMSec()) {
        return if (before) "более года назад" else "более чем через год"
    }
    val humanizedDiff:String = when (diff) {
        in 0..TimeUnits.SECOND.getMSec() -> "только что"
        in TimeUnits.SECOND.getMSec()..TimeUnits.SECOND.getMSec()*45 -> "несколько секунд"
        in TimeUnits.SECOND.getMSec()*45..TimeUnits.SECOND.getMSec()*75 -> "минуту"
        in TimeUnits.SECOND.getMSec()*75..TimeUnits.MINUTE.getMSec()*45 -> TimeUnits.MINUTE.plural((diff/TimeUnits.MINUTE.getMSec()).toInt())
        in TimeUnits.MINUTE.getMSec()*45..TimeUnits.MINUTE.getMSec()*75 -> "час"
        in TimeUnits.MINUTE.getMSec()*75..TimeUnits.HOUR.getMSec()*22 -> TimeUnits.HOUR.plural((diff/TimeUnits.HOUR.getMSec()).toInt())
        in TimeUnits.HOUR.getMSec()*22..TimeUnits.HOUR.getMSec()*26 -> "день"
        else -> TimeUnits.DAY.plural((diff/TimeUnits.DAY.getMSec()).toInt())
    }
    return if (before) "$humanizedDiff назад" else "через $humanizedDiff"
}