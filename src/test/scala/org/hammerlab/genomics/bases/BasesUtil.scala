package org.hammerlab.genomics.bases

trait StringToBasesConversions {
  /** Convert a string (e.g. "AAAGGC") to a byte array. */
  implicit def stringToBases(basesStr: String): Bases = Bases(basesStr)
}

trait BaseToBasesConversions {
  implicit def baseToBases(base: Base): Bases = Bases(base)
}

trait BasesUtil
  extends StringToBasesConversions
    with BaseToBasesConversions

object BasesUtil extends BasesUtil
