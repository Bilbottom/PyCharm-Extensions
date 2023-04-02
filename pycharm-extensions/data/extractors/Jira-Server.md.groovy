/*
    Format the cells appropriately for Jira Server.

    This is a modified version of the Markdown-Groovy extractor, but tweaked to
    return Atlassian server's wiki markup instead of Markdown.

    This is called "Jira-Server" rather than "Atlassian-Server" because I only
     use this in Jira -- Confluence only has the 'Visual' edit mode, whereas
     Jira has both 'Visual' and 'Text' edit modes.
*/


package extensions.data.extractors

NEWLINE = System.getProperty("line.separator")
BACKSLASH = "\\"
BACKQUOTE = "`"
LTAG = "<"
RTAG = ">"
ASTERISK = "*"
LPARENTH = "("
RPARENTH = ")"
LBRACKET = "["
RBRACKET = "]"
TILDE = "~"

SEPARATOR = "|"
HEADER_SEPARATOR = "||"


def printRow = { values, column_separator, valueToString ->
  values.eachWithIndex { value, idx ->
    def str = valueToString(value)
      .replace(BACKSLASH, BACKSLASH + BACKSLASH)
      .replace(SEPARATOR, BACKSLASH + SEPARATOR)
      .replace(BACKQUOTE, BACKSLASH + BACKQUOTE)
      .replace(ASTERISK, BACKSLASH + ASTERISK)
      .replace(LPARENTH, BACKSLASH + LPARENTH)
      .replace(RPARENTH, BACKSLASH + RPARENTH)
      .replace(LBRACKET, BACKSLASH + LBRACKET)
      .replace(RBRACKET, BACKSLASH + RBRACKET)
      .replace(TILDE, BACKSLASH + TILDE)
      .replace(LTAG, "&lt;")
      .replace(RTAG, "&gt;")
      .replaceAll("\r\n|\r|\n", "<br/>")
      .replaceAll("\t|\b|\f", "")

    OUT.append(column_separator + " " + str)
      .append(idx != values.size() - 1 ? " " : " " + column_separator + NEWLINE)
  }
}

if (TRANSPOSED) {
  def values = COLUMNS.collect { new ArrayList<String>([it.name()]) }
  def rowCount = 0
  ROWS.forEach { row ->
    COLUMNS.eachWithIndex { col, i -> values[i].add(FORMATTER.format(row, col)) }
    rowCount++
  }
  for (int i = 0; i <= rowCount; i++) {
    OUT.append(HEADER_SEPARATOR + " ")
  }
  OUT.append(SEPARATOR + NEWLINE)
  values.each { printRow(it, SEPARATOR) { it } }
}
else {
  printRow(COLUMNS, HEADER_SEPARATOR) { values=it.name() }
  ROWS.each { row -> printRow(COLUMNS, SEPARATOR) { FORMATTER.format(row, it) } }
}
