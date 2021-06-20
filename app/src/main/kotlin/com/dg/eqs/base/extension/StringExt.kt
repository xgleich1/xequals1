package com.dg.eqs.base.extension

import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.text.HtmlCompat.fromHtml


fun String.indicesOf(char: Char) =
        mapIndexedNotNull { index, c ->
            index.takeIf { c == char }
        }

fun String.toHtmlStyledSpanned() =
        fromHtml(this, FROM_HTML_MODE_LEGACY)

fun String.split(indices: List<Int>): List<String> {
    var splitStartIndex = 0

    return buildList {
        indices.forEach { index ->
            add(substring(splitStartIndex, index))

            splitStartIndex = index + 1
        }
        add(substring(splitStartIndex, length))
    }
}

fun String.replace(index: Int, replacement: String) =
        replaceRange(index..index, replacement)

fun String.replace(char: Char, transform: () -> String) =
        replace("$char".toRegex()) { transform() }