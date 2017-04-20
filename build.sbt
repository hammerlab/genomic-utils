organization := "org.hammerlab.genomics"
name := "utils"
version := "1.2.3-SNAPSHOT"
deps += libs.value('htsjdk)
enableMacroParadise
addSparkDeps
publishTestJar
