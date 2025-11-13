package burli.bricks

import burli.*

/**
 * Decorator Button 
 *
 * @tparam F
 * has buttons Cancel, Back Next
 */

trait CancelBackNext[F <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Cancel = BTN[F]()(using ref)
  final val Back = BTN[F]()(using ref)
  final val Next = BTN[F]()(using ref)
}
