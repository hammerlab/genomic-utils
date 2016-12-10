package org.hammerlab.genomics

import org.hammerlab.genomics.Bases.{ basesToString, reverseComplement, stringToBases }
import org.hammerlab.test.Suite

class BasesSuite extends Suite {
  test("string conversions, reverse complement") {
    basesToString(reverseComplement(stringToBases("AGGTCA"))) should equal("TGACCT")
  }
}
