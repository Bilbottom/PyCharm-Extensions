/*
    Simply join the selected cells (values only) using a comma and space as a
    separator, quoting the values if needed.
*/


SEPARATOR = ", "
QUOTE     = "'"
STRING_PREFIX = DIALECT.getDbms().isMicrosoft() ? "N" : ""
KEYWORDS_LOWERCASE = com.intellij.database.util.DbSqlUtil.areKeywordsLowerCase(PROJECT)
KW_NULL = KEYWORDS_LOWERCASE ? "null" : "NULL"

first = true
ROWS.each { row ->
    COLUMNS.each { column ->
        def value = row.value(column)
        def stringValue = value == null ? KW_NULL : FORMATTER.formatValue(value, column)
        def isStringLiteral = value != null && FORMATTER.isStringLiteral(value, column)
        if (isStringLiteral && DIALECT.getDbms().isMysql()) stringValue = stringValue.replace("\\", "\\\\")
        OUT.append(first ? "" : SEPARATOR)
                .append(isStringLiteral ? (STRING_PREFIX + QUOTE) : "")
                .append(stringValue ? stringValue.replace(QUOTE, QUOTE + QUOTE) : stringValue)
                .append(isStringLiteral ? QUOTE : "")
        first = false
    }
}
