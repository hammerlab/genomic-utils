organization := "org.hammerlab.genomics"
name := "utils"
version := "1.0.0"

libraryDependencies ++= Seq(
  libraries.value('adam_core),
  libraries.value('htsjdk)
)

testDeps += libraries.value('test_utils)
