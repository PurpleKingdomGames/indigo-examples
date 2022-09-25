import scala.sys.process._
import scala.language.postfixOps

import sbtwelcome._

val scala3Version = "3.2.0"

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0"

lazy val commonSettings = Seq(
  version      := "0.0.1",
  scalaVersion := scala3Version,
  organization := "indigo-examples",
  libraryDependencies ++= Seq(
    "org.scalameta"   %%% "munit"         % "0.7.29" % Test,
    "io.indigoengine" %%% "indigo"        % "0.14.0",
    "io.indigoengine" %%% "indigo-extras" % "0.14.0"
  ),
  testFrameworks += new TestFramework("munit.Framework"),
  Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  scalafixOnCompile := true,
  semanticdbEnabled := true,
  semanticdbVersion := scalafixSemanticdb.revision,
  // Common Indigo settings
  disableFrameRateLimit := false,
  electronInstall       := indigoplugin.ElectronInstall.Latest,
  backgroundColor       := "black",
  showCursor            := true,
  gameAssetsDirectory   := "assets"
)

// Examples
lazy val basicSetup =
  project
    .in(file("basic-setup"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "basic-setup",
      title := "Basic Setup"
    )

lazy val blending =
  project
    .in(file("blending"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "blending-example",
      title := "Blending example"
    )

lazy val subSystems =
  project
    .in(file("subsystems"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "subsystems",
      title := "SubSystems Example"
    )

lazy val scenesSetup =
  project
    .in(file("scenes-setup"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "scenes-setup",
      title := "Scene Manager Setup"
    )

lazy val text =
  project
    .in(file("text"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "text-example",
      title := "Text example"
    )

lazy val inputfield =
  project
    .in(file("inputfield"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "input-field-example",
      title := "Input field example"
    )

lazy val button =
  project
    .in(file("button"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "button-example",
      title := "Button example"
    )

lazy val graphic =
  project
    .in(file("graphic"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "graphic-example",
      title := "Graphic example"
    )

lazy val group =
  project
    .in(file("group"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "group-example",
      title := "Group example"
    )

lazy val tiled =
  project
    .in(file("tiled"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name              := "tiled-example",
      title             := "Tiled example",
      windowStartWidth  := 19 * 32,
      windowStartHeight := 11 * 32,
      libraryDependencies ++= Seq(
        "io.indigoengine" %%% "indigo-json-circe" % "0.14.0"
      )
    )

lazy val sprite =
  project
    .in(file("sprite"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "sprite-example",
      title := "Sprite example"
    )

lazy val http =
  project
    .in(file("http"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "http-example",
      title := "Http example"
    )

lazy val websocket =
  project
    .in(file("websocket"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "websocket-example",
      title := "WebSocket example"
    )

lazy val automata =
  project
    .in(file("automata"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "automata-example",
      title := "Automata example"
    )

lazy val fireworks =
  project
    .in(file("fireworks"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name                  := "fireworks-example",
      title                 := "Fireworks!",
      windowStartWidth      := 1280,
      windowStartHeight     := 720,
      disableFrameRateLimit := true,
      libraryDependencies ++= Seq(
        "org.scalacheck" %%% "scalacheck" % "1.15.3" % "test"
      )
    )

lazy val audio =
  project
    .in(file("audio"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "audio-example",
      title := "Audio example"
    )

lazy val lighting =
  project
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      name              := "lighting Example",
      title             := "Lighting",
      windowStartWidth  := 684,
      windowStartHeight := 384
    )
    .settings(
      publish      := {},
      publishLocal := {}
    )

lazy val distortion =
  project
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      name              := "distortion",
      title             := "Distortion Example",
      windowStartWidth  := 684,
      windowStartHeight := 384
    )
    .settings(
      publish      := {},
      publishLocal := {}
    )

lazy val assetLoading =
  project
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      name  := "assetLoading",
      title := "Asset Loading Example"
    )
    .settings(
      publish      := {},
      publishLocal := {}
    )

lazy val effects =
  project
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      name              := "effects",
      title             := "Effects Example",
      windowStartWidth  := 550,
      windowStartHeight := 400
    )
    .settings(
      publish      := {},
      publishLocal := {}
    )

lazy val radio =
  project
    .in(file("radio"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name  := "radio-example",
      title := "Radio button example"
    )

lazy val jobs =
  project
    .in(file("jobs"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name              := "jobs-example",
      title             := "Job System Example",
      windowStartWidth  := 400,
      windowStartHeight := 400
    )

lazy val confetti =
  project
    .in(file("confetti"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name              := "confetti",
      title             := "Confetti",
      windowStartWidth  := 640,
      windowStartHeight := 480
    )

lazy val inputmapper =
  project
    .in(file("inputmapper"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name              := "inputmapper-example",
      title             := "Input Mapper Example",
      windowStartWidth  := 400,
      windowStartHeight := 400
    )

lazy val mouseevents =
  project
    .in(file("mouseevents"))
    .settings(commonSettings: _*)
    .enablePlugins(SbtIndigo)
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name              := "mouseevents-example",
      title             := "Mouse Events Example",
      windowStartWidth  := 550,
      windowStartHeight := 400
    )

lazy val errors =
  project
    .in(file("errors"))
    .settings(commonSettings: _*)
    .enablePlugins(ScalaJSPlugin)
    .enablePlugins(SbtIndigo)
    .settings(
      name              := "errors",
      title             := "Error Handling",
      windowStartWidth  := 800,
      windowStartHeight := 800
    )

// Root
lazy val examplesProject =
  (project in file("."))
    .settings(
      code := {
        val command = Seq("code", ".")
        val run = sys.props("os.name").toLowerCase match {
          case x if x contains "windows" => Seq("cmd", "/C") ++ command
          case _                         => command
        }
        run.!
      }
    )
    .settings(
      logo := "Indigo Examples",
      usefulTasks := Seq(
        UsefulTask("", "cleanAll", "Clean all the projects"),
        UsefulTask("", "buildAllNoClean", "Rebuild without cleaning"),
        UsefulTask("", "testAllNoClean", "Test all without cleaning"),
        UsefulTask("", "code", "Launch VSCode")
      ) ++ makeCmds(ExampleProjects.exampleProjects),
      logoColor        := scala.Console.MAGENTA,
      aliasColor       := scala.Console.CYAN,
      commandColor     := scala.Console.BLUE,
      descriptionColor := scala.Console.WHITE
    )

def makeCmds(names: List[String]): Seq[UsefulTask] =
  names.zipWithIndex.map { case (n, i) =>
    val cmd = List(
      s"$n/fastOptJS",
      s"$n/indigoRun"
    ).mkString(";", ";", "")
    UsefulTask("run" + (i + 1), cmd, n)
  }.toSeq

lazy val code =
  taskKey[Unit]("Launch VSCode in the current directory")
