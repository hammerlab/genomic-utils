package org.hammerlab.genomics.bases

import com.esotericsoftware.kryo.io.{ Input, Output }
import com.esotericsoftware.kryo.{ Kryo, Serializer }

class BasesSerializer extends Serializer[Bases] {
  override def write(kryo: Kryo, output: Output, bases: Bases): Unit = {
    output.writeInt(bases.bytes.length, true)
    bases.foreach(output.writeByte)
  }

  override def read(kryo: Kryo, input: Input, cls: Class[Bases]): Bases = {
    val count: Int = input.readInt(true)
    val bytes = Vector.newBuilder[Byte]
    (0 until count).foreach(_ ⇒ bytes += input.readByte())
    bytes.result()
  }
}
