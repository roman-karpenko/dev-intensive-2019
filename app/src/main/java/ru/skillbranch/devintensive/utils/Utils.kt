package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName.isNullOrBlank()) {
            return null to null;
        }
        val names: List<String>? = fullName?.split(' ');
        return names?.getOrNull(0) to names?.getOrNull(1);
    }

    fun toInitials(firstName: String?, lastName:String?): String? {
        var initials: String = "";
        if (!firstName.isNullOrBlank()) {
            initials += firstName[0].toUpperCase().toString()
        }
        if (!lastName.isNullOrBlank()) {
            initials += lastName[0].toUpperCase().toString()
        }
        return if (initials.isBlank()) {
            null
        } else {
            initials
        }
    }

    /*fun transliteration(payload: String, divider: String = " ") : String {
        val translit: StringBuilder = StringBuilder()
        val letters = mapOf(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'с' to "s",
        'т' to "t",
        'у' to "u",
        'ф' to "f",
        'х' to "h",
        'ц' to "c",
        'ч' to "ch",
        'ш' to "sh",
        'щ' to "sh'",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya",
        ' ' to divider)
        payload.forEach {
            var lat:String? = letters[it.toLowerCase()];
            translit.append(lat);
            if(it.isUpperCase()) {
                translit[translit.length - lat!!.length] = translit[translit.length - lat!!.length].toUpperCase()
            }
        }
        return translit.toString()
    }*/
}