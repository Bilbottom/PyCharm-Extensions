/*
    Count the total number of characters across the selection. Intended to be
    used on a single cell at a time to quickly determine the length of its
    contents.
*/


def RES = 0G
ROWS.each { row ->
  COLUMNS.each { column ->
    if (row.value(column) != null) {
      RES += row.value(column).toString().length()
    }
  }
}
OUT.append(RES.toString())
