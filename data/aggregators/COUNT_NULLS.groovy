/*
    Count the number of cells that have a null value.
*/

def RES = 0G
ROWS.each { row ->
  COLUMNS.each { column ->
    def value = row.value(column)
    if (value == null) {
      RES += 1
    }
  }
}
OUT.append(RES.toString())
