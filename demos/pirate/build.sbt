//-----------------------------------
// The essentials.
//-----------------------------------
lazy val pirate =
  (project in file("."))
    .enablePlugins(
      ScalaJSPlugin, // Enable the Scala.js
      SbtIndigo      //  Enable Indigo plugin
    )
    .settings( // Standard SBT settings
      name := "pirate",
      version := "0.0.1",
      scalaVersion := "2.13.3",
      organization := "pirate",
      libraryDependencies ++= Seq(
        "com.lihaoyi"    %%% "utest"      % "0.7.4"  % "test",
        "org.scalacheck" %%% "scalacheck" % "1.14.3" % "test"
      ),
      testFrameworks += new TestFramework("utest.runner.Framework"),
      wartremoverWarnings in (Compile, compile) ++= Warts.unsafe
    )
    .settings( // Indigo specific settings
      showCursor := true,
      title := "The Cursed Pirate",
      gameAssetsDirectory := "assets",
      libraryDependencies ++= Seq(
        "io.indigoengine" %%% "indigo-json-circe" % "0.2.0", // Needed for Aseprite & Tiled support
        "io.indigoengine" %%% "indigo"            % "0.2.0", // Important! :-)
        "io.indigoengine" %%% "indigo-extras"     % "0.2.0" // Important! :-)
      )
    )

addCommandAlias("buildGame", ";compile;fastOptJS;indigoBuildJS")
addCommandAlias("buildGameFull", ";clean;update;compile;test;fastOptJS;indigoBuildJS")
addCommandAlias("publishGame", ";compile;fullOptJS;indigoPublishJS")
addCommandAlias("publishGameFull", ";clean;update;compile;test;fullOptJS;indigoPublishJS")
