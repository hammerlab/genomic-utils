package org.hammerlab.genomics.bases

/**
 * Interface for buffers of DNA/RNA bases; subclasses can specialize the length to e.g. [[Int]] (for run-of-the-mill
 * read-processing needs) or [[Long]] (for storing whole reference contigs).
 */
trait BasesBuffer
  extends Any {
  type LengthT
  def length: LengthT
  def apply(idx: LengthT): Base
}
