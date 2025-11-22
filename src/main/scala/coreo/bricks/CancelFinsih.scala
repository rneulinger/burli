package coreo.bricks

import coreo.*

/**
 * has buttons Cancel and Finish
 */
trait CancelFinsih[F <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Cancel = BTN[F]()(using ref)
  final val Finish = BTN[F]()(using ref)
}
