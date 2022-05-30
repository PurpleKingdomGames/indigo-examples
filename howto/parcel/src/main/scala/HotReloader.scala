import indigo._
import indigo.shared.events.StorageEvent

final case class HotReloader() extends SubSystem:
  type EventType      = SaveModel
  type SubSystemModel = Unit

  val id: SubSystemId = SubSystemId("hot reloader")

  def eventFilter: GlobalEvent => Option[EventType] = {
    case e: SaveModel => Some(e)
    case _            => None
  }

  def initialModel: Outcome[SubSystemModel] =
    Outcome(())

  def update(context: SubSystemFrameContext, model: SubSystemModel): EventType => Outcome[SubSystemModel] =
    case SaveModel(_) =>
      Outcome(model).addGlobalEvents(StorageEvent.Save("hot-reload-data", ""))

  def present(context: SubSystemFrameContext, model: SubSystemModel): Outcome[SceneUpdateFragment] =
    Outcome(SceneUpdateFragment.empty)

final case class SaveModel(model: Model) extends GlobalEvent
