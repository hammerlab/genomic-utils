package org.hammerlab.genomics

import htsjdk.samtools.{ Cigar, CigarElement, CigarOperator, TextCigarCodec }

package object cigar {

  /**
   * Enriched HTSJDK CigarElement.
   */
  implicit class Element(val element: CigarElement) extends AnyVal {
    /**
     * The length of a cigar element in read coordinate space.
     *
     * @return Length of CigarElement, 0 if it does not consume any read bases.
     */
    def getReadLength: Int =
      if (element.getOperator.consumesReadBases)
        element.getLength
      else
        0

    /**
     * The length of a cigar element in reference coordinate space.
     *
     * @return Length of CigarElement, 0 if it does not consume any reference bases.
     */
    def getReferenceLength: Int =
      if (element.getOperator.consumesReferenceBases)
        element.getLength
      else
        0

    /** Is the given samtools CigarElement a (hard/soft) clip? */
    def isClipped: Boolean =
      element.getOperator == CigarOperator.SOFT_CLIP ||
        element.getOperator == CigarOperator.HARD_CLIP
  }

  implicit def makeCigar(cigarStr: String): Cigar = TextCigarCodec.decode(cigarStr)
}
