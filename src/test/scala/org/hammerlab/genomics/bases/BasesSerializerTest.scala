package org.hammerlab.genomics.bases

import org.hammerlab.spark.test.suite.{ KryoSparkSuite, SparkSerialization }

class BasesSerializerTest
  extends KryoSparkSuite(classOf[Registrar])
    with SparkSerialization
    with BasesUtil {

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
