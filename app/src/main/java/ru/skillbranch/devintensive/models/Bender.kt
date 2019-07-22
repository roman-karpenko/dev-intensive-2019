package ru.skillbranch.devintensive.models

class Bender (var status:Status = Status.NORMAL, var question:Question = Question.NAME) {

    fun askQuestion():String = question.question

    fun listenAnswer(answer: String):Pair<String, Triple<Int, Int, Int>> {
        val error = question.validate(answer)
        if(error != null) {
            return "${error}\n${question.question}" to status.color
        }
        return if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }
        else {
            val wrongAnswer: String = "Это неправильный ответ"
            if (status == Status.CRITICAL) {
                status = Status.NORMAL
                question = Question.NAME
                "$wrongAnswer. Давай всё по новой\n${question.question}" to status.color
            }
            else {
                status = status.nextStatus()
                "$wrongAnswer\n${question.question}" to status.color
            }
        }
    }

    enum class Status(val color:Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;
        fun nextStatus():Status {
            val values = values()
            return if (this.ordinal < values.lastIndex) {
                values[this.ordinal+1]
            }
            else {
                values[0]
            }
        }
    }

    enum class Question (val question: String, val answers:List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun validate(answer: String): String? {
                return if (answer[0] != answer[0].toUpperCase()) {
                    "Имя должно начинаться с заглавной буквы"
                }
                else {
                    return null
                }
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun validate(answer: String): String? {
                return if (answer[0] != answer[0].toLowerCase()) {
                    "Профессия должна начинаться со строчной буквы"
                }
                else {
                    return null
                }
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun validate(answer: String): String? {
                if (Regex("[1-9]").find(answer)!=null) {
                    return "Материал не должен содержать цифр"
                }
                else {
                    return null
                }
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun validate(answer: String): String? {
                if (Regex("[^1-9]").find(answer)!=null) {
                    return "Год моего рождения должен содержать только цифры"
                }
                else {
                    return null
                }
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun validate(answer: String): String? {
                if (Regex("[1-9]{7}").find(answer) == null) {
                    return "Серийный номер содержит только цифры, и их 7"
                }
                else {
                    return null
                }
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun validate(answer: String): String? {
                return null
            }
        };
        fun nextQuestion():Question {
            val values = values()
            return if (this.ordinal < values.lastIndex) {
                values[this.ordinal+1]
            }
            else {
                values[0]
            }
        }

        abstract fun validate(answer:String):String?
    }
}