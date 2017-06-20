organization := "org.hammerlab.genomics"
name := "utils"
version := "1.2.3"
deps += libs.value('htsjdk)
enableMacroParadise
addSparkDeps
publishTestJar
