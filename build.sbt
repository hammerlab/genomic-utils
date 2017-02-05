organization := "org.hammerlab.genomics"
name := "utils"
version := "1.2.0"
deps += libs.value('htsjdk)
addSparkDeps
publishTestJar
