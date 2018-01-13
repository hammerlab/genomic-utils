package org.hammerlab.genomics.bases

import org.hammerlab.spark.test.suite.{ KryoSparkSuite, SparkSerialization }

class BasesSerializerTest
  extends KryoSparkSuite
    with SparkSerialization
    with BasesUtil {

  registrar[Registrar]

  def check(bases: Bases, otherBases: Bases = ""): Unit = {
    val deserd = deserialize[Bases](serialize(bases))
    deserd should ===(bases)
    deserd should !==(otherBases)
  }

  test("some bases") {
    check("AACGTTCA", "AACGTTCG")
  }

  test("empty") {
    check("", "G")
  }

  test("single") {
    check("C", "A")
  }
}
