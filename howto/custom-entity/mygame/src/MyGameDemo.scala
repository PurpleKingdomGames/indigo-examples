import indigo.*
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object MyGameDemo extends IndigoDemo[Unit, Unit, Unit, Unit] {

  val eventFilters: EventFilters =
    EventFilters.Permissive

  def boot(flags: Map[String, String]): Outcome[BootResult[Unit]] =
    Outcome(
      BootResult
        .noData(GameConfig.default)
        .withShaders(MyColoredEntity.shader, MyBitmapEntity.shader)
        .withAssets(AssetType.Image(AssetName("dots"), AssetPath("assets/dots.png")))
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
        MyColoredEntity(Point(10, 10), Depth(1)),
        MyBitmapEntity(AssetName("dots"), Point(10 + 32 + 10, 10), Depth(1))
      )
    )

}

// This is the interface we must satisfy to make a custom entity
// final case class MyCustomEntity() extends EntityNode:
//   def position: Point = ???
//   def size: Size = ???
//   def depth: Depth = ???
//   def flip: Flip = ???
//   def ref: Point = ???
//   def rotation: Radians = ???
//   def scale: Vector2 = ???
//   def withDepth(newDepth: Depth): MyCustomEntity = ???
//   def toShaderData: ShaderData = ???
//   def eventHandler: ((MyCustomEntity, GlobalEvent)) => Option[GlobalEvent]
//   def eventHandlerEnabled: Boolean

// A custom entity that flood fills it's area with green.
final case class MyColoredEntity(position: Point, depth: Depth) extends EntityNode[MyColoredEntity]:
  def size: Size        = Size(32, 32)
  def flip: Flip        = Flip.default
  def ref: Point        = Point.zero
  def rotation: Radians = Radians.zero
  def scale: Vector2    = Vector2.one

  def withDepth(newDepth: Depth): MyColoredEntity =
    this.copy(depth = newDepth)

  def toShaderData: ShaderData =
    ShaderData(MyColoredEntity.shader.id)

  def eventHandler: ((MyColoredEntity, GlobalEvent)) => Option[GlobalEvent] =
    Function.const(None)
  def eventHandlerEnabled: Boolean = false

object MyColoredEntity:
  val shader: EntityShader =
    EntityShader
      .Source(ShaderId("my-colored-shader"))
      .withFragmentProgram(
        """
        |void fragment() {
        |  //COLOR = vec4(0.0, 1.0, 0.0, 1.0); // solid green
        |  COLOR = vec4(UV.x, UV.y, 0.0, 1.0); // use UV coords as red and green values
        |}
        |""".stripMargin
      )

// A custom entity that flood fills it's area with green.
final case class MyBitmapEntity(asset: AssetName, position: Point, depth: Depth) extends EntityNode[MyBitmapEntity]:
  def size: Size        = Size(32, 32)
  def flip: Flip        = Flip.default
  def ref: Point        = Point.zero
  def rotation: Radians = Radians.zero
  def scale: Vector2    = Vector2.one

  def withDepth(newDepth: Depth): MyBitmapEntity =
    this.copy(depth = newDepth)

  def toShaderData: ShaderData =
    ShaderData(MyBitmapEntity.shader.id)
      .withChannel0(asset)

  def eventHandler: ((MyBitmapEntity, GlobalEvent)) => Option[GlobalEvent] =
    Function.const(None)
  def eventHandlerEnabled: Boolean = false

object MyBitmapEntity:
  val shader: EntityShader =
    EntityShader
      .Source(ShaderId("my-bitmap-shader"))
      .withFragmentProgram(
        """
        |void fragment() {
        |  //COLOR = vec4(CHANNEL_0.r, 0.0, 0.0, CHANNEL_0.a); // would only use the red channel from the texture
        |  COLOR = CHANNEL_0; // draw the texture
        |}
        |""".stripMargin
      )
