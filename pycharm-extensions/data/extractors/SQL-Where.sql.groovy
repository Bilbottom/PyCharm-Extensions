/*
    Join the selected cells' values with the column names ready to be pasted
    into a `WHERE` clause. Intended to be used for a single row at a time, but
    can be used on many.
*/


package extensions.data.extractors

QUOTE = "'"
STRING_PREFIX = DIALECT.getDbms().isMicrosoft() ? "N" : ""
KEYWORDS_LOWERCASE = com.intellij.database.util.DbSqlUtil.areKeywordsLowerCase(PROJECT)
KW_NULL = KEYWORDS_LOWERCASE ? "null" : "NULL"
NEWLINE = System.getProperty("line.separator")

OUT.append("WHERE 1=1")
ROWS.each { row ->
    COLUMNS.each { column ->
        def value = row.value(column)
        def stringValue = value == null ? KW_NULL : FORMATTER.formatValue(value, column)
        def isStringLiteral = value != null && FORMATTER.isStringLiteral(value, column)
        if (isStringLiteral && DIALECT.getDbms().isMysql()) stringValue = stringValue.replace("\\", "\\\\")
        OUT.append(NEWLINE)
           .append("AND " + column.name() + " = ")
           .append(isStringLiteral ? (STRING_PREFIX + QUOTE) : "")
           .append(stringValue ? stringValue.replace(QUOTE, QUOTE + QUOTE) : stringValue)
           .append(isStringLiteral ? QUOTE : "")
    }
}
