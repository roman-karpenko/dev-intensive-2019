package ru.skillbranch.devintensive.extensions

import java.lang.StringBuilder

fun String.truncate(n:Int = 16): String {
    val newString = this.trimEnd()
    var n = n
    if (newString.length>n) {
        if (newString[n-1] == ' ') {
            n--;
        }
        return newString.substring(0, n) + "..."
    }
    return newString
}

fun String.stripHtml(): String {
    val regex = Regex("<[^>]+>| +(?= )")
    return regex.replace(this,"")
}