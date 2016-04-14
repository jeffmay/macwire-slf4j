
scalaVersion := "2.11.7"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases")
)

libraryDependencies ++= Seq(
  "com.softwaremill.macwire" %% "macros" % "2.2.2",
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "org.scalatest" %% "scalatest" % "2.2.5"
)
