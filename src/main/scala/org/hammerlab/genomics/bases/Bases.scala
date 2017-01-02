package org.hammerlab.genomics.bases

import org.hammerlab.genomics.bases.Base._
import org.hammerlab.genomics.bases.Bases._

/**
 * [[Bases]] wraps a [[Vector[Byte]]] where each [[Byte]] holds the ASCII value of one of 5 values: A, C, G, T, N.
 */
class Bases private(val bytes: Bytes)
  extends AnyVal
    with BasesBuffer {

  override type LengthT = Int

  override def length: LengthT = bytes.length

  def apply(idx: LengthT): Base = bytes(idx)

  /** Watson-Crick complement of a sequence of bases. */
  def complement: Bases = bytes.map(_.complement: Byte)

  /** Watson-Crick complement of a sequence of bases, with the sequence reversed. */
  def reverseComplement: Bases = bytes.reverse.complement

  /** Are all the given bases standard? */
  def allStandardBases: Boolean = bytes.forall(_.isStandardBase)

  override def toString: String = bytes.map(_.toChar).mkString
}

object Bases {

  val empty = apply()

  type Bytes = Vector[Byte]

  // Externally-accessible constructors.
  def apply(bytes: Bytes): Bases = new Bases(bytes.map(_.unmask: Byte))
  def apply(bytes: Byte*): Bases = apply(Vector(bytes: _*))

  // Implicit constructors.
  implicit def makeBasesFromBytes(bytes: Bytes): Bases = Bases(bytes)
  implicit def makeBasesFromArray(bytes: Array[Byte]): Bases = Bases(bytes.toVector)

  implicit def unmakeBases(bases: Bases): Bytes = bases.bytes

  def iteratorOrdering[T](implicit ord: Ordering[T]) =
    new Ordering[Iterator[T]] {
      override def compare(x: Iterator[T], y: Iterator[T]): Int = {
        (x.hasNext, y.hasNext) match {
          case (false, false) ⇒ 0
          case (true, true) ⇒
            ord.compare(x.next(), y.next()) match {
              case 0 ⇒ compare(x, y)
              case v ⇒ v
            }
          case (false, true) ⇒ -1
          case (true, false) ⇒ 1
        }
      }
    }

  implicit object BasesOrdering extends Ordering[Bases] {
    val ord = iteratorOrdering[Byte]
    override def compare(x: Bases, y: Bases): Int =
      ord.compare(x.bytes.iterator, y.bytes.iterator)
  }
}
