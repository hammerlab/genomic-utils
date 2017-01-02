package org.hammerlab.genomics.bases

import org.hammerlab.test.Suite

class BasesTest
  extends Suite
    with BasesUtil {
  test("string conversions, reverse complement") {
    "AGGTCA".reverseComplement should ===("TGACCT")
    "AGGTCA".complement should ===("TCCAGT")
  }

  test("masking") {
    ("aggtca".getBytes(): Bases).reverseComplement should ===("TGACCT")
  }
}
