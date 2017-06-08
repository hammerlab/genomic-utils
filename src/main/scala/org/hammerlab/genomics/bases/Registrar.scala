package org.hammerlab.genomics.bases

import com.esotericsoftware.kryo.Kryo
import org.apache.spark.serializer.KryoRegistrator

class Registrar
  extends KryoRegistrator {
  override def registerClasses(kryo: Kryo): Unit = {
    kryo.register(classOf[Bases], new BasesSerializer)
  }
}
