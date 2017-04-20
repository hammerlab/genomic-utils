# genomic-utils

[![Build Status](https://travis-ci.org/hammerlab/genomic-utils.svg?branch=master)](https://travis-ci.org/hammerlab/genomic-utils)
[![Coverage Status](https://coveralls.io/repos/github/hammerlab/genomic-utils/badge.svg?branch=master)](https://coveralls.io/github/hammerlab/genomic-utils?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/org.hammerlab.genomics/utils_2.11.svg?maxAge=600)](http://search.maven.org/#search%7Cga%7C1%7Cgenomic-utils)

Utilities for working with genomic bases, cigar strings, and phred scores.

- [`Bases`][] wraps a `Vector[Base]`, and implements [`BasesBuffer`][], with `Int` length
	- well-suited for sub-contig-sized genomic-sequences, e.g. reads.
- [`BasesBuffer`][] is a more general interface that can also be implemented for contig-sized sequences (often with `Long` length); see [`Contig`](https://github.com/hammerlab/genomic-reference/blob/1.1.0_2.11/src/main/scala/org/hammerlab/genomics/reference/Contig.scala).
- [`Base`][] wraps a `Byte`, and only a maximum of 5 are instantiated (`A`, `C`, `G`, `T`, and `N`).
- All "wrapping" referenced above uses [value classes][] and carries no run-time cost.

[`Bases`]: src/main/scala/org/hammerlab/genomics/bases/Bases.scala
[`BasesBuffer`]: src/main/scala/org/hammerlab/genomics/bases/BasesBuffer.scala
[`Base`]: src/main/scala/org/hammerlab/genomics/bases/Base.scala
[value classes]: http://docs.scala-lang.org/overviews/core/value-classes.html
