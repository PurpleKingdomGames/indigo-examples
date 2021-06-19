package fire

import indigo._

object Assets:

  val fireProgram: AssetName = AssetName("fire")
  val campBg: AssetName      = AssetName("fire-background")
  val campfire: AssetName    = AssetName("campfire")

  val assets: Set[AssetType] =
    Set(
      AssetType.Text(fireProgram, AssetPath(s"assets/${fireProgram.toString}.frag")),
      AssetType.Image(campBg, AssetPath(s"assets/${campBg.toString}.png")),
      AssetType.Image(campfire, AssetPath(s"assets/${campfire.toString}.png"))
    )
