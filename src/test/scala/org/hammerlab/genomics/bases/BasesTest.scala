package org.hammerlab.genomics.bases

import org.hammerlab.test.Suite

class BasesTest
  extends Suite {

  {
    // The following few tests can make use of convenient implicits.
    import BasesUtil._

    test("string conversions, reverse complement") {
      ===("AGGTCA".reverseComplement, "TGACCT")
      ===("AGGTCA".complement, "TCCAGT")
    }

    test("masking") {
      ===("aggtca".reverseComplement, "TGACCT")
    }

    test("default ordering") {
      ===(
        Array[Bases](
          "CCTT",
          "CCNG",
          "CATT",
          "AAAA",
          "TTTT",
          "TCCT"
        )
        .sorted,
        Array(
          "AAAA",
          "CATT",
          "CCNG",
          "CCTT",
          "TCCT",
          "TTTT"
        )
      )
    }
  }

  test("take") {
    ==(Bases("AGGTCA").take(3), Bases("AGG"))
  }

  test("flatMap bases") {
    val bases: Bases =
      Seq(
        Bases("AGGTCA"),
        Bases("TCGATCGA"),
        Bases("AACCGGTT")
      )
      .flatMap(_.takeRight(2))

    ==(bases, Bases("CAGATT"))
  }

  test("flatMap other") {
    val bases: Bases =
      Seq(
        Foo("abc", Bases("AGGTCA")),
        Foo("def", Bases("TCGATCGA")),
        Foo("ghi", Bases("AACCGGTT"))
      )
      .flatMap(_.bases)

    ==(bases, Bases("AGGTCATCGATCGAAACCGGTT"))
  }

  test("concatenation") {
    ==(Bases("AACC") ++ Bases("GGTT"), Bases("AACCGGTT"))
  }
}

case class Foo(s: String, bases: Bases)
