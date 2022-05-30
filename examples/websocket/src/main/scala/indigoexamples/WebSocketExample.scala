package indigoexamples

import indigo.*
import indigoextras.ui.*

import scala.scalajs.js.annotation.*

@JSExportTopLevel("IndigoGame")
object WebSocketExample extends IndigoDemo[Unit, WebSocketConfig, Batch[String], Button] {

  val eventFilters: EventFilters = EventFilters.Permissive

  def boot(flags: Map[String, String]): Outcome[BootResult[Unit]] =
    Outcome(
      BootResult
        .noData(defaultGameConfig)
        .withAssets(AssetType.Image(AssetName("graphics"), AssetPath("assets/graphics.png")))
    )

  val buttonAssets: ButtonAssets =
    ButtonAssets(
      up = Graphic(0, 0, 16, 16, 2, Material.Bitmap(AssetName("graphics"))).withCrop(32, 0, 16, 16),
      over = Graphic(0, 0, 16, 16, 2, Material.Bitmap(AssetName("graphics"))).withCrop(32, 16, 16, 16),
      down = Graphic(0, 0, 16, 16, 2, Material.Bitmap(AssetName("graphics"))).withCrop(32, 32, 16, 16)
    )

  def setup(bootData: Unit, assetCollection: AssetCollection, dice: Dice): Outcome[Startup[WebSocketConfig]] =
    Outcome(
      Startup.Success(
        WebSocketConfig(
          id = WebSocketId("echo"),
          address = "ws://localhost:8080/wsecho"
        )
      )
    )

  def initialModel(echoSocket: WebSocketConfig): Outcome[Batch[String]] =
    Outcome(Batch.empty)

  def initialViewModel(echoSocket: WebSocketConfig, log: Batch[String]): Outcome[Button] =
    Outcome(
      Button(
        buttonAssets = buttonAssets,
        bounds = Rectangle(100, 10, 16, 16),
        depth = Depth(2)
      ).withUpActions(WebSocketEvent.Send("Hello!", echoSocket))
    )

  def updateModel(context: FrameContext[WebSocketConfig], log: Batch[String]): GlobalEvent => Outcome[Batch[String]] = {
    case WebSocketEvent.Receive(WebSocketId("echo"), message) =>
      val msg = "Server says you said: " + message
      IndigoLogger.consoleLog(msg)
      Outcome(msg :: log)

    case WebSocketEvent.Error(WebSocketId(id), message) =>
      val msg = s"Connection [$id] errored with: " + message
      IndigoLogger.consoleLog(msg)
      Outcome(msg :: log)

    case WebSocketEvent.Close(WebSocketId(id)) =>
      val msg = s"Connection [$id] closed."
      IndigoLogger.consoleLog(msg)
      Outcome(msg :: log)

    case _ =>
      Outcome(log)
  }

  def updateViewModel(context: FrameContext[WebSocketConfig], log: Batch[String], button: Button): GlobalEvent => Outcome[Button] = {
    case FrameTick =>
      button.update(context.inputState.mouse)

    case _ =>
      Outcome(button)
  }

  def present(context: FrameContext[WebSocketConfig], log: Batch[String], button: Button): Outcome[SceneUpdateFragment] =
    Outcome(
      SceneUpdateFragment(
        Layer(
          Batch(
            TextBox("Click to connect: ").withColor(RGBA.White).moveTo(5, 12),
            button.draw,
            TextBox("Message Log: ").withColor(RGBA.Green).moveTo(5, 25)
          ) ++
            log.zipWithIndex.map { case (msg, i) =>
              val ii = i + 1
              TextBox(s"(${(log.length - ii).toString}) $msg").withColor(RGBA.Green).moveTo(15, 25 + (ii * 12))
            }
        )
      )
    )
}
