package org.hammerlab.genomics.bases

import org.hammerlab.genomics.bases.Base._
import org.hammerlab.genomics.bases.Bases.Bytes

import scala.collection.generic.CanBuildFrom
import scala.collection.{ IndexedSeqLike, mutable }

/**
 * [[Bases]] wraps a [[Vector[Byte]]] where each [[Byte]] holds the ASCII value of one of 5 values: A, C, G, T, N.
 */
class Bases private(val bytes: Bytes)
  extends AnyVal
    with BasesBuffer
    with IndexedSeqLike[Base, Bases] {

  protected[this] override def thisCollection: IndexedSeq[Base] = bytes
  protected[this] override def toCollection(repr: Bases): IndexedSeq[Base] = bytes

  override protected[this] def newBuilder: mutable.Builder[Base, Bases] = Bases.newBuilder

  override def seq: IndexedSeq[Base] = bytes

  override type LengthT = Int

  override def length: LengthT = bytes.length

  def apply(idx: LengthT): Base = bytes(idx)

  /** Watson-Crick complement of a sequence of bases. */
  def complement: Bases = map(_.complement)

  /** Watson-Crick complement of a sequence of bases, with the sequence reversed. */
  def reverseComplement: Bases = reverse.complement

  /** Are all the given bases standard? */
  def allStandardBases: Boolean = bytes.forall(_.isStandardBase)

  def +(right: Bases): Bases = this ++ right

  override def toString: String = bytes.map(_.toChar).mkString
}

object Bases {

  val empty = apply()

  type Bytes = Vector[Base]

  // Externally-accessible constructors.
  def apply(bytes: Bytes): Bases = new Bases(bytes.map(_.unmask))
  def apply(bytes: Base*): Bases = apply(Vector(bytes: _*))
  def apply(bytes: Array[Base]): Bases = Bases(bytes.toVector)

  /** Convert a string (e.g. "AAAGGC") to a byte array. */
  def apply(basesStr: String): Bases = Bases(basesStr.iterator.map(_.toUpper.toByte: Base).toVector)

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
    val ord = iteratorOrdering[Base]
    override def compare(x: Bases, y: Bases): Int =
      ord.compare(x.bytes.iterator, y.bytes.iterator)
  }

  def newBuilder: mutable.Builder[Base, Bases] = Vector.newBuilder[Base].mapResult(Bases(_))

  implicit def canBuildFromBases: CanBuildFrom[Bases, Base, Bases] =
    new CanBuildFrom[Bases, Base, Bases] {
      override def apply(from: Bases): mutable.Builder[Base, Bases] = newBuilder
      override def apply(): mutable.Builder[Base, Bases] = newBuilder
    }

  implicit val canBuildFromSeqs =
    new CanBuildFrom[Iterable[_], Base, Bases] {
      override def apply(from: Iterable[_]): mutable.Builder[Base, Bases] = Bases.newBuilder
      override def apply(): mutable.Builder[Base, Bases] = Bases.newBuilder
    }

  implicit val canBuildFromArrays =
    new CanBuildFrom[Array[_], Base, Bases] {
      override def apply(from: Array[_]): mutable.Builder[Base, Bases] = Bases.newBuilder
      override def apply(): mutable.Builder[Base, Bases] = Bases.newBuilder
    }
}
