package org.hammerlab.genomics.bases

trait BasesUtil {
  implicit def makeBasesFromBase(base: Base): Bases = Bases(base)

  /** Convert a string (e.g. "AAAGGC") to a byte array. */
  implicit def stringToBases(basesStr: String): Bases = basesStr.toUpperCase.getBytes
}

object BasesUtil extends BasesUtil