name := "Api"

version := "1.0"

lazy val `api` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies ++= Seq("org.reactivemongo" %% "play2-reactivemongo" % "0.11.11")

libraryDependencies ++= Seq("net.fwbrasil" % "activate-core_2.10" % "1.6.2")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

