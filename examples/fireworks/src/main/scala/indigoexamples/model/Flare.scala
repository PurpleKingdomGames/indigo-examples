package indigoexamples.model

import indigo.shared.collections.NonEmptyList
import indigo.shared.datatypes.RGBA
import indigo.shared.datatypes.Radians
import indigo.shared.dice.Dice
import indigo.shared.temporal.Signal
import indigo.shared.time.Seconds
import indigoextras.geometry.Vertex

final case class Flare(flightTime: Seconds, movementSignal: Signal[Vertex], tint: RGBA) extends Projectile

object Flare {

  def generateFlare(dice: Dice, startPosition: Vertex, initialAngle: Radians, tint: RGBA): Flare = {
    val flightTime = Projectiles.pickFlightTime(dice, Seconds(0.3), Seconds(0.5))

    val signalFunction: Dice => Signal[Vertex] =
      pickEndPoint(initialAngle) andThen
        createArcControlVertices(startPosition) andThen
        Projectiles.createArcSignal(flightTime)

    Flare(flightTime, signalFunction(dice), tint)
  }

  def createArcControlVertices(startPosition: Vertex): Vertex => NonEmptyList[Vertex] =
    target => NonEmptyList(startPosition, startPosition + target)

  def pickEndPoint(initialAngle: Radians): Dice => Vertex =
    dice => {
      val radius: Double =
        dice.rollDouble * 0.3d

      Vertex(
        Math.sin(wobble(dice, initialAngle.toDouble - 0.2d, initialAngle.toDouble + 0.2d)),
        Math.cos(wobble(dice, initialAngle.toDouble - 0.2d, initialAngle.toDouble + 0.2d))
      ) * Vertex(radius * 3.0d, radius)
    }

  def wobble(dice: Dice, low: Double, high: Double): Double =
    low + ((high - low) * dice.rollDouble)

}
