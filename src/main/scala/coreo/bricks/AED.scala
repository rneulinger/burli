package coreo.bricks

import coreo.*

trait AED[F <: FRM] {
  self: F =>

  def ref: Own[F]

  final val Add = BTN[F]()(using ref) // new Dialog
  final val Edit = BTN[F]()(using ref) // edit selected
  final val Delete = BTN[F]()(using ref) // delete selected
}
