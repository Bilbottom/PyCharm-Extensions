/*
    Count the number of distinct cells in the selection. Note that null values
    are not ignored and will count as a distinct value.
*/


values = new ArrayList<BigDecimal>()
ROWS.each { row ->
  COLUMNS.each { column ->
    def value = row.value(column)
    if (!values.contains(value)) {
      values.add(value)
    }
  }
}
if (values.size() == 0) {
  OUT.append("Not enough values")
  return
}
OUT.append(values.size().toString())
