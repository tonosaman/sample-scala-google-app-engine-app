import sbtappengine.Plugin.{AppengineKeys => gae}

name := "sample"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "0.7.0",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
)

appengineSettings

(gae.onStartHooks in gae.devServer in Compile) += { () =>
  println("hello")
}

(gae.onStopHooks in gae.devServer in Compile) += { () =>
  println("bye")
}

appengineDataNucleusSettings

gae.persistenceApi in gae.enhance in Compile := "JDO"
