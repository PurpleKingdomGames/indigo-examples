package fire

import indigo.*
import indigo.scenes.*
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object FireGame extends IndigoDemo[Unit, Unit, Unit, Unit] {

  val eventFilters: EventFilters =
    EventFilters.Permissive

  def boot(flags: Map[String, String]): Outcome[BootResult[Unit]] =
    Outcome(
      BootResult
        .noData(GameConfig.default.withViewport(384, 384).withMagnification(2))
        .withShaders(Fire.shader(Assets.fireProgram))
        .withAssets(Assets.assets)
    )

  def initialModel(startupData: Unit): Outcome[Unit] =
    Outcome(())

  def initialViewModel(startupData: Unit, model: Unit): Outcome[Unit] =
    Outcome(())

  def setup(bootData: Unit, assetCollection: AssetCollection, dice: Dice): Outcome[Startup[Unit]] =
    Outcome(Startup.Success(()))

  def updateModel(context: FrameContext[Unit], model: Unit): GlobalEvent => Outcome[Unit] =
    _ => Outcome(model)

  def updateViewModel(context: FrameContext[Unit], model: Unit, viewModel: Unit): GlobalEvent => Outcome[Unit] =
    _ => Outcome(viewModel)

  def present(context: FrameContext[Unit], model: Unit, viewModel: Unit): Outcome[SceneUpdateFragment] =
    Outcome(
      SceneUpdateFragment(
        Graphic(192, 192, Material.Bitmap(Assets.campBg)),
        Fire.orange(Point(60, 30), Size(70, 100)),
        Graphic(192, 192, Material.Bitmap(Assets.campfire))
      )
    )

}
