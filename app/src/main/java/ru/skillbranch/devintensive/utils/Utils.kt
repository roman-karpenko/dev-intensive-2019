package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName.isNullOrBlank()) {
            return null to null;
        }
        val names: List<String>? = fullName.split(' ');
        return names?.getOrNull(0) to names?.getOrNull(1);
    }

    fun toInitials(firstName: String?, lastName:String?): String? {
        var initials: MutableList<Char> = mutableListOf()
        if (!firstName.isNullOrBlank()) {
            initials.add(firstName[0].toUpperCase())
        }
        if (!lastName.isNullOrBlank()) {
            initials.add(lastName[0].toUpperCase())
        }
        return if (initials.isEmpty()) {
            null
        } else {
            initials.joinToString(separator = "")
        }
    }

    fun transliteration(payload: String, divider: String = " ") : String {
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
            var lat = letters[it.toLowerCase()];
            if (lat != null) {
                translit.append(lat);
            }
            else {
                translit.append(it)
            }
            if(lat != null && it.isUpperCase()) {
                translit[translit.count() - lat.count()] = translit[translit.count() - lat.count()].toUpperCase()
            }
        }
        return translit.toString()
    }
}