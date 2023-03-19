/*
    Count the number of cells (not the number of characters) that have at least
    one "bad character". As an English speaker, a "good character" is any
    character between a space (` `, U+0020) and a tilde (`~`, U+0126) on the
    ASCII/unicode scale, and a "bad character" is any character that is not a
    "good character".

    Null values are not counted (but the null character, U+0000, is).
*/


def RES = 0G
ROWS.each { row ->
  COLUMNS.each { column ->
    def value = row.value(column).toString()
    if (value.find(/[^ -~]/) != null) {
      RES += 1
    }
  }
}
OUT.append(RES.toString())
