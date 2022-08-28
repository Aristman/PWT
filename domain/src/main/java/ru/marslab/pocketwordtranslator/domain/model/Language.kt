package ru.marslab.pocketwordtranslator.domain.model

enum class Language {
    Rus,
    Eng
}

fun Language.switch(): Language =
    if (this == Language.Rus) {
        Language.Eng
    } else {
        Language.Rus
    }
