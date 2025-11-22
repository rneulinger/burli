package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F
 * has buttons Ok, Cancel
 */
trait OkCancel[F <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Ok = BTN[F]()(using ref)
  final val Cancel = BTN[F]()(using ref)
}
