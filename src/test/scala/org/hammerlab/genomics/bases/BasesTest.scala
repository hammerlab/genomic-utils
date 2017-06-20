package org.hammerlab.genomics.bases

import org.hammerlab.test.Suite

class BasesTest
  extends Suite {

  {
    // The following few tests can make use of convenient implicits.
    import BasesUtil._

    test("string conversions, reverse complement") {
      "AGGTCA".reverseComplement should ===("TGACCT")
      "AGGTCA".complement should ===("TCCAGT")
    }

    test("masking") {
      "aggtca".reverseComplement should ===("TGACCT")
    }

    test("default ordering") {
      Array[Bases](
        "CCTT",
        "CCNG",
        "CATT",
        "AAAA",
        "TTTT",
        "TCCT"
      )
      .sorted should ===(
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
    Bases("AGGTCA").take(3) should ===(Bases("AGG"))
  }

  test("flatMap bases") {
    val bases: Bases =
      Seq(
        Bases("AGGTCA"),
        Bases("TCGATCGA"),
        Bases("AACCGGTT")
      )
      .flatMap(_.takeRight(2))

    bases should ===(Bases("CAGATT"))
  }

  test("flatMap other") {
    val bases: Bases =
      Seq(
        Foo("abc", Bases("AGGTCA")),
        Foo("def", Bases("TCGATCGA")),
        Foo("ghi", Bases("AACCGGTT"))
      )
      .flatMap(_.bases)

    bases should ===(Bases("AGGTCATCGATCGAAACCGGTT"))
  }

  test("concatenation") {
    (Bases("AACC") ++ Bases("GGTT")) should ===(Bases("AACCGGTT"))
  }
}

case class Foo(s: String, bases: Bases)
