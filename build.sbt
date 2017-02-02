organization := "org.hammerlab.genomics"
name := "utils"
version := "1.2.0-SNAPSHOT"
deps += libs.value('htsjdk)
addSparkDeps
publishTestJar
