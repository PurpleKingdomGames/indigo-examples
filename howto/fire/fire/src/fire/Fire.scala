package fire

import indigo._
import indigo.ShaderPrimitive._

final case class Fire(
    position: Point,
    size: Size,
    depth: Depth,
    outer: RGB,
    inner: RGB,
    center: RGB,
    offset: Double
) extends EntityNode[Fire]:
  def flip: Flip        = Flip.default
  def ref: Point        = Point.zero
  def rotation: Radians = Radians.zero
  def scale: Vector2    = Vector2.one

  def withDepth(newDepth: Depth): Fire =
    this.copy(depth = newDepth)

  def toShaderData: ShaderData =
    ShaderData(
      Fire.shaderId,
      UniformBlock(
        "FireData",
        Batch(
          Uniform("OFFSET")       -> float(offset),
          Uniform("COLOR_OUTER")  -> vec3(outer.r, outer.g, outer.b),
          Uniform("COLOR_INNER")  -> vec3(inner.r, inner.g, inner.b),
          Uniform("COLOR_CENTER") -> vec3(center.r, center.g, center.b)
        )
      )
    )

  def eventHandler: ((Fire, GlobalEvent)) => Option[GlobalEvent] =
    Function.const(None)
  def eventHandlerEnabled: Boolean = false

object Fire:

  def orange(position: Point, size: Size): Fire =
    Fire(position, size, Depth.zero, RGB(1.0, 0.5, 0.0), RGB(1.0, 0.8, 0.0), RGB.White, 0.0d)

  def blue(position: Point, size: Size): Fire =
    Fire(position, size, Depth.zero, RGB.Blue, RGB.Cyan, RGB.White, 0.0d)

  def green(position: Point, size: Size): Fire =
    Fire(position, size, Depth.zero, RGB.Green, RGB.Yellow, RGB.White, 0.0d)

  def apply(position: Point, size: Size): Fire =
    orange(position, size)

  val shaderId: ShaderId =
    ShaderId("fire shader")

  def shader(fragProgram: AssetName): EntityShader =
    EntityShader
      .External(ShaderId("fire shader"))
      .withFragmentProgram(fragProgram)
