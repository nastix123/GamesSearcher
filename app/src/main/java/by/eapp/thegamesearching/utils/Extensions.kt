package by.eapp.thegamesearching.utils

fun String.removeTagsHTML(): String {
    val pattern = "<.*?>"
    return Regex(pattern, RegexOption.DOT_MATCHES_ALL).replace(this, "")
}