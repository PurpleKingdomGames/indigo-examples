package pirate

import indigo._
import indigo.scenes._
import indigoextras.subsystems.FPSCounter
import pirate.scenes.loading.LoadingScene
import pirate.scenes.level.LevelScene
import pirate.core.{Model, ViewModel}

import pirate.core.{Assets, InitialLoad, StartupData}

import scala.scalajs.js.annotation.JSExportTopLevel

/*
The Cursed Pirate uses scenes, and so extends `IndigoGame`.
The parameter types are "boot data", "start up data", "model", "view model"
*/
// This is the only line of Scala.js you *must* use!
@JSExportTopLevel("IndigoGame")
object CursedPirateDemo extends IndigoGame[Rectangle, StartupData, Model, ViewModel] {

  /*
  The boot function sets up the game basics. 
  Two important things to watch out for:
  1. The boot data type, in this case we're going to need the 
     screen dimensions later, so that's type we return.
  2. We only load the assets we need for the loading scene, since
     the loading scene loads the rest later.
  */
  def boot(flags: Map[String, String]): BootResult[Rectangle] = {
    val config =
      GameConfig.default
        .withViewport(GameViewport.at720p)
        .withMagnification(2)

    BootResult(
      config,
      config.screenDimensions
    ).withAssets(Assets.initialAssets)
      .withFonts(Assets.Fonts.fontInfo)
      .withSubSystems(FPSCounter(Assets.Fonts.fontKey, Point(10, 10), 60))
  }

  // The scene's list is ordered, so that you can go forward a backwards.
  // `initialScene` allows you to specify which scene to start at, but in
  // this case, the first scene in the list is correct so was just say
  // `None`
  def initialScene(screenDimensions: Rectangle): Option[SceneName] =
    None

  // Two scenes, the loading screen and the demo itself.
  def scenes(screenDimensions: Rectangle): NonEmptyList[Scene[StartupData, Model, ViewModel]] =
    NonEmptyList(LoadingScene, LevelScene(screenDimensions.width))

  def setup(
      screenDimensions: Rectangle,
      assetCollection: AssetCollection,
      dice: Dice
  ): Startup[StartupErrors, StartupData] =
    InitialLoad.setup(screenDimensions, assetCollection, dice)

  def initialModel(startupData: StartupData): Model =
    Model.initial

  def initialViewModel(startupData: StartupData, model: Model): ViewModel =
    ViewModel.initial

}
