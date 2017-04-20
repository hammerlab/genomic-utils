package org.hammerlab.genomics.bases

import org.hammerlab.genomics.bases.Base._

class Base private(val byte: Byte) extends AnyVal {
  /** Watson-Crick complement of a base. */
  def complement: Base =
    this match {
      case A ⇒ T
      case C ⇒ G
      case G ⇒ C
      case T ⇒ A
      case _ ⇒ N
    }

  def isStandardBase: Boolean =
    this == A || this == C || this == T || this == G

  def unmask: Base = makeBase(byte)

  override def toString: String = byte.toChar.toString
}

object Base {

  def apply(byte: Byte): Base = byte

  // The only externally-accessible way to construct Base instances.
  implicit def makeBase(byte: Byte): Base =
    byte match {
      case 'A'|'a' ⇒ A
      case 'C'|'c' ⇒ C
      case 'G'|'g' ⇒ G
      case 'T'|'t' ⇒ T
      case _ ⇒ N
    }

  implicit def unmakeBase(base: Base): Byte = base.byte

  implicit val baseOrd =
    new Ordering[Base] {
      override def compare(x: Base, y: Base): Int = x.byte.compare(y.byte)
    }

  /** Standard bases. Note that other bases are sometimes used as well (e.g. "N"). */
  val A: Base = new Base('A')
  val C: Base = new Base('C')
  val T: Base = new Base('T')
  val G: Base = new Base('G')

  // Unknown Base
  val N: Base = new Base('N')
}
