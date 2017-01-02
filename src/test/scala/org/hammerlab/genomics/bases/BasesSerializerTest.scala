package org.hammerlab.genomics.bases

import org.hammerlab.spark.test.suite.{ KryoSparkSuite, SparkSerialization }

class BasesSerializerTest
  extends KryoSparkSuite(classOf[Registrar])
    with SparkSerialization
    with BasesUtil {

  def check(bases: Bases): Unit =
    deserialize[Bases](serialize(bases)) should ===(bases)

  test("some bases") {
    check("AACGTTCA")
  }

  test("empty") {
    check("")
  }

  test("single") {
    check("C")
  }
}
