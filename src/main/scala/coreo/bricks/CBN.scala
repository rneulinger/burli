package coreo.bricks

import coreo.*

/**
 * has buttons Cancel, Back Next
 */
trait CBN[F <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Cancel = BTN[F]()(using ref)
  final val Back = BTN[F]()(using ref)
  final val Next = BTN[F]()(using ref)
}
