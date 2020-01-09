package org.myyrakle.myblog.utility

class HtmlEscaper(private var rowText:String)
{
    constructor() : this("")

    fun fromRowText(rowText: String)
    {
        this.rowText = rowText
    }

    fun toEscapedText(): String =
        rowText.map {
            when(it)
            {
                '<'->"&lt;"
                '>'->"&gt;"
                ' '->"&nbsp;"
                '\n'->"<br>"
                '\t' ->"&nbsp;&nbsp;&nbsp;&nbsp;"
                else -> it.toString()
            }
        }.joinToString(separator = "")
}