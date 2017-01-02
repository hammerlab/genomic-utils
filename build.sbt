organization := "org.hammerlab.genomics"
name := "utils"
version := "1.1.1"
deps += libs.value('htsjdk)
addSparkDeps
publishTestJar
